package entity;

public class TriviaPuzzle implements Puzzle {
    private String question;
    private String correctAnswer;
    private int correctAnswers;
    private int requiredCorrectAnswers;
    private boolean isSolved;

    public TriviaPuzzle(int requiredCorrectAnswers) {
        this.requiredCorrectAnswers = requiredCorrectAnswers;
        this.correctAnswers = 0;
        this.isSolved = false;
    }

    public void setCurrentQuestion(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String playerAnswer) {
        boolean isCorrect = playerAnswer.equalsIgnoreCase(correctAnswer);
        if (isCorrect) {
            correctAnswers++;
            if (correctAnswers >= requiredCorrectAnswers) {
                isSolved = true;
            }
        }
        return isCorrect;
    }

    @Override
    public boolean isSolved() {
        return isSolved;
    }

}