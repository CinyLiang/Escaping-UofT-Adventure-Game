package use_case.trivia_game;

public class TriviaGameOutputData {
    private final String question;
    private final boolean wasCorrect;
    private final int correctAnswers;
    private final int requiredAnswers;
    private final boolean puzzleSolved;
    private final String message;

    public TriviaGameOutputData(String question, boolean wasCorrect,
                                int correctAnswers, int requiredAnswers,
                                boolean puzzleSolved, String message) {
        this.question = question;
        this.wasCorrect = wasCorrect;
        this.correctAnswers = correctAnswers;
        this.requiredAnswers = requiredAnswers;
        this.puzzleSolved = puzzleSolved;
        this.message = message;
    }

    public String getQuestion() {
        return question;
    }

    public boolean wasCorrect() {
        return wasCorrect;
    }
    public int getCorrectAnswers() {
        return correctAnswers;
    }
    public int getRequiredAnswers() {
        return requiredAnswers;
    }
    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }
    public String getMessage() {
        return message;
    }
}