package interface_adapter.play_card_game;
import entity.CardPuzzle;

/**
 * The State information representing the Card Game.
 */
public class CardGameState {
    private CardPuzzle cardPuzzle;
    private String hint;
    private String message;
    private boolean solved;

    private String locationName = "Gerstein Library";

    public CardGameState () {
        this.cardPuzzle = null;
        this.hint = "";
        this.message = "";
        this.solved = false;
    }

    public CardGameState (CardGameState other) {
        this.cardPuzzle = other.getcardPuzzle();
        this.hint = other.getHint();
        this.message = other.getMessage();
        this.solved = other.solved;
    }

    public CardPuzzle getcardPuzzle() {
        return this.cardPuzzle;
    }

    public void setCardPuzzle(CardPuzzle cardPuzzle) {
        this.cardPuzzle =  cardPuzzle;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSolved() {
        return this.solved;
    }

    public void setSolved() {
        this.solved = true;
    }

    public String getLocationName() { return locationName; }

}