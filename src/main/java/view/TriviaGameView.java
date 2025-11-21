package view;

import interface_adapter.trivia_game.TriviaGameController;
import interface_adapter.trivia_game.TriviaGameState;
import interface_adapter.trivia_game.TriviaGameViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TriviaGameView extends JPanel implements PropertyChangeListener {
    private final String viewName = "trivia game";
    private final TriviaGameViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private TriviaGameController controller;

    private final JLabel questionLabel;
    private final JLabel scoreLabel;
    private final JButton trueButton;
    private final JButton falseButton;
    private final JButton newQuestionButton;
    private final JButton returnButton;
    private final JLabel messageLabel;

    public TriviaGameView(TriviaGameViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Science Trivia");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);

        add(Box.createRigidArea(new Dimension(0, 20)));

        scoreLabel = new JLabel("Score: 0/3");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scoreLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        questionLabel = new JLabel("<html><div style='text-align: center; width: 600px;'>Loading question...</div></html>");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(questionLabel);

        add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel answerPanel = new JPanel();
        trueButton = new JButton("True");
        falseButton = new JButton("False");

        trueButton.setPreferredSize(new Dimension(100, 40));
        falseButton.setPreferredSize(new Dimension(100, 40));

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

        answerPanel.add(trueButton);
        answerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        answerPanel.add(falseButton);
        add(answerPanel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel buttonPanel = new JPanel();
        newQuestionButton = new JButton("New Question");
        returnButton = new JButton("Return to Kings College Circle");

        newQuestionButton.addActionListener(e -> {
            if (controller != null) {
                controller.startNewQuestion();
            }
        });

        returnButton.addActionListener(e -> {
            viewManagerModel.setState("navigate");
            viewManagerModel.firePropertyChange();
        });

        buttonPanel.add(newQuestionButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(returnButton);
        add(buttonPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TriviaGameState state = (TriviaGameState) evt.getNewValue();

        String wrappedQuestion = "<html><div style='text-align: center; width: 600px;'>"
                + state.getQuestion() + "</div></html>";
        questionLabel.setText(wrappedQuestion);

        scoreLabel.setText("Score: " + state.getCorrectAnswers() + "/" + state.getRequiredAnswers());
        messageLabel.setText(state.getMessage());

        if (state.isPuzzleSolved()) {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
            newQuestionButton.setEnabled(false);
            messageLabel.setForeground(new Color(0, 150, 0));
        } else if (state.hasAnsweredCurrentQuestion()) {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
        } else {
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);
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