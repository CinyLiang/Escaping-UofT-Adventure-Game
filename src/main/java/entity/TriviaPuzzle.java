package entity;

/**
 * A puzzle entity that represents a trivia game.
 * Players must answer a certain number of trivia questions correctly to solve the puzzle.
 */
public class TriviaPuzzle extends Puzzle {
    private String question;
    private String correctAnswer;
    private int correctAnswers;
    private final int requiredCorrectAnswers;

    /**
     * Creates a new TriviaPuzzle that requires a certain number of correct answers.
     * @param requiredCorrectAnswers the number of correct answers needed to complete the puzzle
     */
    public TriviaPuzzle(int requiredCorrectAnswers) {
        super("", "Answer trivia questions correctly to earn a key", "trivia");
        this.requiredCorrectAnswers = requiredCorrectAnswers;
        this.correctAnswers = 0;
    }

    /**
     * Sets the current trivia question and its correct answer.
     * @param question the trivia question text
     * @param correctAnswer the correct answer to the question
     */
    public void setCurrentQuestion(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets the current trivia question.
     * @return the question text
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the number of correct answers so far.
     * @return the count of correct answers
     */
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Gets the total number of correct answers required to solve the puzzle.
     * @return the required correct answers
     */
    public int getRequiredCorrectAnswers() {
        return requiredCorrectAnswers;
    }

    /**
     * Checks if the player's answer is correct and updates progress.
     * @param playerAnswer the answer submitted by the player
     * @return true if the answer was correct, false otherwise
     */
    public boolean checkAnswer(String playerAnswer) {
        boolean isCorrect = playerAnswer.equalsIgnoreCase(correctAnswer);
        if (isCorrect) {
            correctAnswers++;
            if (correctAnswers >= requiredCorrectAnswers) {
                markSolved();
            }
        }
        return isCorrect;
    }

    /**
     * Resets the puzzle progress to zero correct answers.
     * Useful if you want to let players retry the entire puzzle.
     */
    public void resetProgress() {
        this.correctAnswers = 0;
    }
}