package view;

import entity.CardPuzzle;
import entity.Player;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.play_card_game.CardGameController;
import interface_adapter.card_game_hints.CardGameHintsController;
import interface_adapter.return_from_card.ReturnFromCardController;
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
    private ReturnFromCardController returnFromCardController;

    private ReturnFromCardDialogue returnFromCardDialogue;

    private CardGameViewModel cardGameViewModel;

    private Player player;

    private final String viewName = "card game";

    private final JLabel promptLabel = new JLabel("Math24: Please click on the " +
            "\"New Game\" button to start a new game!");
    private final JLabel hintLabel = new JLabel("");
    private final JLabel messageLabel = new JLabel("");

    private final JButton startButton = new JButton("New Game");
    private final JButton hintButton = new JButton("Generate Hint");
    private final JButton validateButton = new JButton("Check My Answer");
    private final JButton returnButton = new JButton("Return");

    private final JTextField answerField = new JTextField(20);

    public CardGameView(CardGameController cardGameController,
                        CardGameHintsController cardGameHintsController,
                        ValidateCardController validateCardController,
                        ReturnFromCardController returnFromCardController,
                        ReturnFromCardDialogue returnFromCardDialogue,
                        CardGameViewModel cardGameViewModel,
                        Player player) {
        this.cardGameController = cardGameController;
        this.cardGameHintsController = cardGameHintsController;
        this.validateCardController = validateCardController;
        this.returnFromCardController = returnFromCardController;
        this.returnFromCardDialogue = returnFromCardDialogue;
        this.cardGameViewModel = cardGameViewModel;
        this.player = player;

        cardGameViewModel.addPropertyChangeListener(this);
        layoutBuilder();
        eventHandler();
    }

    private void layoutBuilder() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        this.promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(this.promptLabel);
        topPanel.add(this.messageLabel);
        topPanel.add(this.hintLabel);

        JPanel answerPanel = new JPanel();
        answerPanel.add(this.answerField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.startButton);
        buttonPanel.add(this.hintButton);
        buttonPanel.add(this.validateButton);
        buttonPanel.add(this.returnButton);

        add(topPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(answerPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonPanel);
    }

    private void eventHandler() {
        startButton.addActionListener(e -> {
            cardGameController.execute();
        });

        hintButton.addActionListener(e -> {
            CardGameState state = cardGameViewModel.getState();
            CardPuzzle cardPuzzle = state.getcardPuzzle();
            if (cardPuzzle != null) {
                CardGameHintsInputDataObject hintInputData = new CardGameHintsInputDataObject(cardPuzzle);
                cardGameHintsController.execute(hintInputData);
            }
        });

        validateButton.addActionListener(e -> {
            CardGameState state = cardGameViewModel.getState();
            CardPuzzle cardPuzzle = state.getcardPuzzle();
            if (cardPuzzle != null) {
                String userAnswer = answerField.getText().trim();
                ValidateCardAnswerInputData validationInputData =
                        new ValidateCardAnswerInputData(player, userAnswer, cardPuzzle);
                validateCardController.execute(validationInputData);
            }
        });

        returnButton.addActionListener(e -> {
            this.returnFromCardController.setShowQuitDialog(() -> returnFromCardDialogue.show());
            returnFromCardController.showQuit();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CardGameState state = cardGameViewModel.getState();
        messageLabel.setText(state.getMessage());
        hintLabel.setText(state.getHint());
    }
}