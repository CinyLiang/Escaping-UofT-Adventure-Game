package use_case.play_card_game;

import entity.*;
import use_case.play_card_game.utilities.TwentyFourChecker;

import java.util.*;

public class PlayCardGameInteractor implements PlayCardGameInputBoundary{
    private final CardGameDataAccessInterface cardGameDataAccessObject;
    private final PlayCardGameOutputBoundary cardGamePresenter;

    private CardPuzzle cardPuzzle;

    public PlayCardGameInteractor (CardGameDataAccessInterface cardGameDataAccessObject,
        PlayCardGameOutputBoundary cardGamePresenter) {
        this.cardGameDataAccessObject = cardGameDataAccessObject;
        this.cardGamePresenter = cardGamePresenter;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Generating cards...");
            // generate cards
            List<Card> cards = this.cardGameDataAccessObject.drawCards(4);

            int tmp = 0;
            while (!TwentyFourChecker.canMake24(cards)) {
                cards = this.cardGameDataAccessObject.drawCards(4);

                // debug
                System.out.println("Generating cards... (" + tmp + ")");
                tmp++;
            }
            if (cards == null || cards.size() != 4) {
                this.cardGamePresenter.prepareFailView("Failed to draw 4 card, sorry!");
                return;
            }

            System.out.println("Cards drawn. Cards: " + cards + " ; First Card: " + cards.get(0).getValue());

            cardPuzzle = new CardPuzzle(cards);
            String displayMessage = "Welcome to the Math24 Card Puzzle!\n Try to connect the four card numbers below using\n \"+\", \"-\", \"*\", \"/\", and parentheses\n to get an expression that evaluates to 24!\nYou have these numbers to work with: " + cardPuzzle.getCardNumberString();

            PlayCardGameOutputData outputData = new PlayCardGameOutputData(true, cardPuzzle, displayMessage);
            this.cardGamePresenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            this.cardGamePresenter.prepareFailView("Error: " + e.getMessage());
        }
    }

}