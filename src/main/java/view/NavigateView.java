package view;

import javax.swing.*;
import view.QuitGameDialog;

import interface_adapter.quit_game.QuitGameController;

public class NavigateView extends javax.swing.JFrame {
    // QUIT GAME CONTROLLER
    private QuitGameController quitGameController;

    // QUIT + SAVE GAME DIALOG
    private QuitGameDialog quitGameDialog;
    private SaveGameDialog saveGameDialog;

    // QUIT BUTTON ; feel free move the code to wherever makes sense
    private void createQuitButton() {
        JButton quit = new JButton("Quit");
        quit.addActionListener(evt -> quitGameController.showQuit());
    }

    // QUIT GAME CONTROLLER
    public void setQuitGameController(QuitGameController quitGameController) {
        this.quitGameController = quitGameController;

        // set up runnable
        this.quitGameDialog = new QuitGameDialog();
        this.saveGameDialog = new SaveGameDialog();
        this.quitGameController.setShowQuitDialog(() -> quitGameDialog.show());
        this.quitGameController.setShowSaveDialog(() -> saveGameDialog.show());
    }
}