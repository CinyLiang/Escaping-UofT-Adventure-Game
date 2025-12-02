package interface_adapter.trivia_game;

import use_case.trivia_game.TriviaGameInputBoundary;
import use_case.trivia_game.TriviaGameInputData;

public class TriviaGameController {
    private final TriviaGameInputBoundary interactor;

    public TriviaGameController(TriviaGameInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void startNewQuestion() {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.START_NEW_QUESTION
        );
        interactor.execute(inputData);
    }

    public void submitAnswer(String answer) {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.SUBMIT_ANSWER,
                answer
        );
        interactor.execute(inputData);
    }

    public void exitPuzzle() {
        TriviaGameInputData inputData = new TriviaGameInputData(
                TriviaGameInputData.Action.EXIT_PUZZLE
        );
        interactor.execute(inputData);
    }
}