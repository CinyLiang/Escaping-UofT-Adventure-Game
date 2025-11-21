package interface_adapter.trivia_game;

public class TriviaGameState {
    private String question;
    private String message;
    private int correctAnswers;
    private int requiredAnswers;
    private boolean puzzleSolved;
    private boolean answeredCurrentQuestion;


    public TriviaGameState() {
        this.question = "";
        this.message = "";
        this.correctAnswers = 0;
        this.requiredAnswers = 3;
        this.puzzleSolved = false;
        this.answeredCurrentQuestion = false;
    }

    public String getQuestion() { return question; }

    public void setQuestion(String question) {
        this.question = question;
        this.answeredCurrentQuestion = false;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getRequiredAnswers() { return requiredAnswers; }
    public void setRequiredAnswers(int requiredAnswers) {
        this.requiredAnswers = requiredAnswers;
    }

    public boolean isPuzzleSolved() { return puzzleSolved; }
    public void setPuzzleSolved(boolean puzzleSolved) {
        this.puzzleSolved = puzzleSolved;
    }

    public boolean hasAnsweredCurrentQuestion() {
        return answeredCurrentQuestion;
    }

    public void setAnsweredCurrentQuestion(boolean answered) {
        this.answeredCurrentQuestion = answered;
    }
}