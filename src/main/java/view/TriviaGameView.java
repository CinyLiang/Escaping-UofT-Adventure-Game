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
    private final TriviaGameViewModel viewModel;
    private TriviaGameController controller;

    private final JLabel questionLabel;
    private final JLabel scoreLabel;
    private final JButton trueButton;
    private final JButton falseButton;
    private final JLabel messageLabel;

    public TriviaGameView(TriviaGameViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("Science Trivia");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        // Score display
        scoreLabel = new JLabel("Score: 0/3");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scoreLabel);

        // Question
        questionLabel = new JLabel("Click 'New Question' to start");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(questionLabel);

        // Buttons
        JPanel buttonPanel = new JPanel();
        trueButton = new JButton("True");
        falseButton = new JButton("False");
        JButton newQuestionButton = new JButton("New Question");

        trueButton.addActionListener(e -> controller.submitAnswer("True", "Player"));
        falseButton.addActionListener(e -> controller.submitAnswer("False", "Player"));
        newQuestionButton.addActionListener(e -> controller.startNewQuestion());

        buttonPanel.add(trueButton);
        buttonPanel.add(falseButton);
        buttonPanel.add(newQuestionButton);
        add(buttonPanel);

        // Message/feedback
        messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(messageLabel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TriviaGameState state = (TriviaGameState) evt.getNewValue();
        questionLabel.setText(state.getQuestion());
        scoreLabel.setText("Score: " + state.getCorrectAnswers() + "/" + state.getRequiredAnswers());
        messageLabel.setText(state.getMessage());

        if (state.isPuzzleSolved()) {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(TriviaGameController controller) {
        this.controller = controller;
    }
}