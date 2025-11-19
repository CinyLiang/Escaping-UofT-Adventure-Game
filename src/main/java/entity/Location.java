package main.java.entity;

import entity.Puzzle;

public class Location {
    private final String name;
    private final String description;
    private Puzzle puzzle;
    private boolean isLocked;
    private boolean completed;
    private String coordinates; // NSEW
    // might replace COORDINATES with the location name when selecting which locations to move to

    public Location(String name, String description, boolean locked, String coordinates, Puzzle puzzle) {
        this.name = name;
        this.description = description;
        this.isLocked = locked;
        this.coordinates = coordinates;
        this.puzzle = puzzle;
    }

    // setters and getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public boolean isLocked() {
        // can or cannot enter
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}