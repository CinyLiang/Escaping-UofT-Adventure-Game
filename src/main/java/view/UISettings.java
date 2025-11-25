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

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final java.awt.Color PARCHMENT_BACKGROUND = new java.awt.Color(245, 222, 179);
    public static final java.awt.Color ACCENT_COLOR = new java.awt.Color(139, 69, 19);
    public static final java.awt.Color HOVER_COLOR = new java.awt.Color(222, 184, 135);

    Font quintessentialBase = Font.createFont(Font.TRUETYPE_FONT, new File(QUINT_FONT_PATH));
    Font quintessential = quintessentialBase.deriveFont(Font.PLAIN, 24);

    Font texturinaBase = Font.createFont(Font.TRUETYPE_FONT, new File(TEXTURINA_FONT_PATH));
    Font texturina = texturinaBase.deriveFont(Font.PLAIN, 24);

    ImageIcon instructionsImage = new ImageIcon(INSTRUCTIONS_IMAGE_PATH);
    ImageIcon navigationImage = new ImageIcon(NAVIGATE_IMAGE_PATH);


    public UISettings() throws IOException, FontFormatException {
        // insert code if needed
    }
}
