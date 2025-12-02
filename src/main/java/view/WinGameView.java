package view;

import interface_adapter.win_game.WinGameState;
import interface_adapter.win_game.WinGameViewModel;
import interface_adapter.ViewManagerModel;
import view.UISettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WinGameView extends JPanel implements PropertyChangeListener {
    private final String viewName = "win game";
    private WinGameViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    private final JLabel titleLabel;
    private final JTextArea messageArea;
    private final JLabel keysLabel;
    private final JPanel buttonPanel;

    public WinGameView(WinGameViewModel viewModel) {
        this.viewModel = viewModel;

        if (this.viewModel != null) {
            this.viewModel.addPropertyChangeListener(this);
        }

        setLayout(new BorderLayout());
        setBackground(UISettings.PARCHMENT_BACKGROUND);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(UISettings.PARCHMENT_BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        titleLabel = new JLabel("Victory!", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(UISettings.quintessential.deriveFont(Font.BOLD, 48f));
        titleLabel.setForeground(UISettings.ACCENT_COLOR);

        messageArea = new JTextArea("You have successfully escaped UofT! " + "The doors of Convocation Hall swing open before you, and freedom awaits beyond.");
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBackground(UISettings.PARCHMENT_BACKGROUND);
        messageArea.setForeground(UISettings.DARK_MAP_TEXT);
        messageArea.setFont(UISettings.texturina.deriveFont(Font.PLAIN, 18f));
        messageArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UISettings.ACCENT_COLOR, 3),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        keysLabel = new JLabel("Keys Collected: 2 / 2");
        keysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        keysLabel.setFont(UISettings.texturina.deriveFont(Font.BOLD, 20f));
        keysLabel.setForeground(UISettings.ACCENT_COLOR);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(UISettings.PARCHMENT_BACKGROUND);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton newGameButton = createStyledButton("New Adventure");
        JButton quitButton = createStyledButton("Exit Game");

        newGameButton.addActionListener(e -> {
            if (viewManagerModel != null) {
                viewManagerModel.setState(NavigateView.VIEW_NAME);
                viewManagerModel.firePropertyChange();
            }
        });

        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(newGameButton);
        buttonPanel.add(quitButton);

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(messageArea);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(keysLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        contentPanel.add(buttonPanel);
        contentPanel.add(Box.createVerticalGlue());

        add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(UISettings.PARCHMENT_BACKGROUND);
        button.setForeground(UISettings.ACCENT_COLOR);
        button.setFont(UISettings.quintessential.deriveFont(Font.PLAIN, 20f));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 50));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UISettings.ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(UISettings.HOVER_COLOR);
                button.setForeground(UISettings.DARK_MAP_TEXT);
                button.repaint();
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(UISettings.PARCHMENT_BACKGROUND);
                button.setForeground(UISettings.ACCENT_COLOR);
                button.repaint();
            }
        });

        return button;
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
            titleLabel.setText("Victory!");
            messageArea.setText(state.getMessage() + "\n\nYou have successfully escaped UofT! " + "The doors of Convocation Hall swing open before you, and freedom awaits beyond.");
            keysLabel.setText("Keys Collected: " + state.getKeysCollected() + " / 2");

            JButton newGameButton = createStyledButton("New Adventure");
            JButton quitButton = createStyledButton("Exit Game");

            newGameButton.addActionListener(e -> {
                viewManagerModel.setState(NavigateView.VIEW_NAME);
                viewManagerModel.firePropertyChange();
            });

            quitButton.addActionListener(e -> System.exit(0));

            buttonPanel.add(newGameButton);
            buttonPanel.add(quitButton);
        } else {
            titleLabel.setText("Not Yet!");
            messageArea.setText(state.getMessage() + "\n\nThe doors remain sealed. " + "Continue your quest to find the remaining keys.");
            keysLabel.setText("");

            JButton backButton = createStyledButton("Return to Map");

            backButton.addActionListener(e -> {
                viewManagerModel.setState(NavigateView.VIEW_NAME);
                viewManagerModel.firePropertyChange();
            });

            buttonPanel.add(backButton);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public String getViewName() {
        return viewName;
    }
}