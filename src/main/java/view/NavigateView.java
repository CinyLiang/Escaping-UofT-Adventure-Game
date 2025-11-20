package view;

import javax.swing.*;
import view.QuitGameDialog;

import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.save_progress.SaveProgressController;
import view.QuitGameDialog;

import interface_adapter.quit_game.QuitGameController;

public class NavigateView extends javax.swing.JFrame {
    // CONTROLLERS
    private QuitGameController quitGameController;
    private ClearHistoryController clearHistoryController;

    // DIALOGS
    private QuitGameDialog quitGameDialog;
    private SaveGameDialog saveGameDialog;
    private ConfirmRestartGameDialog confirmRestartGameDialog;

    // QUIT BUTTON ; feel free move the code to wherever makes sense
    private void createQuitButton() {
        JButton quit = new JButton("Quit");
        quit.addActionListener(evt -> quitGameController.showQuit());
    }

    // CLEAR HISTORY BUTTON ; feel free move the code to wherever makes sense
    private void createClearHistoryButton() {
        JButton quit = new JButton("Restart");
        quit.addActionListener(evt -> clearHistoryController.showConfirm());
    }

    // QUIT GAME CONTROLLER
    public void setQuitGameController(QuitGameController quitGameController,
                                      SaveProgressController saveProgressController) {
        this.quitGameController = quitGameController;

        // set up runnable
        this.quitGameDialog = new QuitGameDialog(quitGameController, saveProgressController);
        this.saveGameDialog = new SaveGameDialog(saveProgressController);
        this.quitGameController.setShowQuitDialog(() -> quitGameDialog.show());
        this.quitGameController.setShowSaveDialog(() -> saveGameDialog.show());
    }

    // CLEAR GAME CONTROLLER
    public void setClearHistoryController(ClearHistoryController clearHistoryController) {
        this.clearHistoryController = clearHistoryController;

        // set up runnable
        this.confirmRestartGameDialog = new ConfirmRestartGameDialog(clearHistoryController);
        this.clearHistoryController.setShowConfirmDialog(() -> confirmRestartGameDialog.show());
    }
}