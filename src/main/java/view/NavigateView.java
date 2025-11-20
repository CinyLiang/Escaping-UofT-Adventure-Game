package view;

import javax.swing.*;
import view.QuitGameDialog;

public class NavigateView extends javax.swing.JFrame {
    // QUIT GAME "POP UP"/DIALOG
    private QuitGameDialog quitDialog;

    public NavigateView() {
        this.quitDialog = new QuitGameDialog();
    }

    // QUIT BUTTON ; feel free move the code to wherever makes sense
    private void createQuitButton() {
        JButton quit = new JButton("Quit");
        quit.addActionListener(
                evt -> quitDialog.show()
        );
    }
}