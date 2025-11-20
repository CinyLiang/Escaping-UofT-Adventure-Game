package view;

import interface_adapter.quit_game.QuitGameController;
import interface_adapter.save_progress.SaveProgressController;

import javax.swing.*;
import java.awt.*;

public class SaveGameDialog {
    private final String viewName = "quit";
    private SaveProgressController saveProgressController;

    private final JButton quitAndSave;
    private final JButton quitDontSave;

    private final JDialog saveGameDialog = new JDialog();

    public SaveGameDialog() {
        final JLabel title = new JLabel("Save game before quitting?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        quitAndSave = new JButton("Save and quit");
        quitDontSave = new JButton("Quit without saving");

        quitAndSave.addActionListener(
                evt -> {
                    saveProgressController.execute();
                    System.exit(0);
                }
        );

        quitDontSave.addActionListener(
                evt -> {
                    System.exit(0);
                }
        );

        buttons.add(quitAndSave);
        buttons.add(quitDontSave);
        saveGameDialog.add(title, BorderLayout.NORTH);
        saveGameDialog.add(buttons, BorderLayout.SOUTH);
    }

    public void show() {
        saveGameDialog.setVisible(true);
    }

    public String getViewName() {
        return viewName;
    }

    public void setSaveGameController(SaveProgressController saveProgressController) {
        this.saveProgressController = saveProgressController;
    }
}
