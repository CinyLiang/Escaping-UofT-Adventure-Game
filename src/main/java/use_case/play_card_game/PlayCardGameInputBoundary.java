package use_case.play_card_game;
import use_case.play_card_game.PlayCardGameInputData;
/**
 * The Play Card Game Use Case.
 */

public interface PlayCardGameInputBoundary {
    /**
     * Execute the Play Card Game Use Case.
     * @param playCardData the input data for this use case
     */
    void execute(PlayCardGameInputData playCardData);

}