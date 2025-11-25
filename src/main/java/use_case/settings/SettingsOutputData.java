package use_case.settings;

public class SettingsOutputData {
    public final String theme;
    public final String fontSize;
    public final boolean accessibility;

    public SettingsOutputData(String theme, String fontSize, boolean accessibility) {
        this.theme = theme;
        this.fontSize = fontSize;
        this.accessibility = accessibility;
    }
}
