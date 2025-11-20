package use_case.card_game_hints;

import entity.CardPuzzle;

public class CardGameHintsInteractor {
    private final CardGameHintsOutputBoundary outputBoundary;

    public CardGameHintsInteractor(CardGameHintsOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void execute(CardGameHintsInputDataObject input) {
        try {
            String hint = input.getCardPuzzle().giveHint();

            CardGameHintsOutputDataObject outputData = new CardGameHintsOutputDataObject(hint);
            this.outputBoundary.prepareSuccessView(outputData);
        }  catch (Exception e) {
            this.outputBoundary.prepareFailView("Failed to get hint: " + e.getMessage());
        }
    }
}
