package app;

import data_access.FileGameDataAccessObject;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Could not set look and feel: " + e.getMessage());
            }

            FileGameDataAccessObject dataAccess = new FileGameDataAccessObject("game_save.json");

            AppBuilder appBuilder = new AppBuilder();
            JFrame app = appBuilder.build(dataAccess);

            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}