package view;

import interface_adapter.trivia_game.TriviaGameController;
import interface_adapter.trivia_game.TriviaGameState;
import interface_adapter.trivia_game.TriviaGameViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TriviaGameView extends JPanel implements PropertyChangeListener {
    private final String viewName = "trivia game";
    private TriviaGameViewModel viewModel;
    private TriviaGameController controller;

    // UI
    private final JLabel titleLabel;
    private final JLabel questionLabel;
    private final JLabel scoreLabel;
    private final JButton trueButton;
    private final JButton falseButton;
    private final JButton newQuestionButton;
    private final JButton returnButton;
    private final JLabel messageLabel;
    private final JScrollPane questionScrollPane;

    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font MESSAGE_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font QUESTION_FONT = new Font("Arial", Font.PLAIN, 16);

    public TriviaGameView(TriviaGameViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top (Title and Score)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        titleLabel = new JLabel("UofT Trivia Challenge");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);

        headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        scoreLabel = new JLabel("Score: 0/3");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(scoreLabel);

        this.add(headerPanel, BorderLayout.NORTH);

        // Center (Question and Answer Buttons)
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setOpaque(false);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // Question
        questionLabel = new JLabel("<html><div style='text-align: center;'>Loading question...</div></html>");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionScrollPane = new JScrollPane(questionLabel);
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        questionScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        questionScrollPane.setPreferredSize(new Dimension(800, 120));

        contentPanel.add(questionScrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(40, 40)));

        // Answer Buttons Panel
        JPanel answerPanel = new JPanel();
        answerPanel.setOpaque(false);
        trueButton = new JButton("True");
        falseButton = new JButton("False");

        styleAnswerButton(trueButton);
        styleAnswerButton(falseButton);
        answerPanel.add(trueButton);
        answerPanel.add(Box.createRigidArea(new Dimension(60, 0)));
        answerPanel.add(falseButton);
        contentPanel.add(answerPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Message
        messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(messageLabel);

        centerContainer.add(Box.createVerticalGlue());
        centerContainer.add(contentPanel);
        centerContainer.add(Box.createVerticalGlue());

        this.add(centerContainer, BorderLayout.CENTER);


        // Bottom (Action Buttons)
        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setOpaque(false);
        actionButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        newQuestionButton = new JButton("New Question");
        returnButton = new JButton("Return to Map");

        styleActionButton(newQuestionButton);
        styleActionButton(returnButton);

        actionButtonPanel.add(newQuestionButton);
        actionButtonPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        actionButtonPanel.add(returnButton);

        this.add(actionButtonPanel, BorderLayout.SOUTH);

        trueButton.addActionListener(e -> {
            if (controller != null) {
                controller.submitAnswer("True", "Player");
            }
        });

        falseButton.addActionListener(e -> {
            if (controller != null) {
                controller.submitAnswer("False", "Player");
            }
        });

        newQuestionButton.addActionListener(e -> {
            if (controller != null) {
                controller.startNewQuestion();
            }
        });

        returnButton.addActionListener(e -> {
            controller.exitPuzzle();
        });

        applyTheme();
    }

    private void styleAnswerButton(JButton b) {
        b.setPreferredSize(new Dimension(180, 50));
        b.setFont(new Font("Arial", Font.BOLD, view.ThemeManager.getFontSize(16)));
        styleThemedButton(b);
    }

    private void styleActionButton(JButton b) {
        b.setPreferredSize(new Dimension(220, 45));
        b.setFont(new Font("Arial", Font.BOLD, view.ThemeManager.getFontSize(14)));
        styleThemedButton(b);
    }

    private void styleThemedButton(JButton b) {
        b.setBackground(view.ThemeManager.getButtonBackground());
        b.setForeground(view.ThemeManager.getButtonForeground());
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(view.ThemeManager.getButtonForeground(), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
    }

    private void applyTheme() {
        this.setBackground(view.ThemeManager.getBackground());

        titleLabel.setForeground(view.ThemeManager.getTextPrimary());
        titleLabel.setFont(TITLE_FONT.deriveFont((float) view.ThemeManager.getFontSize(30)));

        scoreLabel.setForeground(view.ThemeManager.getTextPrimary());
        scoreLabel.setFont(SCORE_FONT.deriveFont((float) view.ThemeManager.getFontSize(18)));

        // Question display
        questionLabel.setForeground(view.ThemeManager.getTextPrimary());
        questionLabel.setFont(QUESTION_FONT.deriveFont((float) view.ThemeManager.getFontSize(16)));
        questionScrollPane.setBackground(view.ThemeManager.getBackground());
        questionScrollPane.getViewport().setBackground(view.ThemeManager.getBackground());
        questionScrollPane.setBorder(BorderFactory.createLineBorder(view.ThemeManager.getTextPrimary(), 2));
        messageLabel.setFont(MESSAGE_FONT.deriveFont((float) view.ThemeManager.getFontSize(14)));

        styleAnswerButton(trueButton);
        styleAnswerButton(falseButton);
        styleActionButton(newQuestionButton);
        styleActionButton(returnButton);

        this.revalidate();
        this.repaint();
    }

    public void setViewModel(TriviaGameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TriviaGameState state = (TriviaGameState) evt.getNewValue();

        String questionText = state.getQuestion();
        questionLabel.setText(questionText);

        // Update score and message
        scoreLabel.setText("Score: " + state.getCorrectAnswers() + "/" + state.getRequiredAnswers());
        messageLabel.setText(state.getMessage());

        // Message color
        if (state.isPuzzleSolved()) {
            messageLabel.setForeground(new Color(0, 150, 0)); // Success Green
        } else if (state.getMessage().contains("incorrect") || state.getMessage().contains("fail")) {
            messageLabel.setForeground(Color.RED); // Failure Red
        } else {
            messageLabel.setForeground(view.ThemeManager.getTextPrimary()); // Default theme color
        }

        // Button state
        boolean answered = state.hasAnsweredCurrentQuestion();
        boolean solved = state.isPuzzleSolved();

        trueButton.setEnabled(!answered && !solved);
        falseButton.setEnabled(!answered && !solved);
        newQuestionButton.setEnabled(!solved);

        applyTheme();
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(TriviaGameController controller) {
        this.controller = controller;
        controller.startNewQuestion();
    }
}