package view;

import java.awt.*;
import javax.swing.*;

import interface_adapter.return_from_card.ReturnFromCardController;

public class ReturnFromCardDialogue extends JDialog {

    private ReturnFromCardController returnFromCardController;

    public ReturnFromCardDialogue(ReturnFromCardController returnFromCardController) {
        this.returnFromCardController = returnFromCardController;

        // When the controller wants the dialog shown
        this.returnFromCardController.setShowQuitDialog(() -> {
            ReturnFromCardDialogue dialog = new ReturnFromCardDialogue(returnFromCardController);
            dialog.showDialog();
        });

        setTitle("Quit Current Game?");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Do you confirm that you want to quit the current game?");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttons = new JPanel();
        JButton quitGame = new JButton("Quit");
        JButton cancel = new JButton("Cancel");

        quitGame.addActionListener(evt -> {
            dispose();
            returnFromCardController.execute();
        });

        cancel.addActionListener(evt -> dispose());

        buttons.add(quitGame);
        buttons.add(cancel);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void showDialog() {
        setVisible(true);
    }
}
