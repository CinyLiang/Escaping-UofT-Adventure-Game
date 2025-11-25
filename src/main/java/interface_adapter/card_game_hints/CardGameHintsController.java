package interface_adapter.card_game_hints;

import use_case.card_game_hints.CardGameHintsInputDataBoundary;
import use_case.card_game_hints.CardGameHintsInputDataObject;

/**
 * Controller for the Give Hints Use Case.
 */
public class CardGameHintsController {
    private final CardGameHintsInputDataBoundary cardGameHintsInteractor;

    public CardGameHintsController(CardGameHintsInputDataBoundary cardGameHintsInteractor) {
        this.cardGameHintsInteractor = cardGameHintsInteractor;
    }

    /**
     * Executes the Give Hints Use Case.
     */
    public void execute(CardGameHintsInputDataObject input) {
        cardGameHintsInteractor.execute(input);
    }
}
