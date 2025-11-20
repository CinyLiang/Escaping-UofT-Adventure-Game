package view;

import entity.CardPuzzle;
import entity.Player;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.play_card_game.CardGameController;
import interface_adapter.card_game_hints.CardGameHintsController;
import interface_adapter.validate_card_answer.ValidateCardController;
import use_case.card_game_hints.CardGameHintsInputDataObject;
import use_case.validateCardAnswer.ValidateCardAnswerInputData;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CardGameView extends JPanel implements PropertyChangeListener {
    private CardGameController cardGameController;
    private CardGameHintsController cardGameHintsController;
    private ValidateCardController validateCardController;
    private CardGameViewModel cardGameViewModel;

    private final String viewName = "card game";

    private final JLabel promptLabel = new JLabel("Math24: Please click on the " +
            "\"New Game\" button to start a new game!");
    private final JLabel hintLabel = new JLabel("");
    private final JLabel messageLabel = new JLabel("");
    private final JButton startButton = new JButton("New Game");
    private final JButton hintButton = new JButton("Generate Hint");
    private final JButton validateButton = new JButton("Check My Answer");
    private final JTextField answerField = new JTextField(20);

    public CardGameView(CardGameController cardGameController,
                        CardGameHintsController cardGameHintsController,
                        ValidateCardController validateCardController,
                        CardGameViewModel cardGameViewModel,
                        Player player) {
        this.cardGameController = cardGameController;
        this.cardGameHintsController = cardGameHintsController;
        this.validateCardController = validateCardController;
        this.cardGameViewModel = cardGameViewModel;

        cardGameViewModel.addPropertyChangeListener(this);
        layoutBuilder();
        eventHandler(player);
    }

    private void layoutBuilder() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Prompts
        JPanel topPanel =  new JPanel();
        this.promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(this.promptLabel);
        topPanel.add(this.messageLabel);
        topPanel.add(this.hintLabel);

        // Answer
        JPanel answerPanel = new JPanel();
        answerPanel.add(this.answerField);

        // Buttons
        JPanel buttonPanel =  new JPanel();
        buttonPanel.add(this.startButton);
        buttonPanel.add(this.hintButton);
        buttonPanel.add(this.validateButton);

        // Putting together
        add(topPanel);
        add(Box.createRigidArea(new Dimension(0, 10))); // 添加间距
        add(answerPanel);
        add(Box.createRigidArea(new Dimension(0, 10))); // 添加间距
        add(buttonPanel);
    }

    private void  eventHandler(Player player) {
        startButton.addActionListener(e -> {
            cardGameController.execute();
        });

        CardPuzzle cardPuzzle = this.cardGameViewModel.getState().getcardPuzzle();
        CardGameHintsInputDataObject hintInputData = new CardGameHintsInputDataObject(cardPuzzle);
        hintButton.addActionListener(e -> {
            cardGameHintsController.execute(hintInputData);
        });

        String userAnswer = answerField.getText().trim();
        // need to confirm how this is done
        ValidateCardAnswerInputData validationInputData = new ValidateCardAnswerInputData(player, userAnswer, cardPuzzle);
        validateButton.addActionListener(e -> {
            validateCardController.execute(validationInputData);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CardGameState state = cardGameViewModel.getState();
        messageLabel.setText(state.getMessage());
        hintLabel.setText(state.getHint());
    }
}