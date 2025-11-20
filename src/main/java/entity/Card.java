package entity;

/**
 * An entity representing a card in the card puzzle.
 */

public class Card {
    private final int value;
    public Card(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}