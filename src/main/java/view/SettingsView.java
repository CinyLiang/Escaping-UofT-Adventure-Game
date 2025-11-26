package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.settings.SettingsController;
import interface_adapter.settings.SettingsViewModel;
import view.components.OptionSelector;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends JPanel {

    public static final String VIEW_NAME = "settings_view";

    // UofT colour palette
    private final Color UOFT_BLUE = new Color(0, 33, 71);
    private final Color UOFT_GOLD = new Color(255, 203, 5);

    public SettingsView(SettingsController controller,
                        SettingsViewModel settingsViewModel,
                        ViewManagerModel viewManagerModel) {

        setLayout(new BorderLayout());
        setBackground(UOFT_BLUE);

        // view.ThemeManager
        JLabel title = new JLabel("SETTINGS", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 38));
        title.setForeground(UOFT_GOLD);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // █████ MAIN OPTIONS PANEL █████
        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new GridLayout(3, 1, 25, 25));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 20, 80));

        // THEME SELECTOR
        OptionSelector themeSelector = new OptionSelector(
                "Theme",
                new String[]{"Light", "Dark"},
                settingsViewModel.getTheme().equals("dark") ? 1 : 0
        );

        // FONT SIZE
        int fontIndex = switch (settingsViewModel.getFontSize()) {
            case "small" -> 0;
            case "large" -> 2;
            default -> 1;
        };

        OptionSelector fontSelector = new OptionSelector(
                "Text Size",
                new String[]{"Small", "Medium", "Large"},
                fontIndex
        );

        // ACCESSIBILITY
        OptionSelector accessSelector = new OptionSelector(
                "Accessibility",
                new String[]{"Off", "On"},
                settingsViewModel.isAccessibility() ? 1 : 0
        );

        optionsPanel.add(themeSelector);
        optionsPanel.add(fontSelector);
        optionsPanel.add(accessSelector);

        add(optionsPanel, BorderLayout.CENTER);

        // █████ BOTTOM BUTTONS █████
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));

        JButton saveButton = makeButton("Save");
        JButton backButton = makeButton("Back");

        buttonsPanel.add(saveButton);
        buttonsPanel.add(backButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        // █████ BUTTON ACTIONS █████
        saveButton.addActionListener(e -> {
            controller.save(
                    themeSelector.getValue().toLowerCase(),
                    fontSelector.getValue().toLowerCase(),
                    accessSelector.getValue().equals("On")
            );

            // Return to home
            viewManagerModel.setState(HomeView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });

        backButton.addActionListener(e -> {
            viewManagerModel.setState(HomeView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });
    }

    /**
     * Styles a UofT-themed button.
     */
    private JButton makeButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setBackground(UOFT_GOLD);
        button.setForeground(UOFT_BLUE);

        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));

        return button;
    }
}
