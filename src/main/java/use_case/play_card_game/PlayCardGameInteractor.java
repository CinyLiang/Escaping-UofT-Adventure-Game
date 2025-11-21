package use_case.play_card_game;

import entity.*;
import data_access.CardGameDataAccessObject;
import use_case.play_card_game.utilities.SolutionGenerator;

import java.util.*;

public class PlayCardGameInteractor implements PlayCardGameInputBoundary{
    private final CardGameDataAccessInterface cardGameDataAccessObject;
    private final PlayCardGameOutputBoundary cardGamePresenter;

    private CardPuzzle cardPuzzle;

    public PlayCardGameInteractor (CardGameDataAccessObject cardGameDataAccessObject,
        PlayCardGameOutputBoundary cardGamePresenter) {
        this.cardGameDataAccessObject = cardGameDataAccessObject;
        this.cardGamePresenter = cardGamePresenter;
    }

    @Override
    public void execute() {
        try {
            // generate cards
            List<Card> cards = this.cardGameDataAccessObject.drawCards(4);
            while (!SolutionGenerator.isSolvable(cards)) {
                cards = this.cardGameDataAccessObject.drawCards(4);
            }
            if (cards == null || cards.size() != 4) {
                this.cardGamePresenter.prepareFailView("Failed to draw 4 card, sorry!");
                return;
            }

            cardPuzzle = new CardPuzzle(cards);
            String displayMessage = "\"Welcome to the Math24 Card Puzzle! \" +\n" +
                    "                    \"Try to connect the four card numbers below\" +\n" +
                    "                    \"using \\\"+\\\", \\\"-\\\", \\\"*\\\", \\\"/\\\", and parentheses \" +\n" +
                    "                    \"to get an expression that evaluates to 24!\"" + cardPuzzle.getCardNumberString();

            PlayCardGameOutputData outputData = new PlayCardGameOutputData(true, cardPuzzle, displayMessage);
            this.cardGamePresenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            this.cardGamePresenter.prepareFailView("Error: " + e.getMessage());
        }
    }

}