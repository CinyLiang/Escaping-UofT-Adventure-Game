package interface_adapter.card_game_hints;

import use_case.card_game_hints.CardGameHintsInputDataObject;
import use_case.card_game_hints.CardGameHintsInteractor;

/**
 * Controller for the Give Hints Use Case.
 */
public class CardGameHintsController {
    private final CardGameHintsInteractor cardGameHintsInteractor;

    public CardGameHintsController(CardGameHintsInteractor cardGameHintsInteractor) {
        this.cardGameHintsInteractor = cardGameHintsInteractor;
    }

    /**
     * Executes the Give Hints Use Case.
     */
    public void execute(CardGameHintsInputDataObject input) {
        cardGameHintsInteractor.execute(input);
    }
}
