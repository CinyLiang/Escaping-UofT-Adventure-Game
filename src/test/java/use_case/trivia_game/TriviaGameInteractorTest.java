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
    void testExecuteWithStartNewQuestionAction() {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.START_NEW_QUESTION
        );

        interactor.execute(inputData);

        assertTrue(mockPresenter.questionPresented);
        assertNotNull(mockPresenter.lastOutputData);
        assertEquals("Test Question?", mockPresenter.lastOutputData.getQuestion());
    }

    @Test
    void testExecuteWithSubmitAnswerAction() {
        interactor.startNewQuestion();

        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.SUBMIT_ANSWER,
                "True"
        );

        interactor.execute(inputData);

        assertTrue(mockPresenter.resultPresented);
        assertTrue(mockPresenter.lastOutputData.wasCorrect());
    }

    @Test
    void testExecuteWithExitPuzzleAction() {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.EXIT_PUZZLE
        );

        interactor.execute(inputData);

        assertTrue(mockPresenter.exitCalled);
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
        interactor.submitAnswer("True");

        assertTrue(mockPresenter.resultPresented);
        assertTrue(mockPresenter.lastOutputData.wasCorrect());
        assertEquals(1, mockPresenter.lastOutputData.getCorrectAnswers());
        assertEquals("Correct!", mockPresenter.lastOutputData.getMessage());
        assertFalse(mockPresenter.lastOutputData.isPuzzleSolved());
    }

    @Test
    void testSubmitIncorrectAnswer() {
        interactor.startNewQuestion();
        interactor.submitAnswer("False");

        assertTrue(mockPresenter.resultPresented);
        assertFalse(mockPresenter.lastOutputData.wasCorrect());
        assertEquals(0, mockPresenter.lastOutputData.getCorrectAnswers());
        assertEquals("Incorrect. Try another question.", mockPresenter.lastOutputData.getMessage());
    }

    @Test
    void testThreeCorrectAnswersSolvesPuzzle() {
        for (int i = 0; i < 3; i++) {
            interactor.startNewQuestion();
            interactor.submitAnswer("True");
        }

        assertTrue(mockPresenter.lastOutputData.isPuzzleSolved());
        assertEquals("Puzzle Complete! You earned a key!", mockPresenter.lastOutputData.getMessage());
        assertEquals(3, mockPresenter.lastOutputData.getCorrectAnswers());
    }

    @Test
    void testProgressTracking() {
        interactor.startNewQuestion();
        interactor.submitAnswer("True");
        assertEquals(1, puzzle.getCorrectAnswers());

        interactor.startNewQuestion();
        interactor.submitAnswer("True");
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
        interactor.submitAnswer("true");

        assertTrue(mockPresenter.lastOutputData.wasCorrect());
    }

    @Test
    void testCaseInsensitiveAnswersUppercase() {
        interactor.startNewQuestion();
        interactor.submitAnswer("TRUE");

        assertTrue(mockPresenter.lastOutputData.wasCorrect());
    }

    @Test
    void testCaseInsensitiveAnswersMixedCase() {
        interactor.startNewQuestion();
        interactor.submitAnswer("TrUe");

        assertTrue(mockPresenter.lastOutputData.wasCorrect());
    }

    @Test
    void testExitPuzzle() {
        interactor.exitPuzzle();
        assertTrue(mockPresenter.exitCalled);
    }

    @Test
    void testPuzzleNotSolvedAfterTwoCorrectAnswers() {
        interactor.startNewQuestion();
        interactor.submitAnswer("True");

        interactor.startNewQuestion();
        interactor.submitAnswer("True");

        assertFalse(mockPresenter.lastOutputData.isPuzzleSolved());
        assertEquals(2, mockPresenter.lastOutputData.getCorrectAnswers());
    }

    @Test
    void testIncorrectAnswerDoesNotIncrementProgress() {
        interactor.startNewQuestion();
        interactor.submitAnswer("False");

        assertEquals(0, puzzle.getCorrectAnswers());

        interactor.startNewQuestion();
        interactor.submitAnswer("False");

        assertEquals(0, puzzle.getCorrectAnswers());
    }

    @Test
    void testGetPlayerAnswerFromInputData() {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.SUBMIT_ANSWER,
                "True"
        );

        assertEquals("True", inputData.getPlayerAnswer());
    }

    @Test
    void testGetActionFromInputData() {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.START_NEW_QUESTION
        );

        assertEquals(TriviaGameInputData.Action.START_NEW_QUESTION, inputData.getAction());
    }

    @Test
    void testInputDataWithoutPlayerAnswer() {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.EXIT_PUZZLE
        );

        assertNull(inputData.getPlayerAnswer());
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