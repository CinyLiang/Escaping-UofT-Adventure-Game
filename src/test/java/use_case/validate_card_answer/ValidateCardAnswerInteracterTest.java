package use_case.validate_card_answer;

import data_access.CardGameDataAccessObject;
import entity.Card;
import entity.CardPuzzle;
import interface_adapter.ViewManagerModel;
import interface_adapter.play_card_game.CardGamePresenter;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.validate_card_answer.ValidateCardPresenter;
import org.junit.jupiter.api.Test;
import use_case.card_game_hints.CardGameHintsOutputBoundary;
import use_case.card_game_hints.CardGameHintsOutputDataObject;
import use_case.play_card_game.PlayCardGameOutputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ValidateCardAnswerInteracterTest {
    @Test
    void successNewGameTest() {
        // Test the interactor for a successful input
        CardGameViewModel cardGameViewModel = new CardGameViewModel();
        ValidateCardAnswerOutputBoundary mockPresenter = new ValidateCardPresenter(cardGameViewModel) {
            @Override
            public void prepareSuccessView(ValidateCardAnswerOutputData outputData) {
                assertTrue(outputData.getSuccess());
                assertNotNull(outputData.getMessage());
            }
            @Override
            public void prepareFailView(ValidateCardAnswerOutputData outputData) {
                fail("Should not call fail view for valid input");
            }
        };

        ValidateCardAnswerInteractor interactor = new ValidateCardAnswerInteractor(mockPresenter);
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(4), new Card(1));
        CardPuzzle cardPuzzle = new CardPuzzle(cards);
        ValidateCardAnswerInputData input = new ValidateCardAnswerInputData("(2*3)*4*1", cardPuzzle);
        interactor.execute(input);
        assertEquals(cardPuzzle, input.getCardPuzzle());
    }

    @Test
    void failNewGameTest() {
        // Test the interactor with a fail input with less than 4 cards used
        TestPresenter mockPresenter = new TestPresenter();
        ValidateCardAnswerInteractor interactor = new ValidateCardAnswerInteractor(mockPresenter);
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(4), new Card(1));
        CardPuzzle cardPuzzle = new CardPuzzle(cards);
        ValidateCardAnswerInputData input = new ValidateCardAnswerInputData("(2*3) + 1", cardPuzzle);
        interactor.execute(input);
        interactor.execute(input);
        assertTrue(mockPresenter.failCalled);
        assertFalse(mockPresenter.successCalled);
    }

    @Test
    void failNewGameTest2() {
        // Test the interactor with a fail input with invalid expression
        TestPresenter mockPresenter = new TestPresenter();
        ValidateCardAnswerInteractor interactor = new ValidateCardAnswerInteractor(mockPresenter);
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(4), new Card(1));
        CardPuzzle cardPuzzle = new CardPuzzle(cards);
        ValidateCardAnswerInputData input = new ValidateCardAnswerInputData("(2*3)+1-4", cardPuzzle);
        interactor.execute(input);
        interactor.execute(input);
        assertTrue(mockPresenter.failCalled);
        assertFalse(mockPresenter.successCalled);
    }

    @Test
    void failNewGameTest3() {
        // Test the interactor with a fail input with less than 4 cards
        TestPresenter mockPresenter = new TestPresenter();
        ValidateCardAnswerInteractor interactor = new ValidateCardAnswerInteractor(mockPresenter);
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(1));
        CardPuzzle cardPuzzle = new CardPuzzle(cards);
        ValidateCardAnswerInputData input = new ValidateCardAnswerInputData("(2*3)+1-4", cardPuzzle);
        interactor.execute(input);
        interactor.execute(input);
        assertTrue(mockPresenter.failCalled);
        assertFalse(mockPresenter.successCalled);
    }
}

class TestPresenter implements ValidateCardAnswerOutputBoundary {
    // Mock presenter for the purpose of testing
    public boolean successCalled = false;
    public boolean failCalled = false;
    public CardGameHintsOutputDataObject lastOutputData = null;

    @Override
    public void prepareSuccessView(ValidateCardAnswerOutputData outputData) {
        this.successCalled = true;
    }

    @Override
    public void prepareFailView(ValidateCardAnswerOutputData outputData) {
        this.failCalled = true;
    }
}
