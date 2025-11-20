package interface_adapter.card_game_hints;

/**
 * The State information representing the Give Hints Use Case.
 */
public class CardGameHintsState {
    private String hint;
    private String errorMessage;

    public CardGameHintsState() {
        this.hint = "";
        this.errorMessage = "";
    }

    public CardGameHintsState(CardGameHintsState cardGameHintsState) {
        this.hint = cardGameHintsState.getHint();
        this.errorMessage =  cardGameHintsState.getErrorMessage();
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
