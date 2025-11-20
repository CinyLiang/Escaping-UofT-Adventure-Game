package interface_adapter.play_card_game;

import use_case.play_card_game.PlayCardGameInputBoundary;

/**
 * Controller for the Play Card Game Use Case.
 */
public class CardGameController {
    private final PlayCardGameInputBoundary playCardGameInteractor;

    public CardGameController(PlayCardGameInputBoundary playCardGameInteractor) {
        this.playCardGameInteractor = playCardGameInteractor;
    }

    /**
     * Executes the Play Card Game Use Case.
     */
    public void execute() {
        playCardGameInteractor.execute();
    }
}