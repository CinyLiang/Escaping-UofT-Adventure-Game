package view.theme;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ThemeManager {

    private static String currentTheme = "dark";  // default
    private static String currentFontSize = "medium";
    private static boolean accessibility = false;

    // Property change support for notifying views
    private static final PropertyChangeSupport pcs =
            new PropertyChangeSupport(ThemeManager.class);

    // DARK MODE
    private static final Color DARK_BG = Color.BLACK;
    private static final Color DARK_RED_ACCENT = new Color(127, 0, 0);
    private static final Color DARK_TEXT = new Color(255, 82, 82);

    private static final Color BUTTON_DARK_BG = new Color(127, 0, 0);
    private static final Color BUTTON_DARK_TEXT = new Color(255, 82, 82);

    // LIGHT MODE
    private static final Color LIGHT_BG = new Color(255, 240, 240);
    private static final Color LIGHT_PANEL = new Color(255, 229, 229);
    private static final Color LIGHT_TEXT = new Color(192, 57, 43);
    private static final Color BUTTON_LIGHT_BG = new Color(255, 247, 247);
    private static final Color BUTTON_LIGHT_TEXT = LIGHT_TEXT;

    // APPLY THEME + NOTIFY
    public static void applyTheme(String theme) {
        String oldTheme = currentTheme;
        currentTheme = theme.toLowerCase();
        pcs.firePropertyChange("theme", oldTheme, currentTheme);
    }

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void addThemeChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public static void removeThemeChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public static Color getBackground() {
        return currentTheme.equals("light") ? LIGHT_BG : DARK_BG;
    }

    public static Color getTextPrimary() {
        return currentTheme.equals("light") ? LIGHT_TEXT : DARK_TEXT;
    }

    public static Color getPanelBackground() {
        return currentTheme.equals("light") ? LIGHT_PANEL : DARK_BG;
    }

    public static Color getButtonBackground() {
        return currentTheme.equals("light") ? BUTTON_LIGHT_BG : BUTTON_DARK_BG;
    }

    public static Color getButtonForeground() {
        return currentTheme.equals("light") ? BUTTON_LIGHT_TEXT : BUTTON_DARK_TEXT;
    }

    public static void applyFontSize(String size) {
        currentFontSize = size.toLowerCase();
        pcs.firePropertyChange("fontSize", null, currentFontSize);
    }

    public static int getFontSize(int base) {
        return switch (currentFontSize) {
            case "small" -> (int) (base * 0.8);
            case "large" -> (int) (base * 1.25);
            default -> base;
        };
    }

    public static void setAccessibility(boolean on) {
        boolean old = accessibility;
        accessibility = on;
        pcs.firePropertyChange("accessibility", old, on);
    }

    public static boolean isHighContrast() {
        return accessibility;
    }
}
