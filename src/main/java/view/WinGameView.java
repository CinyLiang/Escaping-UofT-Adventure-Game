//package view;
//
//import interface_adapter.win_game.WinGameState;
//import interface_adapter.win_game.WinGameViewModel;
//import interface_adapter.ViewManagerModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//
//public class WinGameView extends JPanel implements PropertyChangeListener {
//    private final String viewName = "win game";
//    private WinGameViewModel viewModel;
//    private ViewManagerModel viewManagerModel;
//
//    private final JLabel titleLabel;
//    private final JLabel messageLabel;
//    private final JLabel keysLabel;
//    private final JPanel buttonPanel;
//
//    public WinGameView(WinGameViewModel viewModel) {
//        this.viewModel = viewModel;
//
//        try {
//            this.viewModel.addPropertyChangeListener(this);
//        } catch (NullPointerException e) {
//            System.out.println("Win view model could not be added.");
//        }
//
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        titleLabel = new JLabel("Congratulations!");
//        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//
//        messageLabel = new JLabel();
//        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        keysLabel = new JLabel();
//        keysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        buttonPanel = new JPanel();
//
//        add(Box.createVerticalGlue());
//        add(titleLabel);
//        add(Box.createRigidArea(new Dimension(0, 20)));
//        add(messageLabel);
//        add(Box.createRigidArea(new Dimension(0, 10)));
//        add(keysLabel);
//        add(Box.createRigidArea(new Dimension(0, 30)));
//        add(buttonPanel);
//        add(Box.createVerticalGlue());
//    }
//
//    public void setViewModel(WinGameViewModel viewModel) {
//        this.viewModel = viewModel;
//    }
//
//    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
//        this.viewManagerModel = viewManagerModel;
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        WinGameState state = (WinGameState) evt.getNewValue();
//
//        buttonPanel.removeAll();
//
//        if (state.isWon()) {
//            titleLabel.setText("Congratulations!");
//            messageLabel.setText(state.getMessage());
//            keysLabel.setText("Keys collected: " + state.getKeysCollected());
//
//            JButton newGameButton = new JButton("New Game");
//            JButton quitButton = new JButton("Quit");
//
//            newGameButton.addActionListener(e -> System.exit(0));
//            quitButton.addActionListener(e -> System.exit(0));
//
//            buttonPanel.add(newGameButton);
//            buttonPanel.add(quitButton);
//        } else {
//            titleLabel.setText("Not Yet!");
//            messageLabel.setText(state.getMessage());
//            keysLabel.setText("");
//
//            JButton backButton = new JButton("Return to Kings College Circle");
//
//            backButton.addActionListener(e -> {
//                viewManagerModel.setState("navigate");
//                viewManagerModel.firePropertyChange();
//            });
//
//            buttonPanel.add(backButton);
//        }
//
//        buttonPanel.revalidate();
//        buttonPanel.repaint();
//    }
//
//    public String getViewName() {
//        return viewName;
//    }
//}

package view;

import interface_adapter.win_game.WinGameState;
import interface_adapter.win_game.WinGameViewModel;
import interface_adapter.ViewManagerModel;
import resources.UISettings; // ASSUMPTION: Import UISettings for styling

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException; // Needed for FontFormatException in constructor

public class WinGameView extends JPanel implements PropertyChangeListener {
    public static final String VIEW_NAME = "win game"; // Changed to static constant for consistency
    private WinGameViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    private final JLabel titleLabel;
    private final JLabel messageLabel;
    private final JLabel keysLabel;
    private final JPanel buttonPanel;

    public WinGameView(WinGameViewModel viewModel) throws IOException, FontFormatException { // Added throws clause for Font
        this.viewModel = viewModel;

        try {
            this.viewModel.addPropertyChangeListener(this);
        } catch (NullPointerException e) {
            System.out.println("Win view model could not be added.");
        }

        // Apply background style
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(UISettings.PARCHMENT_BACKGROUND); // Use Parchment background

        // Title Label Styling
        titleLabel = new JLabel("Congratulations!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(UISettings.quintessential.deriveFont(Font.BOLD, 36)); // Use custom font, larger size
        titleLabel.setForeground(UISettings.ACCENT_COLOR.darker()); // Use accent color

        // Message Label Styling
        messageLabel = new JLabel();
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(UISettings.texturina.deriveFont(Font.PLAIN, 16)); // Use story font
        messageLabel.setForeground(UISettings.DARK_MAP_TEXT);

        // Keys Label Styling
        keysLabel = new JLabel();
        keysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        keysLabel.setFont(UISettings.texturina.deriveFont(Font.BOLD, 18)); // Use story font, bold
        keysLabel.setForeground(UISettings.DARK_MAP_TEXT);

        // Button Panel Styling
        buttonPanel = new JPanel();
        buttonPanel.setBackground(UISettings.PARCHMENT_BACKGROUND); // Ensure button panel matches background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding

        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(messageLabel);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(keysLabel);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(buttonPanel);
        add(Box.createVerticalGlue());
    }

    // Reuse the standardized button creation method from your other views
    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(UISettings.PARCHMENT_BACKGROUND);
        b.setForeground(UISettings.ACCENT_COLOR);
        b.setFont(UISettings.quintessential.deriveFont(Font.PLAIN, 18));
        b.setFocusPainted(false);
        b.setOpaque(true);

        // Border matching the standard style
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UISettings.ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        // Hover effect matching the standard style
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

    public void setViewModel(WinGameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        WinGameState state = (WinGameState) evt.getNewValue();

        buttonPanel.removeAll();

        if (state.isWon()) {
            titleLabel.setText("Victory!"); // Adjusted title to sound more epic/gothic
            messageLabel.setText(state.getMessage());
            keysLabel.setText("Ancient Keys Collected: " + state.getKeysCollected() + " / 3"); // Added context

            JButton newGameButton = makeButton("New Quest");
            JButton quitButton = makeButton("Exit"); // Adjusted text

            newGameButton.addActionListener(e -> System.exit(0));
            quitButton.addActionListener(e -> System.exit(0));

            buttonPanel.add(newGameButton);
            buttonPanel.add(quitButton);
        } else {
            titleLabel.setText("Trial Incomplete"); // Adjusted title for loss state
            messageLabel.setText(state.getMessage());
            keysLabel.setText("You must complete the Three Trials."); // Clearer failure message

            JButton backButton = makeButton("Return to Front Lawn"); // Adjusted location name

            backButton.addActionListener(e -> {
                viewManagerModel.setState("map"); // Assume "map" or "navigate" refers to the central hub
                viewManagerModel.firePropertyChange();
            });

            buttonPanel.add(backButton);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}