package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UISettings {
    public static final String QUINT_FONT_PATH = "/Users/vanessa.hanbao/Downloads/Quintessential/Quintessential-Regular.ttf";
    public static final String TEXTURINA_FONT_PATH = "/Users/vanessa.hanbao/Downloads/Quintessential,Texturina/Texturina/Texturina-VariableFont_opsz,wght.ttf";
    public static final String NAVIGATE_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/mapview.jpg";
    public static final String INSTRUCTIONS_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/Gemini_Generated_Image_1lcv901lcv901lcv.png";
    public static final String CONHALL_INT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/conhallint.jpg"
    public static final String CONHALL_EXT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/conhallext.png"
    public static final String KNOX_EXT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/knoxext.png";
    public static final String KNOX_INT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/KNOXINT.jpg";
    public static final String GERSTEIN_INT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/gersteinint.jpg";
    public static final String GERSTEIN_EXT_IMAGE_PATH = "/Users/vanessa.hanbao/Downloads/gersteinext.jpg";

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
    public static ImageIcon conHallImage = new ImageIcon(CONHALL_IMAGE_PATH);


    public UISettings() throws IOException, FontFormatException {
        // insert code if needed
    }
}
