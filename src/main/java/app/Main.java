package app;

import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    /**
     * The main class.
     * @param args .
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::runMain);
    }

    private static void runMain() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException exception) {
            System.err.printf("Could not set look and feel: %s%n", exception.getMessage());
        }
        catch (InstantiationException exception) {
            System.err.printf("Could not set look and feel: %s%n", exception.getMessage());
        }
        catch (IllegalAccessException exception) {
            System.err.println("Could not set look and feel: " + exception.getMessage());
        }
        catch (UnsupportedLookAndFeelException exception) {
            System.err.println("Could not set look and feel: " + exception.getMessage());
        }
        final AppBuilder appBuilder = new AppBuilder();
        JFrame app = null;
        try {
            app = appBuilder.build("game_save.json");
        }
        catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        catch (FontFormatException fontFormatException) {
            throw new RuntimeException(fontFormatException);
        }
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}
