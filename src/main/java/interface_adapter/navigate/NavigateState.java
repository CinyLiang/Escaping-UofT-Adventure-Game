package interface_adapter.navigate;

import java.util.HashSet;
import java.util.Set;

public class NavigateState{
//    private String currentLocationName; // not sure if it's necessary
    private Set<String> puzzlesSolved; // store puzzle name or id or whatever
    private int numberOfKeys;
    private String storyText;
    private String direction;


    public NavigateState() {
//        this.currentLocationName = "";
        this.storyText = "";
        this.direction = "";
        this.puzzlesSolved = new HashSet<>();
        this.numberOfKeys = 0;
    }

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
}
