package use_case.play_card_game;

import entity.*;
import data_access.CardGameDataAccessObject;
import java.util.*;
import use_case.play_card_game.PlayCardGameInputBoundary;

public class PlayCardGameInteractor implements PlayCardGameInputBoundary{
    private final CardGameDataAccessInterface cardGameDataAccessObject;
    private final PlayCardGameOutputBoundary playerPresenter;

    public PlayCardGameInteractor (CardGameDataAccessObject cardGameDataAccessObject,
        PlayCardGameOutputBoundary playerPresenter) {
        this.cardGameDataAccessObject = cardGameDataAccessObject;
        this.playerPresenter = playerPresenter;
    }

    @Override
    public void execute() {
        try {
            List<Card> cards = this.cardGameDataAccessObject.drawCards(4);
            while (!SolutionGenerator.isSolvable(cards)) {
                cards = this.cardGameDataAccessObject.drawCards(4);
            }

            if (cards == null || cards.size() != 4) {
                this.playerPresenter.prepareFailView("Failed to draw 4 card, sorry!");
                return;
            }
            CardPuzzle cardPuzzle = new CardPuzzle(cards);
            PlayCardGameOutputData outputData = new PlayCardGameOutputData(true, cardPuzzle);
            this.playerPresenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            this.playerPresenter.prepareFailView("Error: " + e.getMessage());
        }
    }

}