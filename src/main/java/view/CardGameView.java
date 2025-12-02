package view;

import entity.CardPuzzle;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.play_card_game.CardGameController;
import interface_adapter.card_game_hints.CardGameHintsController;
import interface_adapter.return_from_card.ReturnFromCardController;
import interface_adapter.validate_card_answer.ValidateCardController;
import use_case.card_game_hints.CardGameHintsInputDataObject;
import use_case.validate_card_answer.ValidateCardAnswerInputData;
import view.theme.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

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

    // UI Components
    private final JTextArea promptArea;
    private final JLabel hintLabel;
    private final JLabel messageLabel;
    private final JTextField answerField;

    // Card Display
    private final JPanel cardPanel;
    private final JLabel[] cardLabels = new JLabel[4];

    // Operator
    private final JButton addButton = new JButton("+");
    private final JButton subButton = new JButton("-");
    private final JButton mulButton = new JButton("*");
    private final JButton divButton = new JButton("/");
    private final JButton lpButton = new JButton("(");
    private final JButton rpButton = new JButton(")");
    private final JButton clearButton = new JButton("Clear");

    private final JButton startButton;
    private final JButton hintButton;
    private final JButton validateButton;
    private final JButton returnButton;

    // Theme Constants
    private static final Font PROMPT_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font MESSAGE_FONT = new Font("Arial", Font.ITALIC, 14);
    private static final Font CARD_FONT = new Font("Arial", Font.BOLD, 36);

    private final String[] currentCardValues = new String[4];
    private final boolean[] cardsUsed = new boolean[4];

    public CardGameView(CardGameViewModel cardGameViewModel) {
        this.cardGameViewModel = cardGameViewModel;
        cardGameViewModel.addPropertyChangeListener(this);

        // Initialize components
        this.promptArea = new JTextArea("Math24: Please click the \"New Game\" button to start a new game!!");
        this.promptArea.setEditable(false);
        this.promptArea.setLineWrap(true);
        this.promptArea.setWrapStyleWord(true);
        this.promptArea.setMargin(new Insets(10, 10, 10, 10));

        this.hintLabel = new JLabel("");
        this.messageLabel = new JLabel("");
        this.answerField = new JTextField(20);
        this.answerField.setEditable(false);

        // Initialize card labels and add MouseListener for input
        this.cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        for (int i = 0; i < 4; i++) {
            final int cardIndex = i;
            cardLabels[i] = new JLabel("?", SwingConstants.CENTER);
            cardLabels[i].setPreferredSize(new Dimension(80, 120));
            cardLabels[i].setOpaque(true);
            cardLabels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
            cardLabels[i].setFont(CARD_FONT);
            cardLabels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

            cardLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!cardsUsed[cardIndex] && !currentCardValues[cardIndex].isEmpty()) {
                        appendInput(currentCardValues[cardIndex]);
                        cardsUsed[cardIndex] = true;
                        // Visually gray out the used card
                        cardLabels[cardIndex].setForeground(Color.LIGHT_GRAY);
                        cardLabels[cardIndex].repaint();
                    }
                }
            });
            cardPanel.add(cardLabels[i]);
        }
        cardPanel.setOpaque(false);

        this.startButton = new JButton("New Game");
        this.hintButton = new JButton("Generate Hint");
        this.validateButton = new JButton("Check My Answer");
        this.returnButton = new JButton("Return to Map");

        ThemeManager.addThemeChangeListener(evt -> applyTheme());
        applyTheme();
        layoutBuilder();
        eventHandler();

        // Initial state reset
        resetInputState();
        ThemeManager.addThemeChangeListener(evt -> applyTheme());

    }

    private void appendInput(String text) {
        answerField.setText(answerField.getText() + text);
    }

    // Reset the input state for a new expression
    private void resetInputState() {
        Arrays.fill(cardsUsed, false);
        answerField.setText("");
        applyTheme();
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

    // UI
    private void layoutBuilder() {
        this.setLayout(new BorderLayout(20, 20));

        // TOP Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        topPanel.setOpaque(false);

        // Puzzle Display
        JPanel puzzleDisplayPanel = new JPanel();
        puzzleDisplayPanel.setLayout(new BoxLayout(puzzleDisplayPanel, BoxLayout.Y_AXIS));
        puzzleDisplayPanel.setOpaque(false);

        // Prompt Area (instructions)
        puzzleDisplayPanel.add(promptArea);
        promptArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Card display
        puzzleDisplayPanel.add(Box.createVerticalStrut(20));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        puzzleDisplayPanel.add(cardPanel);
        puzzleDisplayPanel.add(Box.createVerticalStrut(20));

        // Message and Hint labels
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        puzzleDisplayPanel.add(messageLabel);
        puzzleDisplayPanel.add(Box.createVerticalStrut(10));
        puzzleDisplayPanel.add(hintLabel);

        topPanel.add(puzzleDisplayPanel, BorderLayout.CENTER);

        // Mid (Operators and Input)
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setOpaque(false);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // Operator Button Panel
        JPanel operatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        operatorPanel.setOpaque(false);

        styleOperatorButton(addButton);
        styleOperatorButton(subButton);
        styleOperatorButton(mulButton);
        styleOperatorButton(divButton);
        styleOperatorButton(lpButton);
        styleOperatorButton(rpButton);
        styleOperatorButton(clearButton);

        operatorPanel.add(lpButton);
        operatorPanel.add(rpButton);
        operatorPanel.add(Box.createHorizontalStrut(20));
        operatorPanel.add(addButton);
        operatorPanel.add(subButton);
        operatorPanel.add(mulButton);
        operatorPanel.add(divButton);
        operatorPanel.add(Box.createHorizontalStrut(20));
        operatorPanel.add(clearButton);

        middlePanel.add(operatorPanel);
        middlePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Answer Input
        JPanel answerInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        answerInputPanel.setOpaque(false);
        answerField.setPreferredSize(new Dimension(450, 40));
        answerInputPanel.add(answerField);

        middlePanel.add(answerInputPanel);

        // Bottom Panel
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        actionButtonPanel.setOpaque(false);
        actionButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Add action buttons
        styleButton(startButton);
        styleButton(hintButton);
        styleButton(validateButton);
        styleButton(returnButton);

        actionButtonPanel.add(startButton);
        actionButtonPanel.add(hintButton);
        actionButtonPanel.add(validateButton);
        actionButtonPanel.add(returnButton);

        // Putting together the main view
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(actionButtonPanel, BorderLayout.SOUTH);
    }

    // Code Theme
    private void applyTheme() {
        this.setBackground(view.theme.ThemeManager.getBackground());

        // Text Areas and Labels
        promptArea.setBackground(view.theme.ThemeManager.getBackground());
        promptArea.setForeground(view.theme.ThemeManager.getTextPrimary());
        promptArea.setFont(PROMPT_FONT.deriveFont(view.theme.ThemeManager.getFontSize((int) 18f)));

        hintLabel.setForeground(view.theme.ThemeManager.getTextPrimary());
        hintLabel.setFont(MESSAGE_FONT.deriveFont(view.theme.ThemeManager.getFontSize((int) 14f)));

        messageLabel.setFont(MESSAGE_FONT.deriveFont(view.theme.ThemeManager.getFontSize((int) 14f)));
        messageLabel.setForeground(view.theme.ThemeManager.getTextPrimary());

        // Input Area
        answerField.setBackground(Color.WHITE);
        answerField.setForeground(Color.BLACK);
        answerField.setCaretColor(Color.BLACK);

        // Card styling
        Color cardBorder = view.theme.ThemeManager.getTextPrimary();

        for (int i = 0; i < 4; i++) {
            JLabel card = cardLabels[i];
            card.setBackground(Color.WHITE);

            Color cardUnusedFg = Color.BLACK;

            card.setForeground(cardsUsed[i] ? Color.LIGHT_GRAY : cardUnusedFg);
            card.setBorder(BorderFactory.createLineBorder(cardBorder, 3));
        }

        this.repaint();
    }

    private void styleButton(JButton b) {
        b.setPreferredSize(new Dimension(200, 40));
        b.setBackground(view.theme.ThemeManager.getButtonBackground());
        b.setForeground(view.theme.ThemeManager.getButtonForeground());
        b.setFont(new Font("Arial", Font.BOLD, view.theme.ThemeManager.getFontSize(14)));

        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(view.theme.ThemeManager.getButtonForeground(), 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        b.setFocusPainted(false);
    }

    private void styleOperatorButton(JButton b) {
        if (b.getText().equals("Clear")) {
            b.setPreferredSize(new Dimension(80, 40));
            b.setFont(new Font("Arial", Font.BOLD, view.theme.ThemeManager.getFontSize(14)));
        } else {
            b.setPreferredSize(new Dimension(40, 40));
            b.setFont(new Font("Arial", Font.BOLD, view.theme.ThemeManager.getFontSize(18)));
        }

        b.setBackground(view.theme.ThemeManager.getButtonBackground());
        b.setForeground(view.theme.ThemeManager.getButtonForeground());
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(view.theme.ThemeManager.getButtonForeground(), 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        b.setFocusPainted(false);
    }

    private void  eventHandler() {
        startButton.addActionListener(e -> {
            cardGameController.execute();
            CardGameState state = cardGameViewModel.getState();
            state.setHint("");
            resetInputState();
            messageLabel.setForeground(view.theme.ThemeManager.getTextPrimary());
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

            if (cardPuzzle != null) {
                String userAnswer = answerField.getText().trim();
                userAnswer = userAnswer.replaceAll("\\s+", "");

                ValidateCardAnswerInputData validationInputData =
                        new ValidateCardAnswerInputData(userAnswer, cardPuzzle);
                validateCardController.execute(validationInputData);
            }
            // Reset cardsUsed regardless of validation result for next attempt
            // Only reset cardsUsed if the expression is *complete* or start over.
        });

        returnButton.addActionListener(e -> {
            returnFromCardController.setShowQuitDialog(() -> returnFromCardDialogue.showDialog());
            returnFromCardController.showQuit();
        });

        // Operator Buttons (Append to input field)
        addButton.addActionListener(e -> appendInput("+"));
        subButton.addActionListener(e -> appendInput("-"));
        mulButton.addActionListener(e -> appendInput("*"));
        divButton.addActionListener(e -> appendInput("/"));
        lpButton.addActionListener(e -> appendInput("("));
        rpButton.addActionListener(e -> appendInput(")"));

        // Clear Button
        clearButton.addActionListener(e -> resetInputState());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CardGameState state = cardGameViewModel.getState();

        // Update Card Labels with new numbers and reset state
        if (state.getcardPuzzle() != null) {
            String[] cardNumbers = state.getcardPuzzle().getCardNumbers().stream()
                    .map(String::valueOf).toArray(String[]::new);

            Arrays.fill(cardsUsed, false);
            Arrays.fill(currentCardValues, "");

            for (int i = 0; i < 4; i++) {
                if (i < cardNumbers.length) {
                    cardLabels[i].setText(cardNumbers[i]);
                    currentCardValues[i] = cardNumbers[i];
                } else {
                    cardLabels[i].setText("");
                }
            }
        }

        // Update prompt
        String newPrompt = "Welcome to the Math24 Card Puzzle!\n" +
                "Try to connect the four card numbers below using '+', '-', 'x', '/', and parentheses\n" +
                "to get an expression that evaluates to 24!";

        promptArea.setText(newPrompt);

        // Update message and hint labels
        messageLabel.setText(state.getMessage());
        hintLabel.setText(state.getHint());

        if (state.getMessage().contains("Solved") || state.getMessage().contains("Correct")) {
            messageLabel.setForeground(new Color(0, 150, 0));
        } else if (state.getMessage().contains("Invalid") || state.getMessage().contains("incorrect")) {
            messageLabel.setForeground(Color.RED);
        } else {
            messageLabel.setForeground(view.theme.ThemeManager.getTextPrimary()); // Default theme
        }

        applyTheme();

        answerField.setText("");
    }

    public String getViewName() {
        return viewName;
    }
}
