package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.interface_adapter.quit_game.QuitGameController;
//import main.java.interface_adapter.quit_game.QuitGamePresenter;

public class QuitGameDialog {
    private final String viewName = "quit";
    private QuitGameController quitGameController;

    private final JButton quitGame;
    private final JButton cancel;

    private static final JDialog quitGameDialog = new JDialog();

    public QuitGameDialog() {
        final JLabel title = new JLabel("Quit Game?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        quitGame = new JButton("Quit");
        cancel = new JButton("Cancel");

        quitGame.addActionListener(
                evt -> {
                    closeDialog();
                    quitGameController.execute();
                }
        );

        cancel.addActionListener(
                evt -> {
                    closeDialog(); // for now. not sure if this actually works imo
//                        dispose();
                }
        );

        buttons.add(quitGame);
        buttons.add(cancel);
        quitGameDialog.add(title, BorderLayout.NORTH);
        quitGameDialog.add(buttons, BorderLayout.SOUTH);
    }

    public void show() {
        quitGameDialog.setVisible(true);
    }

    public void closeDialog() {
        quitGameDialog.dispose();
//        quitGameDialog.setVisible(false);
    }

//    public void actionPerformed(ActionEvent e) {
//        dispose();
//    }

    public String getViewName() {
        return viewName;
    }

    // set controllers
    public void setQuitGameController(QuitGameController quitGameController) {
        this.quitGameController = quitGameController;
    }
}
