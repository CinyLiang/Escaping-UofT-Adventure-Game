package use_case.trivia_game;

public interface TriviaGameInputBoundary {
    void startNewQuestion();
    void submitAnswer(TriviaGameInputData inputData);
}