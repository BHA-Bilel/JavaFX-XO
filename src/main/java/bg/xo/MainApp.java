package bg.xo;

import bg.xo.game.GameApp;
import bg.xo.gfx.Assets;
import bg.xo.lang.LANGNAME;
import bg.xo.lang.Language;
import bg.xo.popup.MyAlert;
import bg.xo.popup.MyTextInputDialog;
import bg.xo.room.JoinApp;
import bg.xo.room.RoomApp;
import bg.xo.server.local.LocalClient;
import bg.xo.server.room.RoomServer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import shared.Game;
import shared.MainRequest;
import shared.RoomInfo;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class MainApp extends Application {

    public static String CURRENT_VERSION;

    public static RoomServer roomServer;
    private Runnable connectRunnable;
    public static String ONLINE_IP;
    public static String MULTICAST_IP;
    public static int ONLINE_PORT, ONLINE_GAME_PORT, ONLINE_TIMEOUT, LOCAL_TIMEOUT, JOINERS_PORT, HOSTERS_PORT;

    public static final String[] themes = new String[]{"/main_light.css", "/main_dark.css"};
    public static String USERNAME, PREF_MODE, CURRENT_THEME;

    public static Stage stage;
    public static final Game BG_GAME = Game.XO;
    public static StringProperty GAME_NAME;
    public static JFXToggleButton online_mode;
    public static DoubleProperty fontProperty, spacingProperty, paddingProperty;

    private BorderPane root;
    private VBox center;
    private JFXTextField username;
    private Menu game_menu;
    private JFXButton host, join_rooms, join_specific;
    private boolean valid;

    private volatile boolean main_shortcuts_activated = false;
    private JoinApp joinApp;
    public RoomApp roomApp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Assets.init_resolution();
        get_properties();
        GAME_NAME = new SimpleStringProperty();
        GAME_NAME.bind(Language.XO);
        stage.titleProperty().bind(GAME_NAME);
        stage.setWidth(Assets.width / 2);
        stage.setHeight(Assets.height / 2);
        stage.setMaximized(true);
        stage.setOnCloseRequest(e -> release_ressources());
        createGUI();
        MyAlert.post_init();
        connectRunnable = () -> {
            try {
                Socket listen = new Socket();
                listen.connect(new InetSocketAddress(ONLINE_IP, ONLINE_PORT), ONLINE_TIMEOUT);
                try (DataOutputStream dataOut = new DataOutputStream(listen.getOutputStream());
                     DataInputStream dataIn = new DataInputStream(listen.getInputStream())) {
                    dataOut.writeInt(BG_GAME.ordinal());
                    dataOut.flush();
                    try {
                        ONLINE_GAME_PORT = dataIn.readInt();
                    } catch (NullPointerException e) {
                        Alert alert = new MyAlert(AlertType.INFORMATION, Language.unav(), Language.GS_NA_C);
                        alert.setOnHiding(value -> System.exit(1));
                        alert.show();
                        setup_local_multiplayer();
                    }
                } catch (IOException e) {
                    Alert alert = new MyAlert(AlertType.INFORMATION, Language.unav(), Language.GS_NA_C);
                    alert.setOnHiding(value -> System.exit(1));
                    alert.show();
                    setup_local_multiplayer();
                }
                try {
                    listen.close();
                } catch (IOException ignore) {
                }
                Platform.runLater(() -> {
                    LocalClient.stop_local();
                    MyAlert.close_connect_alert();
                    add_join_shortcut();
                });
            } catch (IOException e) {
                Platform.runLater(() -> {
                    MyAlert.close_connect_alert();
                    Alert alert = new MyAlert(AlertType.WARNING, Language.CR_MS_H, Language.CR_MS_C);
                    ButtonType retry = new ButtonType(Language.RETRY.getValue());
                    ButtonType enter_new = new ButtonType(Language.ENTER_IP_PORT.getValue());
                    ButtonType cancel1 = new ButtonType(Language.CANCEL.getValue(), ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(retry, enter_new, cancel1);
                    Optional<ButtonType> res = alert.showAndWait();
                    if (res.isEmpty() || res.get() == cancel1) {
                        online_mode.setSelected(false);
                        return;
                    }
                    if (res.get() == retry) {
                        MyAlert.show_connect_alert();
                        reachServer();
                    } else if (res.get() == enter_new) {
                        valid = false;
                        while (!valid) {
                            TextInputDialog ip_dialog = new MyTextInputDialog(Language.IP_H, ONLINE_IP);
                            Optional<String> result = ip_dialog.showAndWait();
                            if (result.isPresent()) {
                                if (valid_ip(result.get())) {
                                    ONLINE_IP = result.get();
                                    valid = true;
                                }
                            } else {
                                online_mode.setSelected(false);
                                return;
                            }
                        }
                        valid = false;
                        while (!valid) {
                            TextInputDialog port_dialog = new MyTextInputDialog(Language.PORT_H, String.valueOf(ONLINE_PORT));
                            Optional<String> result = port_dialog.showAndWait();
                            if (result.isPresent()) {
                                try {
                                    ONLINE_PORT = Integer.parseInt(result.get());
                                    if (ONLINE_PORT < 65535 && ONLINE_PORT > 0)
                                        valid = true;
                                } catch (NumberFormatException ignore) {
                                }
                            } else {
                                online_mode.setSelected(false);
                                return;
                            }
                        }
                        updateOnineIpPort();
                        MyAlert.show_connect_alert();
                        reachServer();
                    }
                });
            }
        };
        if (isFirstTime()) {
//            todo don't forget to set to true in config file BEFORE CREATING ZIPS
            MyAlert.first_timer(this, 1);
        } else {
            setup_pref_mode();
            Platform.runLater(() -> {
                Notifications notif = Notifications.create()
                        .title(MainApp.GAME_NAME.getValue()).text(Language.WELCOME_BACK.getValue())
                        .owner(stage).hideCloseButton();
                notif.show();
            });
        }
    }

    public void setup_pref_mode() {
        if (online_mode.isSelected()) {
            if (PREF_MODE.equals("LOCAL"))
                online_mode.setSelected(false);
            else setup_online_multiplayer();
        } else {
            if (PREF_MODE.equals("ONLINE"))
                online_mode.setSelected(true);
            else setup_local_multiplayer();
        }
    }

    private void setup_online_multiplayer() {
        MyAlert.show_connect_alert();
        reachServer();
    }

    private void setup_local_multiplayer() {
        try {
            LocalClient.init_local(MULTICAST_IP, JOINERS_PORT, HOSTERS_PORT);
        } catch (IOException ignore) {
        }
    }

    public void reachServer() {
        Thread t = new Thread(connectRunnable);
        t.start();
    }

    // config read begin
    private boolean isFirstTime() {
        Properties prop = new Properties();
        try (InputStream ip = MainApp.class.getResourceAsStream("/config.properties")) {
            prop.load(ip);
            return prop.getProperty("FIRST_TIME").equals("true");
        } catch (IOException ignore) {
        }
        return false;
    }

    private void get_properties() {
        Properties prop = new Properties();
        try (InputStream ip = MainApp.class.getResourceAsStream("/config.properties")) {
            prop.load(ip);
            CURRENT_VERSION = prop.getProperty("CURRENT_VERSION");
            ONLINE_IP = prop.getProperty("ONLINE_IP");
            ONLINE_PORT = Integer.parseInt(prop.getProperty("ONLINE_PORT"));
            MULTICAST_IP = prop.getProperty("MULTICAST_IP");
            JOINERS_PORT = Integer.parseInt(prop.getProperty("JOINERS_PORT"));
            HOSTERS_PORT = Integer.parseInt(prop.getProperty("HOSTERS_PORT"));
            ONLINE_TIMEOUT = Integer.parseInt(prop.getProperty("ONLINE_TIMEOUT"));
            LOCAL_TIMEOUT = Integer.parseInt(prop.getProperty("LOCAL_TIMEOUT"));
            CURRENT_THEME = prop.getProperty("THEME");
            Language.init();
            Language.load_lang(LANGNAME.values()[Integer.parseInt(prop.getProperty("LANG"))]);
            PREF_MODE = prop.getProperty("PREF_MODE");
        } catch (IOException ignore) {
        }
        try (FileInputStream fis = new FileInputStream("src/main/resources/username.txt");
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            USERNAME = reader.readLine();
        } catch (IOException ignore) {
        }
    }
    // config read end

    // config write begin
    public void first_time_done() {
        Properties prop = new Properties();
        String fname = "src/main/resources/config.properties";
        try (InputStream ip = MainApp.class.getResourceAsStream("/config.properties")) {
            prop.load(ip);
            prop.setProperty("FIRST_TIME", "false");
            prop.store(new FileOutputStream(fname), null);
        } catch (IOException ignore) {
        }
        setup_pref_mode();
    }

    private static void save_new_username(String username) {
        USERNAME = username;
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream("src/main/resources/username.txt"), StandardCharsets.UTF_8)) {
            writer.write(username);
        } catch (IOException ignore) {
        }
    }

    public void update_theme(String NEW_THEME) {
        CURRENT_THEME = NEW_THEME;
        Properties prop = new Properties();
        String fname = "src/main/resources/config.properties";
        try (InputStream ip = MainApp.class.getResourceAsStream("/config.properties")) {
            prop.load(ip);
            prop.setProperty("THEME", NEW_THEME);
            prop.store(new FileOutputStream(fname), null);
        } catch (IOException ignore) {
        }
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(MainApp.class.getResource(CURRENT_THEME).toExternalForm());
        stage.getScene().getStylesheets().add(MainApp.class.getResource(CURRENT_THEME.replace("/main_", "/notif_")).toExternalForm());
        if (roomApp != null) {
            roomApp.updateChatTheme();
        }
        MyAlert.update_alerts_theme();
    }

    private void update_lang_prop() {
        Properties prop = new Properties();
        String fname = "src/main/resources/config.properties";
        try (InputStream ip = MainApp.class.getResourceAsStream("/config.properties")) {
            prop.load(ip);
            prop.setProperty("LANG", String.valueOf(Language.LANG_NAME.ordinal()));
            prop.store(new FileOutputStream(fname), null);
        } catch (IOException ignore) {
        }
    }

    private void update_pref_mode(String mode) {
        Properties prop = new Properties();
        String fname = "src/main/resources/config.properties";
        try (InputStream ip = MainApp.class.getResourceAsStream("/config.properties")) {
            prop.load(ip);
            prop.setProperty("PREF_MODE", mode);
            prop.store(new FileOutputStream(fname), null);
        } catch (IOException ignore) {
        }
    }

    private void updateOnineIpPort() {
        Properties prop = new Properties();
        String fname = "src/main/resources/config.properties";
        try (InputStream ip = MainApp.class.getResourceAsStream("/config.properties")) {
            prop.load(ip);
            prop.setProperty("SERVER_IP", ONLINE_IP);
            prop.setProperty("PORT", Integer.toString(ONLINE_PORT));
            prop.store(new FileOutputStream(fname), null);
        } catch (IOException ignore) {
        }
    }
    // config write end

    private boolean valid_ip(String ip) {
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("(?<!\\d|\\d\\.)"
                + "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])" + "(?!\\d|\\.\\d)")
                .matcher(ip);
        String result = m.find() ? m.group() : null;
        return result != null;
    }

    public void createGUI() {
        root = new BorderPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        fontProperty = new SimpleDoubleProperty();
        spacingProperty = new SimpleDoubleProperty();
        paddingProperty = new SimpleDoubleProperty();
        fontProperty.bind(scene.widthProperty().add(scene.heightProperty()).divide(100));
        spacingProperty.bind(scene.widthProperty().add(scene.heightProperty()).divide(100));
        paddingProperty.bind(scene.widthProperty().add(scene.heightProperty()).divide(100));
        root.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontProperty.asString()));
        MenuBar top = createTopGUI();
        center = createCenterGUI();
        root.setTop(top);
        root.setCenter(center);
        root.requestFocus();
        scene.getStylesheets().add(MainApp.class.getResource(CURRENT_THEME).toExternalForm());
        scene.setOnMousePressed(e -> root.requestFocus());
        setup_shotcuts();
        stage.show();
        setup_notif_css();
    }

    private void setup_notif_css() {
        for (String css : themes) {
            String notif_css_file = "src/main/resources" + css.replace("main", "notif");
            StringBuilder notif_css = new StringBuilder();
            try (FileInputStream fis = new FileInputStream(notif_css_file);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr)) {
                String line;
                boolean first_line = true;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("-fx-font-size:")) {
                        line = "    -fx-font-size: " + fontProperty.intValue() + "px;";
                    }
                    if (first_line) {
                        notif_css.append(line);
                        first_line = false;
                    } else notif_css.append("\n").append(line);
                }
            } catch (IOException ignore) {
            }
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(notif_css_file))) {
                writer.write(notif_css.toString());
            } catch (IOException ignore) {
            }
        }
        stage.getScene().getStylesheets().add(MainApp.class.getResource(CURRENT_THEME.replace("/main_", "/notif_")).toExternalForm());
    }

    private MenuBar createTopGUI() {
        MenuBar top = new MenuBar();

        Menu window = new Menu();
        window.textProperty().bind(Language.WINDOW);
        MenuItem switch_fs = new MenuItem();
        switch_fs.textProperty().bind(Language.SWITCH_FS);
        switch_fs.setAccelerator(KeyCombination.keyCombination("F11"));
        MenuItem switch_theme = new MenuItem();
        switch_theme.textProperty().bind(Language.SWITCH_THEME);
        switch_theme.setAccelerator(KeyCombination.keyCombination("F2"));

        Menu language = new Menu();
        language.textProperty().bind(Language.LANGUAGE);
        MenuItem english = new MenuItem();
        english.textProperty().bind(Language.ENGLISH);
        MenuItem french = new MenuItem();
        french.textProperty().bind(Language.FRENCH);
        MenuItem arabic = new MenuItem();
        arabic.textProperty().bind(Language.ARABIC);

        Menu shortcuts = new Menu();
        shortcuts.textProperty().bind(Language.SHORTCUTS);
        MenuItem general_shortcuts = new MenuItem();
        general_shortcuts.textProperty().bind(Language.GENERAL);
        MenuItem main_shortcuts = new MenuItem();
        main_shortcuts.textProperty().bind(Language.MAIN);
        MenuItem join_shortcuts = new MenuItem();
        join_shortcuts.textProperty().bind(Language.JOIN);
        MenuItem room_shortcuts = new MenuItem();
        room_shortcuts.textProperty().bind(Language.ROOM);
        MenuItem game_shortcuts = new MenuItem();
        game_shortcuts.textProperty().bind(Language.GAME);

        Menu about_menu = new Menu();
        about_menu.textProperty().bind(Language.ABOUT);
        MenuItem help = new MenuItem();
        help.textProperty().bind(Language.HELP);
        help.setAccelerator(KeyCombination.keyCombination("F1"));
        MenuItem about = new MenuItem();
        about.textProperty().bind(Language.ABOUT_ME);
        MenuItem feedback = new MenuItem();
        feedback.textProperty().bind(Language.FEEDBACK);
        MenuItem copyright = new MenuItem();
        copyright.textProperty().bind(Language.COPYRIGHT);

        Menu download_update = new Menu();
        download_update.textProperty().bind(Language.DOWNLOAD_UPDATE);
        MenuItem du_xo = new MenuItem();
        du_xo.textProperty().bind(Language.XO);
        MenuItem du_checkers = new MenuItem();
        du_checkers.textProperty().bind(Language.CHECKERS);
        MenuItem du_chess = new MenuItem();
        du_chess.textProperty().bind(Language.CHESS);
        MenuItem du_connect4 = new MenuItem();
        du_connect4.textProperty().bind(Language.CONNECT4);
        MenuItem du_dominoes = new MenuItem();
        du_dominoes.textProperty().bind(Language.DOMINOS);
        MenuItem du_coinche = new MenuItem();
        du_coinche.textProperty().bind(Language.COINCHE);

        game_menu = new Menu();
        game_menu.textProperty().bind(Language.GAME);
        game_menu.setDisable(true);
        MenuItem view_score = new MenuItem();
        view_score.setAccelerator(KeyCombination.keyCombination("ctrl+v"));
        view_score.textProperty().bind(Language.VIEW_SC);
        MenuItem end_game = new MenuItem();
        end_game.setAccelerator(KeyCombination.keyCombination("ctrl+e"));
        end_game.textProperty().bind(Language.END_GAME);

        shortcuts.getItems().addAll(general_shortcuts, main_shortcuts, join_shortcuts, room_shortcuts, game_shortcuts);
        window.getItems().addAll(switch_fs, switch_theme, shortcuts);
        language.getItems().addAll(english, french, arabic);
        download_update.getItems().addAll(du_xo, du_checkers, du_chess, du_connect4, du_dominoes, du_coinche);
        about_menu.getItems().addAll(about, help, feedback, copyright);
        game_menu.getItems().addAll(view_score, end_game);
        top.getMenus().addAll(window, language, download_update, about_menu, game_menu);

        switch_fs.setOnAction(e -> stage.setFullScreen(!stage.isFullScreen()));

        switch_theme.setOnAction(e -> {
            if (CURRENT_THEME.equals(themes[0])) update_theme(themes[1]);
            else update_theme(themes[0]);
        });

        general_shortcuts.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.GEN_SH_T, Language.GEN_SH_H, Language.GEN_SH_C);
            alert.show();
        });

        main_shortcuts.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.M_SH_T, Language.M_SH_H, Language.M_SH_C);
            alert.show();
        });

        join_shortcuts.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.J_SH_T, Language.J_SH_H, Language.J_SH_C);
            alert.show();
        });

        room_shortcuts.setOnAction(e -> {
            Alert first_five = new MyAlert(AlertType.INFORMATION, Language.R_SH_T, Language.R_SH_H1, Language.R_SH_C1);
            ButtonType next_five = new ButtonType(Language.NEXT.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            first_five.getButtonTypes().setAll(next_five, ok);
            Optional<ButtonType> result = first_five.showAndWait();
            if (result.isPresent() && result.get().equals(next_five)) {
                Alert alert = new MyAlert(AlertType.INFORMATION, Language.R_SH_T, Language.R_SH_H2, Language.R_SH_C2);
                alert.show();
            }
        });

        game_shortcuts.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.G_SH_T, Language.G_SH_H, Language.G_SH_C);
            alert.show();
        });

        english.setOnAction(e -> {
            Language.load_lang(LANGNAME.ENGLISH);
            update_lang_prop();
        });

        french.setOnAction(e -> {
            Language.load_lang(LANGNAME.FRENCH);
            update_lang_prop();
        });

        arabic.setOnAction(e -> {
            Language.load_lang(LANGNAME.ARABIC);
            update_lang_prop();
        });

        du_xo.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.UPDATE, Language.DU_XO_H, Language.DU_XO_C);
            ButtonType du_button = new ButtonType(Language.CHECK.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(du_button, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == du_button)
                open_url("https://github.com/BHA-Bilel/JavaFX-XO#setup");
        });

        du_checkers.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.DOWNLOAD, Language.DU_CHECKERS_H, Language.DU_CHECKERS_C);
            alert.initOwner(stage);
            ButtonType du_button = new ButtonType(Language.DOWNLOAD_NOW.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(du_button, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == du_button)
                open_url("https://github.com/BHA-Bilel/JavaFX-CHECKERS#setup");
        });

        du_chess.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.DOWNLOAD, Language.DU_CHESS_H, Language.DU_CHESS_C);
            ButtonType du_button = new ButtonType(Language.DOWNLOAD_NOW.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(du_button, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == du_button)
                open_url("https://github.com/BHA-Bilel/JavaFX-CHESS#setup");
        });

        du_connect4.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.DOWNLOAD, Language.DU_CONNECT4_H, Language.DU_CONNECT4_C);
            ButtonType du_button = new ButtonType(Language.DOWNLOAD_NOW.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(du_button, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == du_button)
                open_url("https://github.com/BHA-Bilel/JavaFX-CONNECT4#setup");
        });

        du_dominoes.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.DOWNLOAD, Language.DU_DOMINOS_H, Language.DU_DOMINOS_C);
            ButtonType du_button = new ButtonType(Language.DOWNLOAD_NOW.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(du_button, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == du_button)
                open_url("https://github.com/BHA-Bilel/JavaFX-DOMINOS#setup");
        });

        du_coinche.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.DOWNLOAD, Language.DU_COINCHE_H, Language.DU_COINCHE_C);
            ButtonType du_button = new ButtonType(Language.DOWNLOAD_NOW.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(du_button, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == du_button)
                open_url("https://github.com/BHA-Bilel/JavaFX-COINCHE#setup");
        });

        about.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.ABOUT_T, Language.ABOUT_H, Language.ABOUT_C);
            ButtonType visit_gh = new ButtonType(Language.VISIT_GH.getValue());
            ButtonType visit_li = new ButtonType(Language.VISIT_LI.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(visit_gh, visit_li, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent()) {
                boolean visit_linkedin = res.get() == visit_li, visit_github = res.get() == visit_gh;
                if (visit_linkedin || visit_github) {
                    String url = visit_linkedin ? "https://www.linkedin.com/in/bilel-bha/" : "https://github.com/BHA-Bilel";
                    open_url(url);
                }
            }
        });

        help.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.HELP, Language.HELP_H, Language.HELP_C);
            ButtonType get_help = new ButtonType(Language.HELP_BT.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(get_help, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == get_help)
                open_url("https://github.com/BHA-Bilel/JavaFX-XO#how-to-play"); // todo change in other bg
        });

        feedback.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.FEEDBACK, Language.FEEDBACK_H, Language.FEEDBACK_C);
            ButtonType give_feedback = new ButtonType(Language.FEEDBACK_BT.getValue());
            ButtonType ok = new ButtonType(Language.OK.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(give_feedback, ok);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == give_feedback)
                open_url("https://gist.github.com/BHA-Bilel/77892e183cce8aaf2408e365a66f21ad");
        });

        copyright.setOnAction(e -> {
            Alert alert = new MyAlert(AlertType.INFORMATION, Language.COPYRIGHT_T, Language.COPYRIGHT_H, Language.COPYRIGHT_C);
            ButtonType open_project = new ButtonType(Language.COPYRIGHT_BT.getValue());
            ButtonType accept = new ButtonType(Language.ACCEPT.getValue(), ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(open_project, accept);
            Optional<ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == open_project) {
                open_url("https://github.com/BHA-Bilel/JavaFX-DOMINOS");
            }
        });

        view_score.setOnAction(e -> {
            if (roomApp == null) return;
            roomApp.viewScore();
        });

        end_game.setOnAction(e -> {
            if (roomApp == null) return;
            roomApp.endGame();
        });

        return top;
    }

    private void open_url(String url) {
        String myOS = System.getProperty("os.name").toLowerCase();
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));
            } else {
                Runtime runtime = Runtime.getRuntime();
                if (myOS.contains("mac")) {
                    runtime.exec("open " + url);
                } else if (myOS.contains("nix") || myOS.contains("nux")) {
                    runtime.exec("xdg-open " + url);
                }
            }
        } catch (IOException | URISyntaxException ignore) {
        }
    }

    private VBox createCenterGUI() {
        VBox center = new VBox();
        center.spacingProperty().bind(spacingProperty);
        center.styleProperty().bind(Bindings.concat("-fx-padding: ", paddingProperty.asString()));
        center.setAlignment(Pos.CENTER);
        username = new JFXTextField();
        username.promptTextProperty().bind(Language.USERNAME);
        username.setText(USERNAME);
        username.maxWidthProperty().bind(stage.widthProperty().divide(3));
        VBox username_vb = new VBox();
        username_vb.setAlignment(Pos.CENTER);
        username_vb.getChildren().add(username);
        HBox top_hbox = createTopHBox();
        HBox toggle_hb = create_toggle();
        HBox hb2 = createBottomHBox();
        hb2.visibleProperty().bind(online_mode.selectedProperty());
        center.getChildren().addAll(username_vb, top_hbox, toggle_hb, hb2);
        return center;
    }

    private HBox createTopHBox() {
        HBox hb = new HBox();
        hb.spacingProperty().bind(spacingProperty);
        hb.styleProperty().bind(Bindings.concat("-fx-padding: ", paddingProperty.asString()));
        hb.setAlignment(Pos.CENTER);

        host = new JFXButton();
        host.textProperty().bind(Language.HOST);
        host.setMinSize(JFXButton.USE_PREF_SIZE, JFXButton.USE_PREF_SIZE);
        join_rooms = new JFXButton();
        join_rooms.textProperty().bind(Language.JOIN_PUB);
        join_rooms.setMinSize(JFXButton.USE_PREF_SIZE, JFXButton.USE_PREF_SIZE);

        host.setOnAction(e -> {
            if (!validUsername(username.getText()))
                return;
            HostRoom();
        });

        join_rooms.setOnAction(e -> {
            if (!validUsername(username.getText()))
                return;
            JoinPublicRooms();
        });

        hb.getChildren().addAll(host, join_rooms);
        return hb;
    }

    public HBox create_toggle() {
        HBox hb = new HBox();
        hb.spacingProperty().bind(spacingProperty);
        hb.styleProperty().bind(Bindings.concat("-fx-padding: ", paddingProperty.asString()));
        hb.setAlignment(Pos.CENTER);
        Label local = new Label();
        String local_style = local.getStyle() + "-fx-text-fill: #3CB371;";
        local.setStyle(local_style);
        local.textProperty().bind(Language.LOCAL);
        local.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        online_mode = new JFXToggleButton();
        online_mode.textProperty().bind(Language.ONLINE);
        online_mode.setMinSize(JFXToggleButton.USE_PREF_SIZE, JFXToggleButton.USE_PREF_SIZE);
        online_mode.selectedProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue) {
                local.setStyle(local_style.replace("-fx-text-fill: #3CB371;", "-fx-text-fill: #FFFFFF;"));
                setup_online_multiplayer();
            } else {
                local.setStyle(local_style);
                remove_join_shortcut();
                setup_local_multiplayer();
            }
        });
        hb.getChildren().addAll(local, online_mode);
        return hb;
    }

    private HBox createBottomHBox() {
        HBox hb = new HBox();
        hb.spacingProperty().bind(spacingProperty);
        hb.styleProperty().bind(Bindings.concat("-fx-padding: ", paddingProperty.asString()));
        hb.setAlignment(Pos.CENTER);

        JFXTextField room_id = new JFXTextField();
        room_id.setPrefColumnCount(5);
        room_id.promptTextProperty().bind(Language.ROOM_ID);
        room_id.setMinSize(TextField.USE_PREF_SIZE, TextField.USE_PREF_SIZE);

        join_specific = new JFXButton();
        join_specific.textProperty().bind(Language.JOIN_SPEC);
        join_specific.setMinSize(JFXButton.USE_PREF_SIZE, JFXButton.USE_PREF_SIZE);

        room_id.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                try {
                    String data = (String) Toolkit.getDefaultToolkit()
                            .getSystemClipboard().getData(DataFlavor.stringFlavor);
                    if (validRoomID(data, true) > 0)
                        room_id.setText(data);
                } catch (UnsupportedFlavorException | IOException ignore) {
                }
            }
        });

        join_specific.setOnAction(e -> {
            if (!validUsername(username.getText()))
                return;
            int roomID = validRoomID(room_id.getText(), false);
            if (roomID < 0)
                return;
            JoinSpecificRoom(String.valueOf(roomID));
        });

        hb.getChildren().addAll(room_id, join_specific);
        return hb;
    }

    public static boolean validUsername(String username) {
        if (username.isEmpty()) {
            Alert alert = new MyAlert(AlertType.WARNING, Language.UN_H1, Language.UN_C1);
            alert.show();
            return false;
        } else if (username.length() > 20) {
            Alert alert = new MyAlert(AlertType.WARNING, Language.UN_H2, Language.UN_C2);
            alert.show();
            return false;
        }
        if (!username.equals(USERNAME)) {
            save_new_username(username);
        }
        return true;
    }

    private int validRoomID(String room_id, boolean supress_alerts) {
        int roomID = -1;
        if (room_id.isEmpty()) {
            if (supress_alerts)
                return roomID;
            Alert alert = new MyAlert(AlertType.WARNING, Language.RID_H1, Language.RID_C1);
            alert.show();
        } else if (room_id.length() > 5) {
            return roomID;
        } else {
            try {
                roomID = Integer.parseInt(room_id);
                if (roomID > 65535) {
                    roomID = -1;
                    if (supress_alerts)
                        return roomID;
                    Alert alert = new MyAlert(AlertType.WARNING, Language.RID_H2, Language.RID_C2);
                    alert.show();
                }
            } catch (NumberFormatException ex) {
                if (supress_alerts)
                    return roomID;
                Alert alert = new MyAlert(AlertType.WARNING, Language.RID_H2, Language.RID_C2);
                alert.show();
            }
        }
        return roomID;
    }

    public void start_migration() {
        MyAlert.migration_started();
        Thread local_host = new Thread(() -> {
            try {
                roomServer = new RoomServer();
                Socket roomSocket = new Socket();
                roomSocket.connect(new InetSocketAddress("0.0.0.0", roomServer.getPort()), LOCAL_TIMEOUT);
                roomApp.send_new_room_server(RoomServer.getHostRoomInfo());
                roomApp.discardOldRoom();
                RoomApp roomApp = new RoomApp(this, null, roomSocket, String.valueOf(roomServer.getPort()), USERNAME);
                Platform.runLater(() -> setRoomApp(roomApp));
            } catch (IOException e) {
                roomServer = null;
                Platform.runLater(() -> {
                    Alert alert = new MyAlert(AlertType.WARNING, Language.HOST_H, Language.CHK_INTERNET);
                    alert.setOnHidden(hidden -> reachServer());
                    alert.show();
                });
            } finally {
                MyAlert.migration_finished();
            }
        });
        local_host.start();
    }

    public void migrate_new_host(RoomInfo roomInfo) {
        MyAlert.migration_started();
        Thread local_join = new Thread(() -> {
            boolean connected = false;
            for (String ip : roomInfo.ip) {
                try {
                    Socket roomSocket = new Socket();
                    roomSocket.connect(new InetSocketAddress(ip, roomInfo.room_id), LOCAL_TIMEOUT);
                    roomApp.discardOldRoom();
                    RoomApp roomApp = new RoomApp(this, null, roomSocket, String.valueOf(roomInfo.room_id), USERNAME);
                    Platform.runLater(() -> setRoomApp(roomApp));
                    connected = true;
                    break;
                } catch (IOException ignore) {
                }
            }
            if (connected) {
                MyAlert.migration_finished();
            } else {
                roomServer = null;
                Platform.runLater(() -> {
                    Alert alert = new MyAlert(AlertType.WARNING, Language.HOST_H, Language.CHK_INTERNET);
                    alert.setOnHidden(hidden -> reachServer());
                    alert.show();
                });
            }
        });
        local_join.start();
    }

    public void HostRoom() {
        MyAlert.prevent_user_interactions();
        if (online_mode.isSelected()) {
            Thread online_host = new Thread(() -> {
                try (Socket s = new Socket(ONLINE_IP, ONLINE_GAME_PORT);
                     ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream());
                     ObjectInputStream objIn = new ObjectInputStream(s.getInputStream())) {
                    objOut.writeInt(MainRequest.HOST.ordinal());
                    objOut.flush();
                    int response = objIn.readInt();
                    if (response > 0) {
                        try {
                            Socket roomSocket = new Socket();
                            roomSocket.connect(new InetSocketAddress(ONLINE_IP, response), ONLINE_TIMEOUT);
                            roomApp = new RoomApp(this, null, roomSocket, String.valueOf(response), username.getText());
                            Platform.runLater(() -> setRoomApp(roomApp));
                        } catch (IOException e) {
                            Platform.runLater(() -> {
                                Alert alert = new MyAlert(AlertType.WARNING, Language.HOST_H, Language.CHK_INTERNET);
                                alert.setOnHidden(hidden -> reachServer());
                                alert.show();
                            });
                        }
                    } else {
                        Platform.runLater(() -> {
                            Alert alert = new MyAlert(AlertType.WARNING, Language.HOST_H, Language.ALL_TAKEN);
                            alert.show();
                        });
                    }
                } catch (IOException e) {
                    Platform.runLater(() -> {
                        Alert alert = new MyAlert(AlertType.WARNING, Language.CR_MS_H, Language.CHK_INTERNET);
                        alert.setOnHidden(hidden -> reachServer());
                        alert.show();
                    });
                }
            });
            online_host.start();
        } else {
            Thread local_host = new Thread(() -> {
                try {
                    roomServer = new RoomServer();
                    Socket roomSocket = new Socket();
                    roomSocket.connect(new InetSocketAddress("0.0.0.0", roomServer.getPort()), LOCAL_TIMEOUT);
                    roomApp = new RoomApp(this, null, roomSocket, String.valueOf(roomServer.getPort()), username.getText());
                    Platform.runLater(() -> setRoomApp(roomApp));
                } catch (IOException e) {
                    roomServer = null;
                    Platform.runLater(() -> {
                        Alert alert = new MyAlert(AlertType.WARNING, Language.HOST_H, Language.CHK_INTERNET);
                        alert.setOnHidden(hidden -> reachServer());
                        alert.show();
                    });
                }
            });
            local_host.start();
        }
    }

    public void JoinSpecificRoom(String roomID) {
        MyAlert.prevent_user_interactions();
        Socket roomSocket = new Socket();
        Thread online_join = new Thread(() -> {
            try {
                roomSocket.connect(new InetSocketAddress(ONLINE_IP, Integer.parseInt(roomID)), ONLINE_TIMEOUT);
                roomApp = new RoomApp(this, null, roomSocket, roomID, username.getText());
                Platform.runLater(() -> setRoomApp(roomApp));
            } catch (IOException e) {
                try {
                    roomSocket.close();
                } catch (IOException ignore) {
                }
                Platform.runLater(() -> {
                    Alert alert = new MyAlert(AlertType.WARNING, Language.ROOM_H, Language.ROOM_C);
                    alert.setOnHidden(hidden -> reachServer());
                    alert.show();
                });
            }
        });
        online_join.start();
    }

    public void JoinPublicRooms() {
        MyAlert.prevent_user_interactions();
        if (online_mode.isSelected()) {
            Thread online_join = new Thread(() -> {
                try {
                    Socket joinSocket = new Socket();
                    joinSocket.connect(new InetSocketAddress(ONLINE_IP, ONLINE_GAME_PORT), ONLINE_TIMEOUT);
                    joinApp = new JoinApp(this, username.getText(), joinSocket);
                    Platform.runLater(() -> setJoinApp(joinApp));
                } catch (IOException e) {
                    Platform.runLater(() -> {
                        Alert alert = new MyAlert(AlertType.WARNING, Language.CR_MS_H, Language.CHK_INTERNET);
                        alert.setOnHidden(hidden -> reachServer());
                        alert.show();
                    });
                }
            });
            online_join.start();
        } else {
            Thread local_join = new Thread(() -> {
                Map<String, RoomInfo> rooms = LocalClient.send_join_req();
                joinApp = new JoinApp(this, username.getText(), rooms);
                Platform.runLater(() -> setJoinApp(joinApp));
            });
            local_join.start();
        }
    }

    private void release_ressources() {
        if (joinApp != null) {
            joinApp.closeJoinApp();
        }
        if (roomApp != null) {
            roomApp.discardOldRoom();
        }
    }

    public void returnHomeApp(boolean exception) {
        if (roomServer != null) {
            roomServer.closeRoom();
            roomServer = null;
        }
        joinApp = null;
        roomApp = null;
        username.setText(USERNAME);
        root.setCenter(center);
        setup_shotcuts();
        root.requestFocus();
        MyAlert.allow_user_interactions();
        if (exception) {
            Alert alert = new MyAlert(AlertType.WARNING, Language.COMM_ERROR_H, Language.COMM_ERROR_C);
            alert.setOnHidden(hidden -> {
                if (online_mode.isSelected())
                    reachServer();
            });
            alert.show();
        }
    }

    private void setup_shotcuts() {
        if (main_shortcuts_activated) return;
        main_shortcuts_activated = true;
        stage.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN), host::fire);
        stage.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.J, KeyCombination.CONTROL_DOWN), join_rooms::fire);
        stage.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN), () -> online_mode.setSelected(true));
        stage.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN), () -> online_mode.setSelected(false));
    }

    private void add_join_shortcut() {
        stage.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN), join_specific::fire);
    }

    private void remove_join_shortcut() {
        stage.getScene().getAccelerators().remove(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
    }

    private void remove_shortcuts() {
        if (!main_shortcuts_activated) return;
        main_shortcuts_activated = false;
        stage.getScene().getAccelerators().remove(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        stage.getScene().getAccelerators().remove(new KeyCodeCombination(KeyCode.J, KeyCombination.CONTROL_DOWN));
        stage.getScene().getAccelerators().remove(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        stage.getScene().getAccelerators().remove(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        stage.getScene().getAccelerators().remove(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
    }

    public void setJoinApp(JoinApp joinApp) {
        this.joinApp = joinApp;
        root.setCenter(joinApp);
        // because for some reason username textfield of join stays black on dark mode
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(MainApp.class.getResource(CURRENT_THEME).toExternalForm());
        remove_shortcuts();
        joinApp.setup_shortcuts();
        root.requestFocus();
        MyAlert.allow_user_interactions();
    }

    public void setRoomApp(RoomApp roomApp) {
        if (online_mode.isSelected()) {
            update_pref_mode("ONLINE");
        } else {
            update_pref_mode("LOCAL");
        }
        this.roomApp = roomApp;
        root.setCenter(roomApp);
        remove_shortcuts();
        roomApp.setup_shortcuts();
        root.requestFocus();
        MyAlert.allow_user_interactions();
    }

    public void disable_game_menu() {
        game_menu.setDisable(true);
    }

    public void setGameApp(GameApp gameApp) {
        root.setCenter(gameApp);
        roomApp.partial_shortcuts_remove();
        game_menu.setDisable(false);
        MyAlert.allow_user_interactions();
    }

}
