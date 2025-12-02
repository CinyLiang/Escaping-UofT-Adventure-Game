package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UISettings {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final Color PARCHMENT_BACKGROUND = new Color(245, 235, 209);
    public static final Color DARK_MAP_TEXT = new Color(40, 25, 15);
    public static final Color ACCENT_COLOR = new Color(110, 40, 40);
    public static final Color HOVER_COLOR = new Color(150, 75, 75);

    public static Font quintessentialBase;
    public static Font quintessential;
    public static Font texturinaBase;
    public static Font texturina;

    public static ImageIcon instructionsImage;
    public static ImageIcon navigationImage;
    public static ImageIcon conHallImage;
    public static ImageIcon conHallIntImage;
    public static ImageIcon conHallExtImage;
    public static ImageIcon knoxExtImage;
    public static ImageIcon knoxIntImage;
    public static ImageIcon gersteinIntImage;
    public static ImageIcon gersteinExtImage;

    static {
        try {
            InputStream quintStream = UISettings.class.getResourceAsStream("/Quintessential-Regular.ttf");
            if (quintStream != null) {
                quintessentialBase = Font.createFont(Font.TRUETYPE_FONT, quintStream);
                quintessential = quintessentialBase.deriveFont(Font.PLAIN, 24);
            } else {
                System.err.println("Could not load Quintessential font");
                quintessential = new Font("Serif", Font.PLAIN, 24);
            }

            InputStream textStream = UISettings.class.getResourceAsStream("/Texturina-VariableFont_opsz,wght.ttf");
            if (textStream != null) {
                texturinaBase = Font.createFont(Font.TRUETYPE_FONT, textStream);
                texturina = texturinaBase.deriveFont(Font.PLAIN, 24);
            } else {
                System.err.println("Could not load Texturina font");
                texturina = new Font("Serif", Font.PLAIN, 24);
            }

            instructionsImage = loadImageIcon("/Gemini_Generated_Image_1lcv901lcv901lcv.png");
            navigationImage = loadImageIcon("/mapview.jpg");
            conHallExtImage = loadImageIcon("/conhallext.png");
            conHallIntImage = loadImageIcon("/conhallint.jpg");
            conHallImage = conHallExtImage;
            knoxExtImage = loadImageIcon("/knoxext.png");
            knoxIntImage = loadImageIcon("/KNOXINT.jpg");
            gersteinIntImage = loadImageIcon("/gersteinint.jpg");
            gersteinExtImage = loadImageIcon("/gersteinext.jpg");

        } catch (FontFormatException | IOException e) {
            throw new RuntimeException("Failed to load UI resources", e);
        }
    }

    private static ImageIcon loadImageIcon(String path) {
        java.net.URL imgURL = UISettings.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Could not find image: " + path);
            return new ImageIcon();
        }
    }

    public UISettings() {}
}