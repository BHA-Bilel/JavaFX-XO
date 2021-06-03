package bg.xo.server.local;

import bg.xo.MainApp;
import bg.xo.server.room.RoomServer;
import shared.RoomInfo;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class LocalClient {

    private static final int bufferSize = 1024;
    private static int joiners_port, hosters_port;
    private static InetAddress local_group;
    private static MulticastSocket joiners_socket, hosters_socket;
    private static volatile boolean local_started = false, stop_waiting = false;

    //    multicast ip range 224.0.0.1 to 239.255.255.255
    // todo fix multicast on lan
//    try this multicast ip: 224.0.0.1
    public static void init_local(String multicast_ip, int joiners_port, int hosters_port) throws IOException {
        if (local_started) return;
        local_started = true;
        LocalClient.joiners_port = joiners_port;
        LocalClient.hosters_port = hosters_port;
        local_group = InetAddress.getByName(multicast_ip);
        joiners_socket = new MulticastSocket(joiners_port);
        hosters_socket = new MulticastSocket(hosters_port);
        hosters_socket.setSoTimeout(MainApp.LOCAL_TIMEOUT);
    }

    public static void stop_local() {
        if (!local_started) return;
        local_started = false;
        local_group = null;
        joiners_socket.close();
        hosters_socket.close();
        joiners_socket = null;
        hosters_socket = null;
    }

    public static void waitForClients() throws IOException {
        // todo debug why sometimes when client leave room doesn't get broadcasted anymore !
        byte[] buffer = new byte[0];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, local_group, joiners_port);
        joiners_socket.joinGroup(local_group);
        try {
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
        RoomInfo room_info = RoomServer.getJoinRoomInfo();
        oos.writeObject(room_info);
        byte[] data = baos.toByteArray();
        hosters_socket.joinGroup(local_group);
        try {
            hosters_socket.send(new DatagramPacket(data, data.length, local_group, hosters_port));
        } catch (IOException ignore) {
        }
        hosters_socket.close();
        hosters_socket = new MulticastSocket(hosters_port);
        hosters_socket.setSoTimeout(MainApp.LOCAL_TIMEOUT);
    }

    public static void stop_waiting() {
        stop_waiting = true;
        joiners_socket.close();
        try {
            joiners_socket = new MulticastSocket(joiners_port);
        } catch (IOException ignore) {
        }
    }

    public static Map<String, RoomInfo> send_join_req() {
        byte[] data = new byte[0];
        Map<String, RoomInfo> local_rooms = new HashMap<>();
        try {
            hosters_socket.joinGroup(local_group);
            joiners_socket.joinGroup(local_group);
            joiners_socket.send(new DatagramPacket(data, data.length, local_group, joiners_port));
            joiners_socket.close();
            joiners_socket = new MulticastSocket(joiners_port);
        } catch (IOException ignore) {
        }
        boolean available = true;
        int i = 0;
        try {
            while (available) {
                byte[] buffer = new byte[bufferSize];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, local_group, hosters_port);
                hosters_socket.receive(packet);
                String src_addr = packet.getAddress().getHostAddress();
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream ois = new ObjectInputStream(bais);

                available = bais.available() > 0;
                RoomInfo info = (RoomInfo) ois.readObject();
                local_rooms.put(i + src_addr, info);
                i++;
            }
        } catch (IOException | ClassNotFoundException ignore) {
        }
        hosters_socket.close();
        try {
            hosters_socket = new MulticastSocket(hosters_port);
            hosters_socket.setSoTimeout(MainApp.LOCAL_TIMEOUT);
        } catch (IOException ignore) {
        }

        return local_rooms;
    }
}
