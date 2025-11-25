package use_case.settings;

public class SettingsInteractor implements SettingsInputBoundary {

    private final SettingsOutputBoundary presenter;

    public SettingsInteractor(SettingsOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveSettings(SettingsInputData data) {
        SettingsOutputData output = new SettingsOutputData(
                data.theme, data.fontSize, data.accessibility
        );
        presenter.applySettings(output);
    }
}