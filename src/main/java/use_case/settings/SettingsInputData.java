package use_case.settings;

public class SettingsInputData {

    private final String theme;
    private final String fontSize;
    private final boolean accessibility;

    public SettingsInputData(String theme, String fontSize, boolean accessibility) {
        this.theme = theme;
        this.fontSize = fontSize;
        this.accessibility = accessibility;
    }

    public String getTheme() {
        return theme;
    }

    public String getFontSize() {
        return fontSize;
    }

    public boolean isAccessibility() {
        return accessibility;
    }
}
