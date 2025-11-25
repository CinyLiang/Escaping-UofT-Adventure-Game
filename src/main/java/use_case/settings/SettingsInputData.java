package use_case.settings;

public class SettingsInputData {
    public final String theme;
    public final String fontSize;
    public final boolean accessibility;

    public SettingsInputData(String theme, String fontSize, boolean accessibility) {
        this.theme = theme;
        this.fontSize = fontSize;
        this.accessibility = accessibility;
    }
}
