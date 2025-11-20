package view;

import interface_adapter.save_progress.SaveProgressController;

import javax.swing.*;
import java.awt.*;

public class SaveGameDialog {
//    private static final String viewName = "save game";
    private SaveProgressController saveProgressController;

    private final JDialog dialog = new JDialog();

    public SaveGameDialog() {
        this.setSaveGameController(saveProgressController);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        final JLabel title = new JLabel("Save game before quitting?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        JButton quitAndSave = new JButton("Save and quit");
        JButton quitDontSave = new JButton("Quit without saving");

        quitAndSave.addActionListener(evt -> {
                saveProgressController.execute();
                closeGame();
            }
        );

        quitDontSave.addActionListener(evt -> closeGame()
        );

        buttons.add(quitAndSave);
        buttons.add(quitDontSave);
        dialog.add(title, BorderLayout.NORTH);
        dialog.add(buttons, BorderLayout.SOUTH);
    }

    private void closeGame() {
        dialog.dispose();
        System.exit(0);
    }

    public void show() {
        dialog.setVisible(true);
    }

//    public String getViewName() {
//        return viewName;
//    }

    public void setSaveGameController(SaveProgressController saveProgressController) {
        this.saveProgressController = saveProgressController;
        // this will be updated in case the saveProgressController needs more stuff to function.
    }
}
