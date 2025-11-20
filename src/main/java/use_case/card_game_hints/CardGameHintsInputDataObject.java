package use_case.card_game_hints;

import java.util.*;
import entity.CardPuzzle;

public class CardGameHintsInputDataObject {
    /**
     * The input data for the Get Hints Use Case.
     */
    private final CardPuzzle cardPuzzle;

    public CardGameHintsInputDataObject(CardPuzzle cardPuzzle) {
        this.cardPuzzle = cardPuzzle;
    }

    public CardPuzzle getCardPuzzle() {
        return this.cardPuzzle;
    }
}
