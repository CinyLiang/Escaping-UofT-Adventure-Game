package use_case.trivia_game;

public interface TriviaGameDataAccessInterface {
    /**
     * Fetches a new trivia question from the API
     * @return array where [0] is question, [1] is correct answer
     */
    String[] fetchQuestion();
}