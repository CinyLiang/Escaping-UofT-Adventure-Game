package view;

import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.clear_history.ClearHistoryViewModel;
import interface_adapter.navigate.NavigateController;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.save_progress.SaveProgressController;
import interface_adapter.view_progress.ViewProgressController;
import interface_adapter.quit_game.QuitGameController;

import view.QuitGameDialog;

import javax.swing.*;
import java.awt.*;

public class NavigateView extends JPanel {

    public static final String VIEW_NAME = "navigate_view";

    private JTextArea storyArea;
    private JComboBox<String> directionSelector;

    private JButton restartButton;
    private JButton progressButton;
    private JButton saveButton;
    private JButton quitButton;

    private JLabel keysLabel;

    private QuitGameDialog quitDialog;

    // Controllers
    private NavigateController navigateController;
    private ClearHistoryController clearHistoryController;
    private SaveProgressController saveController;
    private ViewProgressController viewProgressController;
    private QuitGameController quitGameController;

    // ViewModels
    private NavigateViewModel navigateViewModel;
    private ClearHistoryViewModel clearHistoryViewModel;

    public NavigateView() {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        quitDialog = new QuitGameDialog();

        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setBackground(Color.BLACK);

        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        statusBar.setOpaque(false);

        keysLabel = new JLabel("Keys: 0 / 3");
        keysLabel.setForeground(Color.WHITE);
        keysLabel.setFont(new Font("Arial", Font.BOLD, 18));

        statusBar.add(keysLabel);
        topSection.add(statusBar, BorderLayout.NORTH);

        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setBackground(new Color(40, 40, 40));
        storyArea.setForeground(Color.WHITE);
        storyArea.setFont(new Font("Serif", Font.PLAIN, 22));
        storyArea.setText("Welcome to Escaping UofT!\nSelect a direction to begin...");

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
        dirLabel.setFont(new Font("Arial", Font.BOLD, 18));

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
            if (viewProgressController != null)
                viewProgressController.execute();
        });

        saveButton.addActionListener(e -> {
            if (saveController != null)
                saveController.execute();
        });

        quitButton.addActionListener(e -> {
            if (quitDialog != null) quitDialog.show();
        });

        directionSelector.addActionListener(e -> {
            if (navigateController != null) {
                navigateController.execute((String) directionSelector.getSelectedItem());
            }
        });
    }

    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(70, 70, 70));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 16));
        return b;
    }

    public void setNavigateController(NavigateController c) {
        this.navigateController = c;
    }

    // Quit dialog
    public void setQuitGameDialog(QuitGameDialog quitDialog) {
        this.quitDialog = quitDialog;
    }

    // Restart controller
    public void setRestartController(ClearHistoryController controller) {
        restartButton.addActionListener(e -> controller.execute());
    }

    // Progress controller
    public void setViewProgressController(ViewProgressController controller) {
        progressButton.addActionListener(e -> controller.execute());
    }

    // Save controller
    public void setSaveProgressController(SaveProgressController controller) {
        saveButton.addActionListener(e -> controller.execute());
    }

    public void setNavigateViewModel(NavigateViewModel vm) {
        this.navigateViewModel = vm;

        vm.addPropertyChangeListener(evt -> {
            var state = vm.getState();
            storyArea.setText(state.getStoryText());
            keysLabel.setText("Keys: " + state.getNumberOfKeys() + " / 3");
        });
    }

    public void setClearHistoryViewModel(ClearHistoryViewModel vm) {
        this.clearHistoryViewModel = vm;

        vm.addPropertyChangeListener(evt ->
                JOptionPane.showMessageDialog(this, vm.getMessage())
        );
    }

    public void setClearHistoryController(ClearHistoryController controller) {
        this.clearHistoryController = controller;
    }

    public void setQuitGameController(QuitGameController quitController,
                                      SaveProgressController saveController) {

        this.quitGameController = quitController;
        this.saveController = saveController;

        this.quitDialog = new QuitGameDialog(quitController, saveController);

        quitButton.addActionListener(e -> quitDialog.show());
    }
}
