package use_case.card_game_hints;

/**
 * Output Data for the Get Hints Use Case.
 */
public class CardGameHintsOutputDataObject {
    private final String hint;

    public CardGameHintsOutputDataObject(String hint) {
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }
}
