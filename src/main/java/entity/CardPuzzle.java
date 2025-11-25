package entity;

import java.util.*;

/**
 * An entity representing a Card Puzzle
 */
public class CardPuzzle extends Puzzle {
    private final List<Card> cards;
    private static final String NAME = "CardPuzzle";

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

    public List<Integer> getCardNumbers() {
        List<Integer> cardNumbers = new ArrayList<>();
        for (Card card : cards) {
            cardNumbers.add(card.getValue());
        }
        return cardNumbers;
    }

    public String getCardNumberString() {
        List<Integer> cardNumbers = this.getCardNumbers();
        return cardNumbers.toString();
    }
}
