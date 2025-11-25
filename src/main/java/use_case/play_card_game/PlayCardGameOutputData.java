package use_case.play_card_game;

import entity.CardPuzzle;

/**
 * Output Data for the Play Card Game Use Case.
 */
public class PlayCardGameOutputData {
    private final boolean success;
    private final CardPuzzle cardPuzzle;
    private final String message;

    public PlayCardGameOutputData(boolean success, CardPuzzle cardPuzzle, String message) {
        this.success = success;
        this.cardPuzzle = cardPuzzle;
        this.message = message;
    }

    public boolean getSuccess() {
        return this.success;
    }
    public CardPuzzle getCardPuzzle() {return this.cardPuzzle;}
    public String getMessage() {return this.message;}
}
