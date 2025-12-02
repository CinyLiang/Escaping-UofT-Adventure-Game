package use_case.trivia_game;

public class TriviaGameInputData {
    public enum Action {
        START_NEW_QUESTION,
        SUBMIT_ANSWER,
        EXIT_PUZZLE
    }

    private final Action action;
    private final String playerAnswer;

    public TriviaGameInputData(Action action) {
        this.action = action;
        this.playerAnswer = null;
    }

    public TriviaGameInputData(Action action, String playerAnswer) {
        this.action = action;
        this.playerAnswer = playerAnswer;
    }

    public Action getAction() {
        return action;
    }

    public String getPlayerAnswer() {
        return playerAnswer;
    }
}