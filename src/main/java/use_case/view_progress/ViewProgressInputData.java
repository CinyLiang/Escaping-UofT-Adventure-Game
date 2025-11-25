package use_case.view_progress;

import java.util.Set;

public class ViewProgressInputData {
    String loc;
    int keys;
    Set<String> puzzles;

    public ViewProgressInputData(String loc, int keys, Set<String> puzzles) {
        this.loc = loc;
        this.keys = keys;
        this.puzzles = puzzles;
    }

    public String getLocation() {
        return loc;
    }

    public int getKeys() {
        return keys;
    }

    public Set<String> getPuzzles() {
        return puzzles;
    }
}
