package use_case.play_card_game;

/**
 * The output boundary for the Play Card Game Use Case.
 */
public interface PlayCardGameOutputBoundary {
    /**
     * Prepares the success view for the Play Card Game Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(PlayCardGameOutputData outputData);

    /**
     * Prepares the failure view for the Play Card Game Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}