package entity;

import java.util.*;

import entity.Location;

public class Player {

//    private final String name;
//    ,,,i don't think we ever ask for player's name tbh
    private Location currentLocation;
    private int keysCollected;
    private final Set<String> puzzlesSolved;
//     imo we could do a Map<String, boolean> where it maps Puzzle names to T/F based on what keys are obtained
//    and in "view progress", we can mention something like "You have solved Puzzle X. You have NOT solved Puzzle Y" or sth
//    private final List<Key> keyInventory; // sort of unecessary.
//    we do not end up needing a key entity since we do not care if the key is attached to a certain location
//    besides we can keep track of solved locations/puzzles from PuzzlesSolved

    public Player(Location startLocation) {
//        this.name = name;
        this.currentLocation = startLocation;
        this.keysCollected = 0;
        this.puzzlesSolved = new HashSet<>();
//        this.keyInventory = new ArrayList<>();
    }

    // no need :""""" if we're mimicking CA lab (and we are indeed doing that yes),
    // any changes made by the user will alter the state in the next view
    // each view will have its own "Player" state containing details like their location and puzzles solved
    // which dictates which view is presented and what is presented
    // there is no need for additional functions in Player that allows movement of any sort.

//    public boolean move(String direction) {
//        if (currentLocation.getExits().containsKey(direction)) {
//            Location next = currentLocation.getExits().get(direction);
//
//            if (next.getName().equals("Convocation Hall") && keysCollected < 3) {
//                System.out.println("YOU CANNOT ENTER HERE, Convocation Hall is locked. Collect 3 keys to enter.");
//                return false;
//            }
//
//            currentLocation = next;
//            return true;
//        }
//        return false;
//    }

//    public boolean enterLocation(Location location) {
//        this.currentLocation = location;
//        return true;
//    }

//    public void exitLocation() {
//    }

    public void addKey() {
//        keyInventory.add(key);
        keysCollected++;
    }

//    public boolean hasKey(String keyOrigin) {
//        return keyInventory.stream().anyMatch(k -> k.getOrigin().equals(keyOrigin));
//    }
//    i'm assuming this is to check if there exists a key for a certain puzzle?
//    if it's to check access to CC, then getting no. of keys should suffice
//    if it's to check progress, then puzzlessolved should do the trick (?) and comparing it to a seperate list of puzzles
//    (otherwise we can go with the Map idea)

    public void markPuzzleSolved(String puzzleId) {
        puzzlesSolved.add(puzzleId);
    }

    public boolean isPuzzleSolved(String puzzleId) {
        return puzzlesSolved.contains(puzzleId);
    }

    public boolean canEnterConvocationHall() {
        return keysCollected >= 2;
    } // update with our current no. of games

//    public String getName() {
//        return name;
//    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public int getKeysCollected() {
        return keysCollected;
    }

//    public List<Key> getKeyInventory() {
//        return keyInventory;
//    }

    public String getStatus() {
        return "\nLocation: " + currentLocation.getName() +
                "\nKeys collected: " + keysCollected +
                "\nSolved puzzles: " + puzzlesSolved;
//                "\nInventory: " + keyInventory;
    }
}
