package use_case.trivia_game;

import entity.TriviaPuzzle;

public class TriviaGameInteractor implements TriviaGameInputBoundary {
    private final TriviaGameDataAccessInterface dataAccess;
    private final TriviaGameOutputBoundary presenter;
    private final TriviaPuzzle puzzle;

    public TriviaGameInteractor(TriviaGameDataAccessInterface dataAccess,
                                TriviaGameOutputBoundary presenter,
                                TriviaPuzzle puzzle) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.puzzle = puzzle;
    }

    @Override
    public void startNewQuestion() {

        String[] questionData = dataAccess.fetchQuestion();
        String question = questionData[0];
        String correctAnswer = questionData[1];

        puzzle.setCurrentQuestion(question, correctAnswer);


        TriviaGameOutputData outputData = new TriviaGameOutputData(
                question, false, puzzle.getCorrectAnswers(),
                puzzle.getRequiredCorrectAnswers(), puzzle.isSolved(),
                "Answer the question:"
        );
        presenter.presentQuestion(outputData);
    }

    @Override
    public void submitAnswer(TriviaGameInputData inputData) {

        boolean isCorrect = puzzle.checkAnswer(inputData.getPlayerAnswer());

        String message;
        if (isCorrect) {
            message = "Correct!";
            if (puzzle.isSolved()) {
                message = "Puzzle Complete! You earned a key!";
            }
        } else {
            message = "Incorrect. Try another question.";
        }


        TriviaGameOutputData outputData = new TriviaGameOutputData(
                puzzle.getQuestion(), isCorrect,
                puzzle.getCorrectAnswers(), puzzle.getRequiredCorrectAnswers(),
                puzzle.isSolved(), message
        );

        presenter.presentResult(outputData);
    }

    @Override
    public void exitPuzzle() {
        presenter.exitPuzzle();
    }
}