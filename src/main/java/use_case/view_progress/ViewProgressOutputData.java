package use_case.view_progress;

import java.util.Set;

public class ViewProgressOutputData {
    private final String location;
    private final int keysCollected;
    private final Set<String> solvedPuzzles;

    public ViewProgressOutputData(String location, int keysCollected, Set<String> solvedPuzzles) {
        this.location = location;
        this.keysCollected = keysCollected;
        this.solvedPuzzles = solvedPuzzles;
    }

    public String getLocation() { return location; }
    public int getKeysCollected() { return keysCollected; }
    public Set<String> getSolvedPuzzles() { return solvedPuzzles; }
}
