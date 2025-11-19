package interface_adapter.play_card_game;
import entity.CardPuzzle;
import interface_adapter.ViewModel;

/**
 * The State information representing the logged-in user.
 */
public class CardGameState {
    private CardPuzzle cardPuzzle;
    private String errorMessage = "";

    public CardGameState () {
        this.cardPuzzle = null;
    }

    public CardGameState (CardGameState other) {
        this.cardPuzzle = other.getcardPuzzle();
    }

    public CardPuzzle getcardPuzzle() {
        return this.cardPuzzle;
    }

    public void setCardPuzzle(CardPuzzle cardPuzzle) {
        this.cardPuzzle =  cardPuzzle;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}