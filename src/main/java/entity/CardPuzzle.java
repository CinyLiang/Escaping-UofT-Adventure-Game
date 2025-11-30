package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity representing a Card Puzzle.
 */
public class CardPuzzle extends Puzzle {
    private static final String NAME = "CardPuzzle";
    private final List<Card> cards;

    /**
     * Creates a new card puzzle with the given id and cards.
     * @param cards the randomly drawn cards
     */
    public CardPuzzle(List<Card> cards) {
        super("", "Math 24 Game", NAME);
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * The getCardNumber method.
     * @return the list of card numbers.
     */
    public List<Integer> getCardNumbers() {
        final List<Integer> cardNumbers = new ArrayList<>();
        for (Card card : cards) {
            cardNumbers.add(card.getValue());
        }
        return cardNumbers;
    }

    /**
     * The getCardNumberString method.
     * @return the String of the list of card numbers.
     */
    public String getCardNumberString() {
        final List<Integer> cardNumbers = this.getCardNumbers();
        return cardNumbers.toString();
    }
}
