package use_case.play_card_game;

import java.util.ArrayList;
import entity.CardPuzzle;

/**
 * Output Data for the Play Card Game Use Case.
 */
public class PlayCardGameOutputData {
    private final boolean success;
    private final CardPuzzle cardPuzzle;

    public PlayCardGameOutputData(boolean success,  CardPuzzle cardPuzzle) {
        this.success = success;
        this.cardPuzzle = cardPuzzle;
    }

    public boolean getSuccess() {
        return this.success;
    }
    public CardPuzzle getCardPuzzle() {return this.cardPuzzle;}
}
