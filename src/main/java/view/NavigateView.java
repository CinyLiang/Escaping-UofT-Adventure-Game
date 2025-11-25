package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import interface_adapter.navigate.NavigateController;
import interface_adapter.clear_history.ClearHistoryViewModel;
import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.save_progress.SaveProgressController;
import interface_adapter.view_progress.ViewProgressController;
import interface_adapter.quit_game.QuitGameController;
import interface_adapter.win_game.WinGameController;
import view.UISettings;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class NavigateView extends JPanel {
    private ClearHistoryViewModel clearHistoryViewModel;

    public static final String VIEW_NAME = "navigate_view";

    // MAP IMAGES + FONT
    private JLabel mainMapLabel;

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

    public NavigateView(NavigateViewModel navigateViewModel) throws IOException, FontFormatException {
        this.navigateViewModel = navigateViewModel;

        this.setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        keysLabel = new JLabel("Keys: 0 / 2");
        storyArea = new JTextArea("You find yourself in a dimly lit room in University College. " +
                "The air is thick with mystery, and the walls whisper secrets of the past. " +
                "Navigate through the campus, solve puzzles, and collect keys to unlock Convocation Hall " +
                "before time runs out. Choose your directions wisely, for each path holds its own " +
                "challenges and surprises. Good luck, adventurer!");

        // set up important text
        if (navigateViewModel != null) {
            keysLabel.setText("Keys: "+ navigateViewModel.getState().getNumberOfKeys() + " / 2");

            navigateViewModel.addPropertyChangeListener(evt -> {
                NavigateState s = navigateViewModel.getState();
                storyArea.setText(s.getStoryText());
                keysLabel.setText("Keys: " + s.getNumberOfKeys() + " / 2");
            });
        } else {
            System.out.println("navigationViewModel is null");
        }

        // status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        statusBar.setBackground(UISettings.PARCHMENT_BACKGROUND);
        statusBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UISettings.ACCENT_COLOR));

        keysLabel.setForeground(UISettings.ACCENT_COLOR);
        keysLabel.setFont(UISettings.quintessential);
        statusBar.add(keysLabel);

        this.add(statusBar, BorderLayout.NORTH);

        // scroll pane containing center content
        // map, story, and selector all in here
        JPanel scrollableContent = new JPanel();
        scrollableContent.setLayout(new BoxLayout(scrollableContent, BoxLayout.Y_AXIS));
        scrollableContent.setBackground(UISettings.PARCHMENT_BACKGROUND);
        scrollableContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Overall padding

        // map image
        ImageIcon originalMap = UISettings.navigationImage;
        int newWidth = 500;
        int newHeight = (originalMap.getIconHeight() * newWidth) / originalMap.getIconWidth();
        Image scaledMapImage = originalMap.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledMapIcon = new ImageIcon(scaledMapImage);

        mainMapLabel = new JLabel(scaledMapIcon);
        mainMapLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMapLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollableContent.add(mainMapLabel);
        scrollableContent.add(Box.createVerticalStrut(25));

        // storyArea
        // note: moved story text out of its own scroll pane to the main scroll pane
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setBackground(UISettings.PARCHMENT_BACKGROUND);
        storyArea.setForeground(UISettings.DARK_MAP_TEXT);
        storyArea.setFont(UISettings.texturina);

        scrollableContent.add(storyArea);

        // direction floating box
        JPanel floatingBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        floatingBox.setOpaque(true);
        floatingBox.setBackground(UISettings.ACCENT_COLOR);
        floatingBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel dirLabel = new JLabel("Choose Direction:");
        dirLabel.setForeground(Color.WHITE);
        dirLabel.setFont(UISettings.quintessential);

        directionSelector = new JComboBox<>(new String[]{
                "North", "South", "East", "West"
        });
        directionSelector.setPreferredSize(new Dimension(100, 28));
        directionSelector.setFocusable(false);

        floatingBox.add(dirLabel);
        floatingBox.add(directionSelector);

        floatingBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        scrollableContent.add(Box.createVerticalStrut(25)); // Spacing after text
        scrollableContent.add(floatingBox);

        // Wrap the scrollableContent panel in the main JScrollPane
        JScrollPane mainScrollPane = new JScrollPane(scrollableContent);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(mainScrollPane, BorderLayout.CENTER);

        // BOTTOM PANE: game control buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        bottomPanel.setBackground(UISettings.ACCENT_COLOR);

        restartButton = makeButton("Restart");
        progressButton = makeButton("Progress");
        saveButton = makeButton("Save");
        quitButton = makeButton("Quit");

        bottomPanel.add(restartButton);
        bottomPanel.add(progressButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(quitButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners (button + dropdown logic)
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
        b.setBackground(UISettings.PARCHMENT_BACKGROUND);
        b.setForeground(UISettings.ACCENT_COLOR);
        b.setFont(UISettings.quintessential.deriveFont(Font.PLAIN, 18)); // Reduced font size for buttons
        b.setFocusPainted(false);
        b.setOpaque(true);

        //border
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UISettings.ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // hover effect
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                b.setBackground(UISettings.HOVER_COLOR);
                b.setForeground(UISettings.DARK_MAP_TEXT);
                b.repaint();
            }

            public void mouseExited(MouseEvent evt) {
                b.setBackground(UISettings.PARCHMENT_BACKGROUND);
                b.setForeground(UISettings.ACCENT_COLOR);
                b.repaint();
            }
        });
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