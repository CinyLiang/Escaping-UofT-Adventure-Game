package view;

import interface_adapter.trivia_game.TriviaGameController;
import interface_adapter.trivia_game.TriviaGameState;
import interface_adapter.trivia_game.TriviaGameViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import view.UISettings;

public class TriviaGameView extends JPanel implements PropertyChangeListener {
    private final String viewName = "trivia game";
    private TriviaGameViewModel viewModel;
    private TriviaGameController controller;

    private final JLabel questionLabel;
    private final JLabel scoreLabel;
    private final JButton trueButton;
    private final JButton falseButton;
    private final JButton newQuestionButton;
    private final JButton returnButton;
    private final JLabel messageLabel;

    public TriviaGameView(TriviaGameViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Use BorderLayout for main structure (similar to NavigateView)
        setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        // title bar
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        titlePanel.setBackground(UISettings.ACCENT_COLOR);
        titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UISettings.DARK_MAP_TEXT));

        JLabel title = new JLabel("Science Trivia");
        title.setFont(UISettings.quintessential.deriveFont(Font.BOLD, 36f)); // Larger title
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        this.add(titlePanel, BorderLayout.NORTH);


        // main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(UISettings.PARCHMENT_BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Padding

        // Score Label
        scoreLabel = new JLabel("Score: 0/3");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setFont(UISettings.texturina.deriveFont(Font.BOLD, 18f));
        scoreLabel.setForeground(UISettings.ACCENT_COLOR);
        contentPanel.add(scoreLabel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Question label
        questionLabel = new JLabel("<html><div style='text-align: center; width: 600px;'>Loading question...</div></html>");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(UISettings.texturina.deriveFont(Font.PLAIN, 16f));
        questionLabel.setForeground(UISettings.DARK_MAP_TEXT);
        contentPanel.add(questionLabel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Answer Buttons
        JPanel answerPanel = new JPanel();
        answerPanel.setBackground(UISettings.PARCHMENT_BACKGROUND);

        trueButton = createButton("True");
        falseButton = createButton("False");

        trueButton.addActionListener(e -> {
            if (controller != null) {
                controller.submitAnswer("True");
            }
        });

        falseButton.addActionListener(e -> {
            if (controller != null) {
                controller.submitAnswer("False");
            }
        });

        answerPanel.add(trueButton);
        answerPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        answerPanel.add(falseButton);
        contentPanel.add(answerPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Message Label (Feedback)
        messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(UISettings.quintessential.deriveFont(Font.BOLD, 20f));
        messageLabel.setForeground(UISettings.DARK_MAP_TEXT); // Default color, will be changed in propertyChange
        contentPanel.add(messageLabel);

        contentPanel.add(Box.createVerticalGlue()); // Push content upwards

        // Add contentPanel to the center
        this.add(contentPanel, BorderLayout.CENTER);


        // --- BOTTOM PANE: Control Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(UISettings.ACCENT_COLOR); // Use ACCENT_COLOR for the bottom bar

        newQuestionButton = createButton("New Question");
        returnButton = createButton("Return to Kings College Circle");

        newQuestionButton.addActionListener(e -> {
            if (controller != null) {
                controller.startNewQuestion();
            }
        });

        returnButton.addActionListener(e -> {
            controller.exitPuzzle();
        });

        buttonPanel.add(newQuestionButton);
        buttonPanel.add(returnButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Helper method to create buttons matching the style of NavigateView's bottom buttons.
     */
    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(UISettings.PARCHMENT_BACKGROUND);
        b.setForeground(UISettings.ACCENT_COLOR);
        b.setFont(UISettings.quintessential.deriveFont(Font.PLAIN, 18));
        b.setFocusPainted(false);
        b.setOpaque(true);

        // Border
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UISettings.ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Hover effect (copied from NavigateView)
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(UISettings.HOVER_COLOR);
                b.setForeground(UISettings.DARK_MAP_TEXT);
                b.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(UISettings.PARCHMENT_BACKGROUND);
                b.setForeground(UISettings.ACCENT_COLOR);
                b.repaint();
            }
        });
        return b;
    }

    public void setViewModel(TriviaGameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TriviaGameState state = (TriviaGameState) evt.getNewValue();

        String wrappedQuestion = "<html><div style='text-align: center;'>"
                + state.getQuestion() + "</div></html>";
        questionLabel.setText(wrappedQuestion);

        scoreLabel.setText("Correct Answers: " + state.getCorrectAnswers() + " / " + state.getRequiredAnswers());
        messageLabel.setText(state.getMessage());

        if (state.isPuzzleSolved()) {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
            newQuestionButton.setEnabled(false);
            messageLabel.setForeground(UISettings.ACCENT_COLOR.darker()); // solved message
        } else if (state.hasAnsweredCurrentQuestion()) {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
            // color-coded feedback
            if (state.getMessage().contains("Correct")) {
                messageLabel.setForeground(new Color(0, 150, 0)); // green when correct
            } else {
                messageLabel.setForeground(new Color(180, 0, 0)); // Red
            }
        } else {
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);
            messageLabel.setForeground(UISettings.DARK_MAP_TEXT); // Neutral color
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(TriviaGameController controller) {
        this.controller = controller;
        controller.startNewQuestion();
    }
}