package use_case.card_game_hints;

import data_access.CardHintsDataAccessObject;
import entity.CardPuzzle;
import java.util.*;

public class CardGameHintsInteractor {
    private final CardGameHintsOutputBoundary outputBoundary;
    private final CardHintsDataAccessObject hintDataAccess;

    public CardGameHintsInteractor(CardGameHintsOutputBoundary outputBoundary,
                                   CardHintsDataAccessObject hintDataAccess) {
        this.outputBoundary = outputBoundary;
        this.hintDataAccess = hintDataAccess;
    }

    public void execute(CardGameHintsInputDataObject input) {
        try {
            CardPuzzle cardPuzzle = input.getCardPuzzle();
            String hint = this.hintDataAccess.generateHint(cardPuzzle);

            CardGameHintsOutputDataObject outputData = new CardGameHintsOutputDataObject(hint);
            this.outputBoundary.prepareSuccessView(outputData);
        }  catch (Exception e) {
            this.outputBoundary.prepareFailView("Failed to get hint: " + e.getMessage());
        }
    }
}
