package view;

import java.awt.*;
import javax.swing.*;
import interface_adapter.quit_game.QuitGameController;

public class QuitGameDialog {
//    private static final String viewName = "quit";
    private QuitGameController quitGameController;

    private final JDialog dialog = new JDialog();

    public QuitGameDialog() {
        this.setQuitGameController(quitGameController);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        final JLabel title = new JLabel("Quit Game?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        JButton quitGame = new JButton("Quit");
        JButton cancel = new JButton("Cancel");

        quitGame.addActionListener(evt -> {
                dialog.dispose();
                quitGameController.showSave(); // show Save Game dialog
            }
        );

        cancel.addActionListener(evt -> dialog.dispose()
        );

        buttons.add(quitGame);
        buttons.add(cancel);
        dialog.add(title, BorderLayout.NORTH);
        dialog.add(buttons, BorderLayout.SOUTH);
    }

    public void show() {
        dialog.setVisible(true);
    }

//    public String getViewName() {
//        return viewName;
//    }

    // set controllers
    public void setQuitGameController(QuitGameController quitGameController) {
        this.quitGameController = quitGameController;

        this.quitGameController.setShowSaveDialog(() -> {
            // This code runs when the user clicks Quit → controller.showSave()
            SaveGameDialog saveDialog = new SaveGameDialog();
            saveDialog.show();
        });
    }
}
