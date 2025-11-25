package use_case.card_game_hints;

import entity.Card;
import entity.CardPuzzle;
import interface_adapter.ViewManagerModel;
import interface_adapter.card_game_hints.CardGameHintsPresenter;
import interface_adapter.play_card_game.CardGamePresenter;
import interface_adapter.play_card_game.CardGameViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

public class CardGameHintsInteractorTest {
    @Test
    void successTest() {

        CardGameViewModel cardGameViewModel = new CardGameViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Create test presenter that captures calls
        TestPresenter presenter = new TestPresenter();

        // Create the interactor we're actually testing
        CardGameHintsInteractor interactor = new CardGameHintsInteractor((CardGameHintsOutputBoundary) presenter);

        // Create input data (you'll need to create a CardPuzzle with test cards)
        List<Card> cards = Arrays.asList(new Card(2), new Card(4), new Card(6), new Card(3));
        CardPuzzle puzzle = new CardPuzzle(cards);
        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        interactor.execute(input);
        assertTrue(presenter.successCalled);
        assertFalse(presenter.failCalled);
        assertNotNull(presenter.lastOutputData);
    }


    @Test
    void getHintsTest() {
        CardGameHintsOutputDataObject output = new CardGameHintsOutputDataObject("Test Hint");
        assertEquals("Test Hint", output.getHint());
    }

    @Test
    void testExtractInnerEmpty() {

        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner("");
        assertEquals("a simple calculation", result);
    }

    @Test
    void testExtractInnerNull() {
        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner(null);
        assertEquals("a simple calculation", result);
    }

    @Test
    void testExtractInnerStrange() {
        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner(")(");
        assertEquals(")(", result);
    }


    @Test
    void testExtractInnerLarge() {
        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner("6+7+8+9+10");
        assertEquals("6+7+8+", result);
    }

    @Test
    void testExceptionWhenGenerateHintFails() {
        CardGameHintsOutputBoundary failurePresenter = new CardGameHintsOutputBoundary() {
            @Override
            public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
                fail("Should not succeed when generateHint fails");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("Failed to get hint"));
                assertTrue(error.contains("No solutions found"));
            }
        };

        List<Card> cards = Arrays.asList(new Card(1), new Card(1), new Card(1), new Card(1));
        CardPuzzle puzzle = new CardPuzzle(cards);

        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        CardGameHintsInteractor interactor = new CardGameHintsInteractor(failurePresenter);
        CardGameHintsInteractor spyInteractor = Mockito.spy(interactor);

        doThrow(new RuntimeException("No solutions found")).when(spyInteractor).generateHint(cards);

        spyInteractor.execute(input);
    }
}


class TestPresenter implements CardGameHintsOutputBoundary {
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

//        private static class TestCardGameHintsPresenter implements CardGameHintsOutputBoundary {
//            public boolean successCalled = false;
//            public boolean failCalled = false;
//            public CardGameHintsOutputDataObject lastOutputData;
//            public String lastErrorMessage;
//
//            public TestCardGameHintsPresenter(CardGameViewModel viewModel, ViewManagerModel viewManagerModel) {
//                // You might not even need these if you're just testing the interactor logic
//            }
//            @Override
//            public void prepareSuccessView(CardGameHintsOutputDataObject output) {
//                assertEquals("2+4",  output.getHint() );
//                assertEquals(output.getHint(), cardGameViewModel.getState().getHint());
//            }
//
//            @Override
//            public void prepareFailView(String message) {
//                fail("Use case failure is unexpected.");
//            }

//    }

//    void testExtractInner() {
//        String sampleSol = "(8+2)*2+4";
//        assertEquals("8+2", CardGameHintsInteractor.extractInner(sampleSol));
//    }
//
//    void testGenerateHint() {
//        Card c1 = new Card(4);
//        Card c2 = new Card(5);
//        Card c3 = new Card(6);
//        Card c4 = new Card(6);
//        List<Card> cards = Arrays.asList(c1, c2, c3, c4);
//
//        CardGameHintsInteractor interactor = new CardGameHintsInteractor();
//        String solutions = interactor.getSampleSolution(cards);
//    }
//}
