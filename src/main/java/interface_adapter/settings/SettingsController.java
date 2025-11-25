package interface_adapter.settings;

import use_case.settings.SettingsInputBoundary;
import use_case.settings.SettingsInputData;

public class SettingsController {

    private final SettingsInputBoundary interactor;

    public SettingsController(SettingsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void apply(String theme, String fontSize, boolean accessibility) {
        SettingsInputData data = new SettingsInputData(theme, fontSize, accessibility);
        interactor.saveSettings(data);
    }
}
