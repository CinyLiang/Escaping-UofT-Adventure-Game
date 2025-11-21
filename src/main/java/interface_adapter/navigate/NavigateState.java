package interface_adapter.navigate;

import entity.Location;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NavigateState{
//    private String currentLocationName; // not sure if it's necessary
    private Set<String> puzzlesSolved; // store puzzle name or id or whatever
    private int numberOfKeys;
    private Map<String, Location> locationMap;


    public NavigateState() {
//        this.currentLocationName = "";
        this.puzzlesSolved = new HashSet<>();
        this.numberOfKeys = 0;
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

    public void addNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys += numberOfKeys;
    }

    public void setLocationMap(Map<String, Location> locationMap) {
        this.locationMap = locationMap;
    }

    public Map<String, Location> getLocationMap() {
        return locationMap;
    }

    public void updateLocationMap(String locationName, Location location) {
        this.locationMap.put(locationName, location);
    }

    // HELPER METHODS
    // i'm moving this out of Game.java for now, we can decide later where it should go
    private void createLocationMap() {
        locationMap = new HashMap<>();
        // Initialize locations and add to locationMap
        // location 1: kings college circle (middle)
        Location kcc = new Location("Kings College Circle", "The central lawn of the university.", "start");
        locationMap.put("Kings College Circle", kcc);

        // location 2: knox college (west)
        Location knox = new Location("Knox College", "Theological college with a haunting interior.", "west");
        knox.setPuzzle(unknown);

        // location 3: gerstein library (east)
        Location gerstein = new Location("Gerstein Library", "Quiet library.", "east");
        gerstein.setPuzzle(cardGame);

        // location 4: university college (north)
        Location uc = new Location("University College", "Founding college with an ornate door.", "north");
        uc.setPuzzle(trivia);

        // location 5: convocation hall (south)
        Location conHall = new Location("Convocation Hall", "Rotunda with three locked doors.", "south");
        conHall.setPuzzle(threeKeys);
        conHall.setIsLocked(false);
    }
}

// vanessa: move locationMap here
// add setter getter, update location map function
// nav presenter updates lcoation map
// nav controller should call presenter
// nav ui should call controller
// set up input and output boundary interface
// presenter implements output
// input takes player input from ui (navigate view) and passes to controller