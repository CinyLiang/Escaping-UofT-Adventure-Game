package view;

import entity.CardPuzzle;
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
    // Controllers
    private CardGameController cardGameController;
    private CardGameHintsController cardGameHintsController;
    private ValidateCardController validateCardController;
    private ReturnFromCardController returnFromCardController;
    // Dialogue
    private ReturnFromCardDialogue returnFromCardDialogue;
    // ViewModel
    private CardGameViewModel cardGameViewModel;
    // Name
    private final String viewName = "card game";
    // Labels
    private final JLabel promptLabel = new JLabel("Math24: Please click on the " +
            "\"New Game\" button to start a new game!");
    private final JLabel hintLabel = new JLabel("");
    private final JLabel messageLabel = new JLabel("");
    // Buttons
    private final JButton startButton = new JButton("New Game");
    private final JButton hintButton = new JButton("Generate Hint");
    private final JButton validateButton = new JButton("Check My Answer");
    private final JButton returnButton = new JButton("Return");
    // TextField
    private final JTextField answerField = new JTextField(20);

    public CardGameView(CardGameViewModel cardGameViewModel) {
        this.cardGameViewModel = cardGameViewModel;
        cardGameViewModel.addPropertyChangeListener(this);

        layoutBuilder();
        eventHandler();
    }

    public void setCardGameController(CardGameController cardGameController) {
        this.cardGameController = cardGameController;
    }

    public void setCardGameHintsController(CardGameHintsController cardGameHintsController) {
        this.cardGameHintsController = cardGameHintsController;
    }

    public void setValidateCardController(ValidateCardController validateCardController) {
        this.validateCardController = validateCardController;
    }

    public void setReturnFromCardController(ReturnFromCardController returnFromCardController) {
        this.returnFromCardController = returnFromCardController;
    }

    public void setReturnFromCardDialogue(ReturnFromCardDialogue returnFromCardDialogue) {
        this.returnFromCardDialogue = returnFromCardDialogue;
    }

    public void setCardGameViewModel(CardGameViewModel cardGameViewModel) {
        this.cardGameViewModel = cardGameViewModel;
    }

    private void layoutBuilder() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Prompts
        JPanel topPanel =  new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        this.promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(this.promptLabel);
        topPanel.add(Box.createVerticalStrut(20));
//        this.messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        buttonPanel.add(this.returnButton);

        // Putting together
        add(topPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(answerPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonPanel);
    }

    private void  eventHandler() {
        startButton.addActionListener(e -> {
            cardGameController.execute();
            CardGameState state = cardGameViewModel.getState();
            state.setHint("");
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
            state.setHint("");
            System.out.println("(View) cards: " +  cardPuzzle.getCardNumberString());
            if (cardPuzzle != null) {
                String userAnswer = answerField.getText().trim();
                userAnswer = userAnswer.replaceAll("\\s+", "");;
                ValidateCardAnswerInputData validationInputData =
                        new ValidateCardAnswerInputData(userAnswer, cardPuzzle);
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

        messageLabel.setText("<html>" + state.getMessage().replace("\n", "<br>") + "</html>");
        hintLabel.setText("<html>" + state.getHint().replace("\n", "<br>") + "</html>");
    }
}