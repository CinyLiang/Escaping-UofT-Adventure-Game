package entity;

import java.util.*;

public class Player {

    private Location currentLocation;
    private int keysCollected;
    private final Set<String> puzzlesSolved;

    public Player(Location startLocation) {
        this.currentLocation = startLocation;
        this.keysCollected = 0;
        this.puzzlesSolved = new HashSet<>();
    }

    public void markPuzzleSolved(String puzzleId) {
        puzzlesSolved.add(puzzleId);
    }

    public boolean isPuzzleSolved(String puzzleId) {
        return puzzlesSolved.contains(puzzleId);
    }

    public boolean canEnterConvocationHall() {
        return keysCollected >= 2;
    } // update with our current no. of games

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public int getKeysCollected() {
        return keysCollected;
    }

    // added for testing
    public void addKey() {
        this.keysCollected++;
    }
}