package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.settings.SettingsController;
import interface_adapter.settings.SettingsViewModel;
import view.components.OptionSelector;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends JPanel {

    public static final String VIEW_NAME = "settings_view";

    public SettingsView(SettingsController controller,
                        SettingsViewModel settingsViewModel,
                        ViewManagerModel viewManagerModel) {

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Title
        JLabel title = new JLabel("SETTINGS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        add(title, BorderLayout.NORTH);

        // MAIN CENTER PANEL
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 1, 0, 20));
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        // Theme
        OptionSelector themeSelector = new OptionSelector(
                "Theme",
                new String[]{"Light", "Dark"},
                settingsViewModel.getTheme().equals("dark") ? 1 : 0
        );

        // Font Size
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

        // Accessibility
        OptionSelector accessibilitySelector = new OptionSelector(
                "Accessibility",
                new String[]{"Off", "On"},
                settingsViewModel.isAccessibility() ? 1 : 0
        );

        center.add(themeSelector);
        center.add(fontSelector);
        center.add(accessibilitySelector);

        add(center, BorderLayout.CENTER);

        // BOTTOM Button
        JPanel bottomButtons = new JPanel();
        bottomButtons.setOpaque(false);
        bottomButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");
    }
}