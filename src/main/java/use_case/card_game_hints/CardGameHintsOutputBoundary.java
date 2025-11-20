package use_case.card_game_hints;

import use_case.play_card_game.PlayCardGameOutputData;

/**
 * The output boundary for the Play Card Game Use Case.
 */
public interface CardGameHintsOutputBoundary {
    /**
     * Prepares the success view for the Get Hints Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CardGameHintsOutputDataObject outputData);

    /**
     * Prepares the failure view for the Play Card Game Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
