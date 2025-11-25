package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Could not set look and feel: " + e.getMessage());
            }

            AppBuilder appBuilder = new AppBuilder();
            JFrame app = appBuilder.build("game_save.txt");


            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}