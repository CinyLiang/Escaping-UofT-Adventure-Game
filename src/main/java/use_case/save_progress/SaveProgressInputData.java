package use_case.save_progress;

import java.util.Set;

/**
 * Input data for the Save Progress use case.
 * Currently empty because saving only depends on the current game state.
 */
public class SaveProgressInputData {
    private String location;
    private int numberOfKeys;
    private Set<String> puzzlesSolved;

    public SaveProgressInputData(String location, int numberOfKeys, Set<String> puzzlesSolved) {
        this.location = location;
        this.numberOfKeys = numberOfKeys;
        this.puzzlesSolved = puzzlesSolved;
    }

    public String getLocation() {
        return location;
    }
    public int  getNumberOfKeys() {
        return numberOfKeys;
    }
    public Set<String> getPuzzlesSolved() {
        return puzzlesSolved;
    }
}
