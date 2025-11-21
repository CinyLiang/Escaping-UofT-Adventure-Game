package view;

import java.awt.*;
import javax.swing.*;

import interface_adapter.quit_game.QuitGameController;
import interface_adapter.return_from_card.ReturnFromCardController;
import interface_adapter.save_progress.SaveProgressController;

public class ReturnFromCardDialogue {
    private ReturnFromCardController returnFromCardController;
    private final JDialog dialog = new JDialog();

    public ReturnFromCardDialogue(ReturnFromCardController returnFromCardController) {
        this.setReturnFromCardControllerController(returnFromCardController);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        final JLabel title = new JLabel("Do you confirm that you want to quit the current game??");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        JButton quitGame = new JButton("Quit");
        JButton cancel = new JButton("Cancel");

        quitGame.addActionListener(evt -> {
            dialog.dispose();
            returnFromCardController.execute();
        });

        cancel.addActionListener(evt -> dialog.dispose()
        );

        buttons.add(quitGame);
        buttons.add(cancel);
        dialog.add(title, BorderLayout.NORTH);
        dialog.add(buttons, BorderLayout.SOUTH);
    }

    public void show() {
        this.dialog.setVisible(true);
    }

    public void setReturnFromCardControllerController(ReturnFromCardController returnFromCardController) {
        this.returnFromCardController = returnFromCardController;

        this.returnFromCardController.setShowQuitDialog(() -> {
            // This code runs when the user clicks Quit → controller.showSave()
            ReturnFromCardDialogue returnDialog = new ReturnFromCardDialogue(returnFromCardController);
            returnDialog.show();
        });
}
}
