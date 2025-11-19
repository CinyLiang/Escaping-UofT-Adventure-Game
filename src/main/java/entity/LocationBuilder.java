package main.java.entity;

public class LocationBuilder {
    private String name;
    private String description;
    private boolean locked;
    private String coordinates;
    private entity.Puzzle puzzle;

    public LocationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LocationBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocationBuilder setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public LocationBuilder setCoordinates(String coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public LocationBuilder setPuzzle(entity.Puzzle puzzle) {
        this.puzzle = puzzle;
        return this;
    }

    public Location createLocation() {
        return new Location(name, description, locked, coordinates, puzzle);
    }
}