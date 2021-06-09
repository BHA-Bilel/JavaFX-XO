package bg.xo.server.local;

import bg.xo.MainApp;
import bg.xo.server.room.RoomServer;
import shared.LocalRoomInfo;

import java.io.*;
import java.net.*;
import java.util.*;

public class LocalClient {

    private static final int bufferSize = 1024;
    private static int joiners_port, hosters_port;
    private static InetAddress local_group;
    private static MulticastSocket joiners_socket, hosters_socket;
    private static volatile boolean stop_waiting = false;

    public static boolean init_local(String multicast_ip, int joiners_port, int hosters_port) {
        LocalClient.joiners_port = joiners_port;
        LocalClient.hosters_port = hosters_port;
        try {
            local_group = InetAddress.getByName(multicast_ip);
            joiners_socket = new MulticastSocket(joiners_port);
            hosters_socket = new MulticastSocket(hosters_port);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static List<String> get_lan_ip() throws SocketException {
        List<String> local_ips = new ArrayList<>();
        for (final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
             interfaces.hasMoreElements(); ) {
            final NetworkInterface cur = interfaces.nextElement();
            for (final InterfaceAddress addr : cur.getInterfaceAddresses()) {
                final InetAddress inet_addr = addr.getAddress();
                if (!(inet_addr instanceof Inet4Address)) continue;
                String ip = inet_addr.getHostAddress();
                if (ip.startsWith("10.") || ip.startsWith("172.") || ip.startsWith("192.168"))
                    local_ips.add(inet_addr.getHostAddress());
            }
        }
        return local_ips;
    }

    public static void stop_local() {
        local_group = null;
        if (joiners_socket != null) {
            joiners_socket.close();
            hosters_socket.close();
        }
        joiners_socket = null;
        hosters_socket = null;
    }

    private static void send_all(MulticastSocket socket, DatagramPacket packet) throws IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            if (iface.isLoopback() || !iface.isUp())
                continue;

            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr.getHostAddress().startsWith("10.")
                        || addr.getHostAddress().startsWith("172.")
                        || addr.getHostAddress().startsWith("192.168")) {
                    socket.setInterface(addr);
                    socket.send(packet);
                }
            }
        }
    }

    private static void receive_all(MulticastSocket socket) throws IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            if (iface.isLoopback() || !iface.isUp())
                continue;

            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr.getHostAddress().startsWith("10.")
                        || addr.getHostAddress().startsWith("172.")
                        || addr.getHostAddress().startsWith("192.168")) {
                    socket.setInterface(addr);
                    socket.joinGroup(local_group);
                }
            }
        }
    }

    public static void waitForClients() throws IOException {
        byte[] buffer = new byte[0];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, local_group, joiners_port);
        try {
            receive_all(joiners_socket);
            joiners_socket.receive(packet);
        } catch (IOException ignore) {
        }
        if (stop_waiting) {
            stop_waiting = false;
            return;
        }
        joiners_socket.close();
        joiners_socket = new MulticastSocket(joiners_port);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        LocalRoomInfo room_info = RoomServer.getJoinRoomInfo();
        if (room_info == null) return;
        oos.writeObject(room_info);
        byte[] data = baos.toByteArray();
        try {
            send_all(hosters_socket, new DatagramPacket(data, data.length, local_group, hosters_port));
        } catch (IOException ignore) {
        }
        hosters_socket.close();
        hosters_socket = new MulticastSocket(hosters_port);
    }

    public static void stop_waiting() {
        stop_waiting = true;
        joiners_socket.close();
        try {
            joiners_socket = new MulticastSocket(joiners_port);
        } catch (IOException ignore) {
        }
    }

    public static Map<String, LocalRoomInfo> send_join_req() {
        byte[] data = new byte[0];
        Map<String, LocalRoomInfo> local_rooms = new HashMap<>();
        try {
            hosters_socket.setSoTimeout(MainApp.LOCAL_TIMEOUT);
            receive_all(hosters_socket);
            send_all(joiners_socket, new DatagramPacket(data, data.length, local_group, joiners_port));
            joiners_socket.close();
            joiners_socket = new MulticastSocket(joiners_port);
        } catch (IOException ignore) {
        }
        boolean available = true;
        int i = 0;
        try {
            List<String> temp = new ArrayList<>();
            while (available) {
                byte[] buffer = new byte[bufferSize];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, local_group, hosters_port);
                hosters_socket.receive(packet);
                String src_addr = packet.getAddress().getHostAddress();
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream ois = new ObjectInputStream(bais);

                available = bais.available() > 0;
                LocalRoomInfo info = (LocalRoomInfo) ois.readObject();
                if (!temp.contains(info.host_name + info.room_id)) {
                    temp.add(info.host_name + info.room_id);
                    local_rooms.put(i + src_addr, info);
                    i++;
                }
            }
        } catch (IOException | ClassNotFoundException ignore) {
        }
        hosters_socket.close();
        try {
            hosters_socket = new MulticastSocket(hosters_port);
        } catch (IOException ignore) {
        }
        return local_rooms;
    }
}
