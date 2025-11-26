package interface_adapter.settings;

import use_case.settings.SettingsInputBoundary;
import use_case.settings.SettingsInputData;
import view.ThemeManager;

public class SettingsController {

    private final SettingsInputBoundary interactor;

    public SettingsController(SettingsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void save(String theme, String font, boolean accessibility) {

        // Apply to ThemeManager
        ThemeManager.applyTheme(theme);
        ThemeManager.applyFontSize(font);
        ThemeManager.setAccessibility(accessibility);

        SettingsInputData data = new SettingsInputData(theme, font, accessibility);
        interactor.saveSettings(data);
    }
}
