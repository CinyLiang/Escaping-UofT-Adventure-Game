package use_case.trivia_game;

import entity.TriviaPuzzle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriviaGameInteractorTest {
    private TriviaGameInteractor interactor;
    private MockTriviaDataAccess mockDataAccess;
    private MockTriviaPresenter mockPresenter;
    private TriviaPuzzle puzzle;

    @BeforeEach
    void setUp() {
        mockDataAccess = new MockTriviaDataAccess();
        mockPresenter = new MockTriviaPresenter();
        puzzle = new TriviaPuzzle(3);
        interactor = new TriviaGameInteractor(mockDataAccess, mockPresenter, puzzle);
    }

    @Test
    void testStartNewQuestionLoadsQuestion() {
        interactor.startNewQuestion();

        assertTrue(mockPresenter.questionPresented);
        assertNotNull(mockPresenter.lastOutputData);
        assertEquals("Test Question?", mockPresenter.lastOutputData.getQuestion());
        assertEquals("Answer the question:", mockPresenter.lastOutputData.getMessage());
        assertFalse(mockPresenter.lastOutputData.isPuzzleSolved());
    }

    @Test
    void testSubmitCorrectAnswer() {
        interactor.startNewQuestion();
        TriviaGameInputData inputData = new TriviaGameInputData(TriviaGameInputData.Action.START_NEW_QUESTION,"True");
        interactor.submitAnswer(inputData.getPlayerAnswer());

        assertTrue(mockPresenter.resultPresented);
        assertTrue(mockPresenter.lastOutputData.wasCorrect());
        assertEquals(1, mockPresenter.lastOutputData.getCorrectAnswers());
        assertEquals("Correct!", mockPresenter.lastOutputData.getMessage());
        assertFalse(mockPresenter.lastOutputData.isPuzzleSolved());
    }

    @Test
    void testSubmitIncorrectAnswer() {
        interactor.startNewQuestion();
        TriviaGameInputData inputData = new TriviaGameInputData(TriviaGameInputData.Action.START_NEW_QUESTION, "False");
        interactor.submitAnswer(inputData.getPlayerAnswer());

        assertTrue(mockPresenter.resultPresented);
        assertFalse(mockPresenter.lastOutputData.wasCorrect());
        assertEquals(0, mockPresenter.lastOutputData.getCorrectAnswers());
        assertEquals("Incorrect. Try another question.", mockPresenter.lastOutputData.getMessage());
    }

    @Test
    void testThreeCorrectAnswersSolvesPuzzle() {
        for (int i = 0; i < 3; i++) {
            interactor.startNewQuestion();
            TriviaGameInputData inputData = new TriviaGameInputData(TriviaGameInputData.Action.START_NEW_QUESTION,"True");
            interactor.submitAnswer(inputData.getPlayerAnswer());
        }

        assertTrue(mockPresenter.lastOutputData.isPuzzleSolved());
        assertEquals("Puzzle Complete! You earned a key!", mockPresenter.lastOutputData.getMessage());
        assertEquals(3, mockPresenter.lastOutputData.getCorrectAnswers());
    }

    @Test
    void testProgressTracking() {
        interactor.startNewQuestion();
        TriviaGameInputData correct = new TriviaGameInputData(TriviaGameInputData.Action.START_NEW_QUESTION,"True");
        interactor.submitAnswer(correct.getPlayerAnswer());
        assertEquals(1, puzzle.getCorrectAnswers());

        interactor.startNewQuestion();
        interactor.submitAnswer(correct.getPlayerAnswer());
        assertEquals(2, puzzle.getCorrectAnswers());
    }

    @Test
    void testRequiredAnswersCount() {
        interactor.startNewQuestion();
        assertEquals(3, mockPresenter.lastOutputData.getRequiredAnswers());
    }

    @Test
    void testCaseInsensitiveAnswers() {
        interactor.startNewQuestion();
        TriviaGameInputData inputData = new TriviaGameInputData(TriviaGameInputData.Action.START_NEW_QUESTION,"true");
        interactor.submitAnswer(inputData.getPlayerAnswer());

        assertTrue(mockPresenter.lastOutputData.wasCorrect());
    }

    @Test
    void testExitPuzzle() {
        interactor.exitPuzzle();
        assertTrue(mockPresenter.exitCalled);
    }

    private static class MockTriviaDataAccess implements TriviaGameDataAccessInterface {
        @Override
        public String[] fetchQuestion() {
            return new String[]{"Test Question?", "True"};
        }
    }

    private static class MockTriviaPresenter implements TriviaGameOutputBoundary {
        boolean questionPresented = false;
        boolean resultPresented = false;
        boolean exitCalled = false;
        TriviaGameOutputData lastOutputData = null;

        @Override
        public void presentQuestion(TriviaGameOutputData outputData) {
            questionPresented = true;
            lastOutputData = outputData;
        }

        @Override
        public void presentResult(TriviaGameOutputData outputData) {
            resultPresented = true;
            lastOutputData = outputData;
        }

        @Override
        public void exitPuzzle() {
            exitCalled = true;
        }
    }
}