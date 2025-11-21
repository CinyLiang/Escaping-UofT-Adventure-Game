package view;

import javax.swing.*;

import interface_adapter.navigate.NavigateController;
import view.QuitGameDialog;

import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.save_progress.SaveProgressController;
import interface_adapter.view_progress.ViewProgressController;
import view.QuitGameDialog;

import interface_adapter.quit_game.QuitGameController;

import java.awt.event.ActionListener;

public class NavigateView extends javax.swing.JFrame {
    public NavigateView() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        createSaveProgressButton();
        createViewProgressButton();

        // MAIN VIEW
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout (mainPanel, BoxLayout.Y_AXIS));

        // title
        JLabel title = new JLabel("Story");
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(title);

        // story text
        JTextArea storyTextArea = new JTextArea(10, 40);
        storyTextArea.setText("");
        storyTextArea.setEditable(true);

        // action dropdown
        JPanel actionPanel = new JPanel();
        String[] actions = {"Go North", "Go South", "Go East", "Go West"};
        JComboBox<String> actionDropdown = new JComboBox<>(actions);

        JButton actionButton = new JButton(">");
        actionPanel.add(actionDropdown);
        actionPanel.add(actionButton);
        mainPanel.add(actionPanel);

        // bottom buttons
        JButton saveButton = new JButton("Save");
        JButton viewProgressButton = new JButton("View Progress");
        JButton quitButton = new JButton ("Quit");
    }

    // CONTROLLERS
    private QuitGameController quitGameController;
    private ClearHistoryController clearHistoryController;
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

    // ACTION LISTENERS
    public void setNavigateController(NavigateController navigateController) {
           this.navigateController = navigateController;

           actionButton.addActionListener(evt -> {
               String selectedAction = (String) actionDropdown.getSelectedItem();
               if (selectedAction != null && selectedAction.startsWith("Go ")) {
                   String direction = selectedAction.split(" ")[1]; // Extract direction
                   navigateController.execute(direction);
               } else {
                   JOptionPane.showMessageDialog(
                           null,
                           "You stand still, unsure where to go.");
               }
           });
    }
}