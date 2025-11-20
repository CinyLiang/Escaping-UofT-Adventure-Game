package use_case.validateCardAnswer;

import java.util.List;
import entity.Card;
import entity.CardPuzzle;
import entity.Player;

public class ValidateCardAnswerInputData {
    /**
     * The input data for the Validate Card Answer Use Case.
     */
    private final Player player;
    private final String expression;
    private final List<Card> cards;
    private final CardPuzzle cardPuzzle;

    public ValidateCardAnswerInputData(Player player, String expression, CardPuzzle cardPuzzle) {
        this.player = player;
        this.expression = expression;
        this.cards = cardPuzzle.getCards();
        this.cardPuzzle = cardPuzzle;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getExpression() {
        return this.expression;

    }
    public List<Card> getCards() {
        return this.cards;
    }

    public CardPuzzle getCardPuzzle() {
        return this.cardPuzzle;
    }
}