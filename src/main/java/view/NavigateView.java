package view;

import javax.swing.*;
import view.QuitGameDialog;

import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.save_progress.SaveProgressController;
import interface_adapter.view_progress.ViewProgressController;
import view.QuitGameDialog;


import interface_adapter.quit_game.QuitGameController;

public class NavigateView extends javax.swing.JFrame {
    public NavigateView() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        createSaveProgressButton();
        createViewProgressButton();
    }
    // CONTROLLERS
    private QuitGameController quitGameController;
    private ClearHistoryController clearHistoryController;
    public static final String VIEW_NAME = "navigate_view";
    private SaveProgressController saveProgressController;
    private ViewProgressController viewProgressController;

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

    // SAVE PROGRESS BUTTON
    private void createSaveProgressButton() {
        JButton save = new JButton("Save");
        save.addActionListener(evt -> saveProgressController.execute());
        add(save);
    }

    // VIEW PROGRESS BUTTON
    private void createViewProgressButton() {
        JButton view = new JButton("View Progress");
        view.addActionListener(evt -> viewProgressController.execute());
        add(view);
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

    public void setSaveProgressController(SaveProgressController saveProgressController) {
        this.saveProgressController = saveProgressController;
    }

    public void setViewProgressController(ViewProgressController viewProgressController) {
        this.viewProgressController = viewProgressController;
    }
}