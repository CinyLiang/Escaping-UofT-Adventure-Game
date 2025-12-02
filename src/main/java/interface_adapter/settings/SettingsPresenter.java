package interface_adapter.settings;

import interface_adapter.ViewManagerModel;
import use_case.settings.SettingsOutputBoundary;
import use_case.settings.SettingsOutputData;
import view.theme.ThemeManager;

public class SettingsPresenter implements SettingsOutputBoundary {

    private final SettingsViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public SettingsPresenter(SettingsViewModel viewModel,
                             ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void applySettings(SettingsOutputData data) {

        // Update view model
        viewModel.setTheme(data.theme);
        viewModel.setFontSize(data.fontSize);
        viewModel.setAccessibility(data.accessibility);

        ThemeManager.applyTheme(data.theme);

        viewManagerModel.firePropertyChange();
    }
}
