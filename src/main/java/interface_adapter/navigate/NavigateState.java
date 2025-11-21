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
}

// vanessa: move locationMap here
// add setter getter, update location map function
// nav presenter updates lcoation map
// nav controller should call presenter
// nav ui should call controller
// set up input and output boundary interface
// presenter implements output
// input takes player input from ui (navigate view) and passes to controller