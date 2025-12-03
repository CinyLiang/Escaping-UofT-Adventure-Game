package view.theme;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

//public class UISettings {
//    public static final int WINDOW_WIDTH = 800;
//    public static final int WINDOW_HEIGHT = 600;
//
//    public static final Color PARCHMENT_BACKGROUND = new Color(245, 235, 209);
//    public static final Color DARK_MAP_TEXT = new Color(40, 25, 15);
//    public static final Color ACCENT_COLOR = new Color(110, 40, 40);
//    public static final Color HOVER_COLOR = new Color(150, 75, 75);
//
//    public static Font quintessentialBase;
//    public static Font quintessential;
//    public static Font texturinaBase;
//    public static Font texturina;
//
//    public static ImageIcon instructionsImage;
//    public static ImageIcon navigationImage;
//    public static ImageIcon conHallImage;
//    public static ImageIcon conHallIntImage;
//    public static ImageIcon conHallExtImage;
//    public static ImageIcon knoxExtImage;
//    public static ImageIcon knoxIntImage;
//    public static ImageIcon gersteinIntImage;
//    public static ImageIcon gersteinExtImage;
//
//    static {
//        try {
//            InputStream quintStream = UISettings.class.getResourceAsStream("/Quintessential-Regular.ttf");
//            if (quintStream != null) {
//                quintessentialBase = Font.createFont(Font.TRUETYPE_FONT, quintStream);
//                quintessential = quintessentialBase.deriveFont(Font.PLAIN, 24);
//            } else {
//                System.err.println("Could not load Quintessential font");
//                quintessential = new Font("Serif", Font.PLAIN, 24);
//            }
//
//            InputStream textStream = UISettings.class.getResourceAsStream("/Texturina-VariableFont_opsz,wght.ttf");
//            if (textStream != null) {
//                texturinaBase = Font.createFont(Font.TRUETYPE_FONT, textStream);
//                texturina = texturinaBase.deriveFont(Font.PLAIN, 24);
//            } else {
//                System.err.println("Could not load Texturina font");
//                texturina = new Font("Serif", Font.PLAIN, 24);
//            }
//
//            instructionsImage = loadImageIcon("/Gemini_Generated_Image_1lcv901lcv901lcv.png");
//            navigationImage = loadImageIcon("/mapview.jpg");
//            conHallExtImage = loadImageIcon("/conhallext.png");
//            conHallIntImage = loadImageIcon("/conhallint.jpg");
//            conHallImage = conHallExtImage;
//            knoxExtImage = loadImageIcon("/knoxext.png");
//            knoxIntImage = loadImageIcon("/KNOXINT.jpg");
//            gersteinIntImage = loadImageIcon("/gersteinint.jpg");
//            gersteinExtImage = loadImageIcon("/gersteinext.jpg");
//
//        } catch (FontFormatException | IOException e) {
//            throw new RuntimeException("Failed to load UI resources", e);
//        }
//    }
//
//    private static ImageIcon loadImageIcon(String path) {
//        java.net.URL imgURL = UISettings.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Could not find image: " + path);
//            return new ImageIcon();
//        }
//    }
//
//    public UISettings() {}
//}

public class UISettings {
    public static final String QUINT_FONT_PATH = "/Users/vanessa.hanbao/Downloads/Quintessential/Quintessential-Regular.ttf";
    public static final String TEXTURINA_FONT_PATH = "/Users/vanessa.hanbao/Downloads/Quintessential,Texturina/Texturina/Texturina-VariableFont_opsz,wght.ttf";
    public static final String NAVIGATE_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/mapview.jpg";
    public static final String INSTRUCTIONS_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/Gemini_Generated_Image_1lcv901lcv901lcv.png";
    public static final String CONHALL_INT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/conhallint.png";
    public static final String CONHALL_EXT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/conhallext.png";
    public static final String KNOX_EXT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/knoxext.png";
    public static final String KNOX_INT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/KNOXINT.jpg";
    public static final String GERSTEIN_INT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/gersteinint.jpg";
    public static final String GERSTEIN_EXT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/gersteinext.jpg";
    public static final String UC_EXT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/ucext.jpg";
    public static final String UC_INT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/csc207 final project resources/ucint.jpg";

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final java.awt.Color PARCHMENT_BACKGROUND = new java.awt.Color(245, 235, 209);
    public static final java.awt.Color DARK_MAP_TEXT = new java.awt.Color(40, 25, 15);
    public static final java.awt.Color ACCENT_COLOR = new java.awt.Color(110, 40, 40);
    public static final java.awt.Color HOVER_COLOR = new java.awt.Color(150, 75, 75);

    public static Font quintessentialBase;

    static {
        try {
            quintessentialBase = Font.createFont(Font.TRUETYPE_FONT, new File(QUINT_FONT_PATH));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Font quintessential = quintessentialBase.deriveFont(Font.PLAIN, 24);

    public static Font texturinaBase;

    static {
        try {
            texturinaBase = Font.createFont(Font.TRUETYPE_FONT, new File(TEXTURINA_FONT_PATH));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Font texturina = texturinaBase.deriveFont(Font.PLAIN, 24);

    public static ImageIcon instructionsImage = new ImageIcon(INSTRUCTIONS_IMAGE_PATH);
    public static ImageIcon navigationImage = new ImageIcon(NAVIGATE_IMAGE_PATH);
    public static ImageIcon conHallImage = new ImageIcon(CONHALL_EXT_IMAGE_PATH);
    public static ImageIcon conHallIntImage = new ImageIcon(CONHALL_INT_IMAGE_PATH);
    public static ImageIcon conHallExtImage = new ImageIcon(CONHALL_EXT_IMAGE_PATH);
    public static ImageIcon knoxExtImage = new ImageIcon(KNOX_EXT_IMAGE_PATH);
    public static ImageIcon knoxIntImage = new ImageIcon(KNOX_INT_IMAGE_PATH);
    public static ImageIcon gersteinIntImage = new ImageIcon(GERSTEIN_INT_IMAGE_PATH);
    public static ImageIcon gersteinExtImage = new ImageIcon(GERSTEIN_EXT_IMAGE_PATH);
    public static ImageIcon ucExtImage = new ImageIcon(UC_EXT_IMAGE_PATH);
    public static ImageIcon ucIntImage = new ImageIcon(UC_INT_IMAGE_PATH);

    public UISettings() throws IOException, FontFormatException {
        // insert code if needed
    }
}