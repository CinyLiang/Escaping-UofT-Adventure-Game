package interface_adapter.navigate;

import java.util.HashSet;
import java.util.Set;

public class NavigateState{
    private Set<String> puzzlesSolved; // store puzzle name or id or whatever
    private int numberOfKeys;
    private String storyText;
    private String direction;

    // PROGRESS TEXT BECAUSE THE VIEW MODEL AINT SHIT
    private String progressText = "";
    private int targetNumberOfKeys = 2;

    // ALSO LOCATION BC WHY IS THE VIEW PROGRESS TAKING IN A DAI OBJECT ?????
    // oh.
    // actually never mind i get it but to be fair, i would imagine this to show in-game progress, not the one in the save file.
    private String location = "King College Circle";

    public NavigateState() {
        this.storyText = "";
        this.direction = "";
        this.puzzlesSolved = new HashSet<>();
        this.numberOfKeys = 0;
    }
    public String getProgressText() { return progressText; }
    public void setProgressText(String progressText) { this.progressText = progressText; }
    public String getStoryText() {
        return storyText;
    }
    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Set<String> getPuzzlesSolved() {
        return puzzlesSolved;
    }

    public void addPuzzleSolved(String puzzle) {
        puzzlesSolved.add(puzzle);
    }

    public void removePuzzleSolved(String puzzle) {
        puzzlesSolved.remove(puzzle);
    }

    public void resetPuzzlesSolved() {
        puzzlesSolved = new HashSet<>();
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    public void addNumberOfKeys() {
        this.numberOfKeys++;
    }

    public void addNumberOfKeys(int delta) {
        this.numberOfKeys += delta;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLocation() {
        this.location = "King College Circle";
    }

    public String getLocation() {
        return location;
    }

    public int getTargetNumberOfKeys() {
        return targetNumberOfKeys;
    }
}
