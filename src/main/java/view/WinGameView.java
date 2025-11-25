package view;

import interface_adapter.win_game.WinGameState;
import interface_adapter.win_game.WinGameViewModel;
import interface_adapter.ViewManagerModel;
import view.UISettings;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WinGameView extends JPanel implements PropertyChangeListener {
    private final String viewName = "win game";
    private WinGameViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    private final JLabel titleLabel;
    private final JLabel messageLabel;
    private final JLabel keysLabel;
    private final JPanel buttonPanel;

    public WinGameView(WinGameViewModel viewModel) {
        this.viewModel = viewModel;

        try {
            this.viewModel.addPropertyChangeListener(this);
        } catch (NullPointerException e) {
            System.out.println("Win view model could not be added.");
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Congratulations!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        messageLabel = new JLabel();
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        keysLabel = new JLabel();
        keysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel = new JPanel();

        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(messageLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(keysLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(buttonPanel);
        add(Box.createVerticalGlue());
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
            titleLabel.setText("Congratulations!");
            messageLabel.setText(state.getMessage());
            keysLabel.setText("Keys collected: " + state.getKeysCollected());

            JButton newGameButton = new JButton("New Game");
            JButton quitButton = new JButton("Quit");

            newGameButton.addActionListener(e -> System.exit(0));
            quitButton.addActionListener(e -> System.exit(0));

            buttonPanel.add(newGameButton);
            buttonPanel.add(quitButton);
        } else {
            titleLabel.setText("Not Yet!");
            messageLabel.setText(state.getMessage());
            keysLabel.setText("");

            JButton backButton = new JButton("Return to Kings College Circle");

            backButton.addActionListener(e -> {
                viewManagerModel.setState("navigate");
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