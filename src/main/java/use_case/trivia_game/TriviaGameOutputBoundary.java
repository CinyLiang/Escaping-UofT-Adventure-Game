package use_case.trivia_game;

public interface TriviaGameOutputBoundary {
    void presentQuestion(TriviaGameOutputData outputData);
    void presentResult(TriviaGameOutputData outputData);
    void exitPuzzle();
}