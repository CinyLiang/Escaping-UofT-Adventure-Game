package entity;

/**
 * An entity representing a general puzzel question.
 */
public abstract class Puzzle {
    private final String id;
    private final String description;
    private boolean isSolved;
    private int attempts;
//    private string hint;
 /*   private int suceessesToPass;*/

    /**
     * Creates a new puzzel with the given id.
     * @param id the puzzle id
     */

    public Puzzle(String id, String description/*, int successes*/) {
        this.id = id;
        this.description = description;
        this.isSolved = false;
        this.attempts = 0;
//        this.suceessesToPass = successes;
//        this.hint = this.solve();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDescription() { return description;
    }

    public boolean getIsSolved() {
        return isSolved;
    }

    public int getAttempts() {return attempts;}

    /*public int getsuceessesToPass() {
        return suceessesToPass;
    }*/

    // Abstract methods
    public abstract String solve(); // Abstract method generating one possible solution to the puzzle

    public abstract boolean validateSolution(String solution); // Abstract method validating whether a solution is correct or not

    // Returning a string of hints for a puzzle
//    public String giveHint() {
//        return this.hint;
//    }

    // Marking a puzzle solved
    public void markSolved() {
        isSolved = true;
    }

    // Recording an attempt by the player
    public boolean attemptSolve(String inputSolution) {
        attempts++;
        if (validateSolution(inputSolution)) {
            markSolved();
        }
        return isSolved;
    }

}