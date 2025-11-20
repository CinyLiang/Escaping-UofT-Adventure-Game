package interface_adapter.trivia_game;

import use_case.trivia_game.TriviaGameInputBoundary;
import use_case.trivia_game.TriviaGameInputData;

public class TriviaGameController {
    private final TriviaGameInputBoundary interactor;

    public TriviaGameController(TriviaGameInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void startNewQuestion() {
        interactor.startNewQuestion();
    }

    public void submitAnswer(String answer, String playerName) {
        TriviaGameInputData inputData = new TriviaGameInputData(answer, playerName);
        interactor.submitAnswer(inputData);
    }
}