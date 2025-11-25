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
    private String name;
    /*   private int suceessesToPass;*/

    /**
     * Creates a new puzzel with the given id.
     * @param id the puzzle id
     */

    public Puzzle(String id, String description, String name/*, int successes*/) {
        this.id = id;
        this.description = description;
        this.isSolved = false;
        this.attempts = 0;
        this.name = name;
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

    public String getName() {return name;}

    /*public int getsuceessesToPass() {
        return suceessesToPass;
    }*/

    // Marking a puzzle solved
    public void markSolved() {
        isSolved = true;
    }

    public boolean isSolved() {return isSolved;}

}