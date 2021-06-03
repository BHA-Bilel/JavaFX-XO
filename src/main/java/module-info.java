module MainModule {
    requires javafx.controls;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires java.desktop;

    exports shared;
    exports bg.xo.server.local;
    exports bg.xo.server.room;
    exports bg.xo.lang;
    exports bg.xo.room;
    exports bg.xo.game;
    exports bg.xo;
}