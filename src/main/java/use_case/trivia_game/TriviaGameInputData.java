package use_case.trivia_game;

public class TriviaGameInputData {
    private final String playerAnswer;

    public TriviaGameInputData(String playerAnswer, String playerName) {
        this.playerAnswer = playerAnswer;
    }

    public String getPlayerAnswer() {
        return playerAnswer;
    }

}