package view;

import javax.swing.*;

import interface_adapter.navigate.NavigateController;
import interface_adapter.clear_history.ClearHistoryViewModel;

import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.save_progress.SaveProgressController;
import interface_adapter.view_progress.ViewProgressController;

import interface_adapter.quit_game.QuitGameController;
import interface_adapter.win_game.WinGameController;

import java.awt.*;

public class NavigateView extends JPanel {
    private ClearHistoryViewModel clearHistoryViewModel;

    public static final String VIEW_NAME = "navigate_view";

    // CONTROLLERS
    private QuitGameController quitGameController;
    private ClearHistoryController clearHistoryController;
    private SaveProgressController saveProgressController;
    private ViewProgressController viewProgressController;
    private NavigateController navigateController;
    private WinGameController winGameController;

    // VIEW MODEL
    private NavigateViewModel  navigateViewModel;

    // DIALOGS
    private QuitGameDialog quitGameDialog;
    private SaveGameDialog saveGameDialog;
    private ConfirmRestartGameDialog confirmRestartGameDialog;

    // NAV UI
    private JTextArea storyArea;
    private JComboBox<String> directionSelector;

    private JButton restartButton;
    private JButton progressButton;
    private JButton saveButton;
    private JButton quitButton;

    private JLabel keysLabel;

    private static final String FONT = "Arial";

    public NavigateView(NavigateViewModel navigateViewModel) {
        this.navigateViewModel = navigateViewModel;

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setBackground(Color.BLACK);

        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        statusBar.setOpaque(false);

        // init text
        keysLabel = new JLabel("Keys: 0 / 2");
        storyArea = new JTextArea("Welcome to Escaping UofT!\nSelect a direction to begin...");

        // set up important text
        if (navigateViewModel != null) {
            keysLabel.setText("Keys: "+ navigateViewModel.getState().getNumberOfKeys() + " / 2");
            storyArea.setText("Welcome to Escaping UofT!\nSelect a direction to begin...");

            // actually calls on change lmao. did not know that.
            navigateViewModel.addPropertyChangeListener(evt -> {
                NavigateState s = navigateViewModel.getState();
                storyArea.setText(s.getStoryText());
                keysLabel.setText("Keys: " + s.getNumberOfKeys() + " / 2");
            });
        } else {
            System.out.println("navigationViewModel is null");
        }

        // styling
        keysLabel.setForeground(Color.WHITE);
        keysLabel.setFont(new Font(FONT, Font.BOLD, 18));
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setBackground(new Color(40, 40, 40));
        storyArea.setForeground(Color.WHITE);
        storyArea.setFont(new Font("Serif", Font.PLAIN, 22));

        // structure
        statusBar.add(keysLabel);
        topSection.add(statusBar, BorderLayout.NORTH);

        JScrollPane storyScroll = new JScrollPane(storyArea);
        storyScroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        storyScroll.setPreferredSize(new Dimension(700, 300));

        topSection.add(storyScroll, BorderLayout.CENTER);

        this.add(topSection, BorderLayout.NORTH);

        JPanel centerContainer = new JPanel();
        centerContainer.setOpaque(false);
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));

        centerContainer.add(Box.createVerticalGlue());

        JPanel floatingBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        floatingBox.setOpaque(true);
        floatingBox.setBackground(new Color(20, 20, 20, 90));
        floatingBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel dirLabel = new JLabel("Choose Direction:");
        dirLabel.setForeground(Color.WHITE);
        dirLabel.setFont(new Font(FONT, Font.BOLD, 18));

        directionSelector = new JComboBox<>(new String[]{
                "North", "South", "East", "West"
        });
        directionSelector.setPreferredSize(new Dimension(100, 28));
        directionSelector.setFocusable(false);

        floatingBox.add(dirLabel);
        floatingBox.add(directionSelector);

        centerContainer.add(floatingBox);
        centerContainer.add(Box.createVerticalGlue());

        this.add(centerContainer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        bottomPanel.setBackground(Color.BLACK);

        restartButton = makeButton("Restart");
        progressButton = makeButton("Progress");
        saveButton = makeButton("Save");
        quitButton = makeButton("Quit");

        bottomPanel.add(restartButton);
        bottomPanel.add(progressButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(quitButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        restartButton.addActionListener(e -> {
            if (clearHistoryController != null) {
                clearHistoryController.showConfirm();
            }
        });

        progressButton.addActionListener(e -> {
            if (viewProgressController != null) {
                NavigateState state = navigateViewModel.getState();
                viewProgressController.execute(state.getLocation(), state.getNumberOfKeys(), state.getPuzzlesSolved());
                // show dialog
                JDialog progressDialog = new ProgressDialog(state.getProgressText());
                progressDialog.setVisible(true);
            }
        });

        saveButton.addActionListener(e -> {
            NavigateState s = navigateViewModel.getState();
            if (saveProgressController != null)
                saveProgressController.execute(s.getLocation(), s.getNumberOfKeys(), s.getPuzzlesSolved());
        });

        quitButton.addActionListener(e -> {
            if (quitGameController != null) {
                quitGameController.showQuit();
            }
        });

        directionSelector.addActionListener(e -> {
            if (navigateController != null) {
                System.out.println("selected direction: " + directionSelector.getSelectedItem());
                navigateController.execute((String) directionSelector.getSelectedItem());
            }
        });
    }

    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(70, 70, 70));
        b.setForeground(Color.WHITE);
        b.setFont(new Font(FONT, Font.BOLD, 16));
        return b;
    }

    // QUIT GAME CONTROLLER
    public void setQuitGameController(QuitGameController quitGameController,
                                      SaveProgressController saveProgressController) {
        this.quitGameController = quitGameController;

        // set up runnable
        this.quitGameDialog = new QuitGameDialog(quitGameController, saveProgressController, navigateViewModel);
        this.saveGameDialog = new SaveGameDialog(saveProgressController, navigateViewModel);
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

    // SAVE PROGRESS CONTROLLER
    public void setSaveProgressController(SaveProgressController saveProgressController) {
        this.saveProgressController = saveProgressController;
    }

    // VIEW PROGRESS CONTROLLER
    public void setViewProgressController(ViewProgressController viewProgressController) {
        this.viewProgressController = viewProgressController;
    }

    // WIN GAME CONTROLLER
    public void setWinGameController(WinGameController winGameController) {
        this.winGameController = winGameController;
    }

    // ACTION LISTENERS
    public void setNavigateController(NavigateController navigateController) {
           this.navigateController = navigateController;
    }

    public void setClearHistoryViewModel(ClearHistoryViewModel vm) {
        this.clearHistoryViewModel = vm;
        vm.addPropertyChangeListener(evt -> JOptionPane.showMessageDialog(this, vm.getMessage()));
    }
}
