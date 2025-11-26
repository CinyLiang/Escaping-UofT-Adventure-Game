package view;

import java.awt.*;

public class ThemeManager {

    private static String currentTheme = "dark";
    private static String currentFontSize = "medium";
    private static boolean accessibility = false;

    // UofT Colors
    private static final Color UOFT_BLUE = new Color(0, 33, 71);
    private static final Color UOFT_LIGHT_BLUE = new Color(221, 231, 243);
    private static final Color UOFT_GOLD = new Color(255, 203, 5);

    public static void applyTheme(String theme) {
        currentTheme = theme.toLowerCase();
    }

    public static String getCurrentTheme() {
        return currentTheme;
    }
    public static Color getBackground() {
        return switch (currentTheme) {
            case "light" -> UOFT_LIGHT_BLUE;
            default -> UOFT_BLUE;   // dark mode background
        };
    }

    public static Color getTextPrimary() {
        return switch (currentTheme) {
            case "light" -> UOFT_BLUE;
            default -> UOFT_GOLD;
        };
    }

    public static Color getButtonBackground() {
        return switch (currentTheme) {
            case "light" -> Color.WHITE;
            default -> UOFT_BLUE;
        };
    }

    public static Color getButtonForeground() {
        return switch (currentTheme) {
            case "light" -> UOFT_BLUE;
            default -> UOFT_GOLD;
        };
    }

    // Font Size
    public static void applyFontSize(String size) {
        currentFontSize = size.toLowerCase();
    }

    public static int getFontSize(int base) {
        return switch (currentFontSize) {
            case "small" -> (int)(base * 0.8);
            case "large" -> (int)(base * 1.25);
            default -> base;
        };
    }

    // Accessibility
    public static void setAccessibility(boolean on) {
        accessibility = on;
    }

    public static boolean isHighContrast() {
        return accessibility;
    }
}
