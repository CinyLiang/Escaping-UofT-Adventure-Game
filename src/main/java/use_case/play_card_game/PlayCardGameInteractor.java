package use_case.play_card_game;

import entity.*;
import data_access.CardGameDataAccessObject;
import use_case.play_card_game.utilities.SolutionGenerator;

import java.util.*;

public class PlayCardGameInteractor implements PlayCardGameInputBoundary {
    private final CardGameDataAccessInterface cardGameDataAccessObject;
    private final PlayCardGameOutputBoundary cardGamePresenter;

    private CardPuzzle cardPuzzle;

    public PlayCardGameInteractor(CardGameDataAccessObject cardGameDataAccessObject,
                                  PlayCardGameOutputBoundary cardGamePresenter) {
        this.cardGameDataAccessObject = cardGameDataAccessObject;
        this.cardGamePresenter = cardGamePresenter;
    }

    @Override
    public void execute() {
        try {
            List<Card> cards = null;
            int attempts = 0;
            int maxAttempts = 10;

            while (attempts < maxAttempts) {
                cards = this.cardGameDataAccessObject.drawCards(4);

                if (cards == null || cards.size() != 4) {
                    this.cardGamePresenter.prepareFailView("Failed to draw 4 cards, sorry!");
                    return;
                }

                if (SolutionGenerator.isSolvable(cards)) {
                    break;
                }

                attempts++;
            }

            if (attempts >= maxAttempts) {
                this.cardGamePresenter.prepareFailView("Could not generate a solvable puzzle. Please try again.");
                return;
            }

            cardPuzzle = new CardPuzzle(cards);
            String displayMessage = "Welcome to the Math24 Card Puzzle! " +
                    "Try to connect the four card numbers below " +
                    "using \"+\", \"-\", \"*\", \"/\", and parentheses " +
                    "to get an expression that evaluates to 24!\n\n" +
                    "Cards: " + cardPuzzle.getCardNumberString();

            PlayCardGameOutputData outputData = new PlayCardGameOutputData(true, cardPuzzle, displayMessage);
            this.cardGamePresenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            this.cardGamePresenter.prepareFailView("Error: " + e.getMessage());
        }
    }
}