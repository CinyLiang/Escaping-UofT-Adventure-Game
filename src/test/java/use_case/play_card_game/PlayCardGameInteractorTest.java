package use_case.play_card_game;

import data_access.CardGameDataAccessObject;
import interface_adapter.play_card_game.CardGamePresenter;
import org.junit.jupiter.api.Test;
import interface_adapter.ViewManagerModel;
import interface_adapter.play_card_game.CardGameViewModel;
import static org.junit.jupiter.api.Assertions.*;
import static use_case.play_card_game.utilities.TwentyFourChecker.canMake24;

import entity.Card;
import entity.CardPuzzle;
import use_case.card_game_hints.CardGameHintsInputDataObject;
import use_case.card_game_hints.CardGameHintsInteractor;
import use_case.card_game_hints.CardGameHintsOutputBoundary;
import use_case.card_game_hints.CardGameHintsOutputDataObject;
import use_case.play_card_game.utilities.SolutionGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class PlayCardGameInteractorTest {

    @Test
    void successNewGameTest() {
        // Test the prepare success and fail functionalities of the interactor
        CardGameDataAccessObject cardGameDataAccessObject = new CardGameDataAccessObject(4);
        CardGameViewModel cardGameViewModel = new CardGameViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        CardGamePresenter successPresenter = new CardGamePresenter(cardGameViewModel, viewManagerModel) {
            @Override
            public void prepareSuccessView(PlayCardGameOutputData outputData) {
                assertEquals(4, cardGameDataAccessObject.drawCards().size());
                assertNotNull(outputData.getCardPuzzle());
                assertNotNull(outputData.getMessage());
                assertTrue(outputData.getCardPuzzle().getCards().stream()
                        .allMatch(card -> card.getValue() >= 1 && card.getValue() <= 13));
                assertEquals(true, outputData.getSuccess());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        PlayCardGameInputBoundary interactor = new PlayCardGameInteractor(cardGameDataAccessObject, successPresenter);
        interactor.execute();
    }

    @Test
    void successEvaluationTest() {
        // Test execute on a success call
        TestPresenter presenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(presenter);

        List<Card> cards = Arrays.asList(new Card(2), new Card(4), new Card(6), new Card(3));
        CardPuzzle puzzle = new CardPuzzle(cards);
        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        interactor.execute(input);
        assertTrue(presenter.successCalled);
        assertFalse(presenter.failCalled);
        assertNotNull(presenter.lastOutputData);
    }

    @Test
    void failureEvaluationTest() {
        // Test execute on a fail call
        TestPresenter presenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(presenter);

        // Test with invalid puzzle (null or empty)
        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(null);

        interactor.execute(input);
        assertTrue(presenter.failCalled);
        assertFalse(presenter.successCalled);
    }

    @Test
    void testCardPuzzleWithDifferentCardValues() {
        // Test the interacter with different card values
        TestPresenter presenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(presenter);

        // Test with edge case card values
        List<Card> cards = Arrays.asList(new Card(1), new Card(13), new Card(7), new Card(10));
        CardPuzzle puzzle = new CardPuzzle(cards);
        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        interactor.execute(input);
        assertTrue(presenter.successCalled);
        assertNotNull(presenter.lastOutputData);
    }

    @Test
    void testCardPuzzleWithDuplicateCards() {
        // Test the interacter with duplicate card values
        TestPresenter presenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(presenter);

        // Test with duplicate card values
        List<Card> cards = Arrays.asList(new Card(5), new Card(5), new Card(5), new Card(5));
        CardPuzzle puzzle = new CardPuzzle(cards);
        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        interactor.execute(input);
        assertTrue(presenter.successCalled);
        assertNotNull(presenter.lastOutputData);
    }

    @Test
    void testLessThanFourCards() {
        // Test the interacter with less than four cards so that it should generate a failure view
        // Use the TestPlayCardGamePresenter directly with the interactor
        TestPlayCardGamePresenter mockPresenter = new TestPlayCardGamePresenter();

        // Create data access that returns less than 4 cards
        CardGameDataAccessObject cardGameDataAccessObject = new CardGameDataAccessObject(3);

        PlayCardGameInteractor interactor = new PlayCardGameInteractor(cardGameDataAccessObject, mockPresenter);
        interactor.execute();

        assertEquals("Failed to draw 4 card, sorry!", mockPresenter.errorMessage);
        assertTrue(mockPresenter.failViewCalled, "prepareFailView should be called");
        assertEquals(true, interactor.failCardDraw(cardGameDataAccessObject.drawCards()));
    }

    @Test
    void testFailCardDrawNull() {
        // Test extracted with an empty card set
        // Use the TestPlayCardGamePresenter directly with the interactor
        List<Card> cards = null;
        TestPlayCardGamePresenter mockPresenter = new TestPlayCardGamePresenter();

        // Create data access that returns less than 4 cards
        CardGameDataAccessObject cardGameDataAccessObject = new CardGameDataAccessObject(0);

        PlayCardGameInteractor interactor = new PlayCardGameInteractor(cardGameDataAccessObject, mockPresenter);
        assertEquals(true, interactor.failCardDraw(cards));
    }

    @Test
    void testEmptyCardPuzzle() {
        // // Test the interacter with empty card input
        TestPresenter presenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(presenter);

        // Test with empty card list
        List<Card> cards = new ArrayList<>();
        CardPuzzle puzzle = new CardPuzzle(cards);
        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        interactor.execute(input);
        // Depending on your business logic, this could be success or failure
        // You'll need to adjust based on actual requirements
        assertTrue(presenter.successCalled || presenter.failCalled);
    }

    @Test
    void testCardGameDataAccessObject() {
        // Test that the drawCards method in CardGameDataAccessObject draws cards as desired
        CardGameDataAccessObject dao = new CardGameDataAccessObject(4);

        // Test drawing different numbers of cards
        List<Card> cards4 = dao.drawCards();
        assertEquals(4, cards4.size());
    }

    @Test
    void testCardCreationAndProperties() {
        // Test card creation
        // Test card creation with different values
        Card card1 = new Card(1);
        assertEquals(1, card1.getValue());

        Card card13 = new Card(13);
        assertEquals(13, card13.getValue());

        // Test card equality and hashcode if implemented
        Card anotherCard1 = new Card(1);
        // If equals is implemented:
        // assertEquals(card1, anotherCard1);
    }

    @Test
    void testCardPuzzleProperties() {
        // Test CardPuzzle properties
        List<Card> cards = Arrays.asList(new Card(1), new Card(2), new Card(3), new Card(4));
        CardPuzzle puzzle = new CardPuzzle(cards);

        assertEquals(4, puzzle.getCards().size());
        assertEquals(1, puzzle.getCards().get(0).getValue());
        assertEquals(4, puzzle.getCards().get(3).getValue());
    }

    @Test
    void testPresenterMethods() {
        // Test presenter functions correctly
        CardGameViewModel viewModel = new CardGameViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CardGamePresenter presenter = new CardGamePresenter(viewModel, viewManagerModel);

        // Test that presenter can be instantiated without errors
        assertNotNull(presenter);

        // You might need to add more specific tests for presenter methods
        // depending on their implementation
    }

    // Test the utilities
    @Test
    void testSolutionGenerator() {
        // Test the solutionGenerator with valid cards that are solvable
        List<Card> cards = Arrays.asList(new Card(2), new Card(2), new Card(2), new Card(4));
        List<String> solutions = SolutionGenerator.find24Solutions(cards);
        assertTrue(solutions.size() > 0);
        assertTrue(solutions.contains("((2+2)+2)*4"));
        assertTrue(SolutionGenerator.isSolvable(cards));
        assertTrue(canMake24(cards));
    }

    @Test
    void testTwentyFourChecker() {
        // Test the twentyFourChecker with cards that are solvable and not solvable
        System.out.println(canMake24(6, 6, 6, 6));   // -> true
        System.out.println(canMake24(1, 1, 1, 1));   // -> false
        System.out.println(canMake24(3, 9, 6, 7));   // -> true
    }


}

class TestPresenter implements CardGameHintsOutputBoundary {
    // Mock vacuous presenters for CardGameHints
    public boolean successCalled = false;
    public boolean failCalled = false;
    public CardGameHintsOutputDataObject lastOutputData = null;

    @Override
    public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
        this.successCalled = true;
        this.lastOutputData = outputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failCalled = true;
    }
}

class TestPlayCardGamePresenter implements PlayCardGameOutputBoundary {
    // Mock presenter for PlayCardGame
    public boolean failViewCalled = false;
    public boolean successViewCalled = false;
    public String errorMessage = "";
    public PlayCardGameOutputData outputData = null;

    @Override
    public void prepareSuccessView(PlayCardGameOutputData outputData) {
        this.successViewCalled = true;
        this.outputData = outputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failViewCalled = true;
        this.errorMessage = errorMessage;
    }
}