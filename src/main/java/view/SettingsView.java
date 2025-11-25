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
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Center menu
        JPanel menu = new JPanel(new GridLayout(3, 2, 20, 20));
        menu.setOpaque(false);
        menu.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        // Theme
        JLabel themeLabel = makeLabel("Theme");
        OptionSelector themeSelector = new OptionSelector(
                new String[]{"Light", "Dark"},
                settingsViewModel.getTheme().equals("dark") ? 1 : 0
        );

        // Font
        JLabel fontLabel = makeLabel("Text Size");
        OptionSelector fontSelector = new OptionSelector(
                new String[]{"Small", "Medium", "Large"},
                switch (settingsViewModel.getFontSize()) {
                    case "small" -> 0;
                    case "large" -> 2;
                    default -> 1;
                }
        );

        // Accessibility
        JLabel accessLabel = makeLabel("Accessibility");
        OptionSelector accessSelector = new OptionSelector(
                new String[]{"Off", "On"},
                settingsViewModel.isAccessibility() ? 1 : 0
        );

        menu.add(themeLabel);
        menu.add(themeSelector);
        menu.add(fontLabel);
        menu.add(fontSelector);
        menu.add(accessLabel);
        menu.add(accessSelector);

        add(menu, BorderLayout.CENTER);

        // Buttons bottom
        JPanel buttons = new JPanel();
        buttons.setOpaque(false);

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");

        styleButton(saveButton);
        styleButton(backButton);

        buttons.add(saveButton);
        buttons.add(backButton);

        add(buttons, BorderLayout.SOUTH);

        // Button actions
        saveButton.addActionListener(e -> {
            controller.apply(
                    themeSelector.getValue().toLowerCase(),
                    fontSelector.getValue().toLowerCase(),
                    accessSelector.getValue().equals("On")
            );
            viewManagerModel.setState(HomeView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });

        backButton.addActionListener(e -> {
            viewManagerModel.setState(HomeView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });
    }

    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        return label;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
    }
}
