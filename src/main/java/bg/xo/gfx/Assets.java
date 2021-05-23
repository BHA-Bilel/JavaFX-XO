package bg.xo.gfx;

import javafx.scene.transform.Scale;

import java.awt.*;

public class Assets {

    public static double width, height;
    public static double mainApp_width = 0, mainApp_height = 0;
    public static double joinApp_width = 0, joinApp_height = 0;
    public static double roomApp_width = 0, roomApp_height = 0;
    public static double gameApp_width = 0, gameApp_height = 0;
    public static Scale scale;

    public static void init_scale() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        double scale_width = 1, scale_height = 1;
        if (height < 900 && height > 600) {
            scale_height = .9;
            scale_width = .9;
        } else if (height <= 600) {
            scale_height = .7;
            scale_width = .7;
        }
        scale = new Scale(scale_width, scale_height, 0, 0);
    }

}