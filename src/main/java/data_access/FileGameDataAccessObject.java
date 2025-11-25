package data_access;

import use_case.save_progress.SaveProgressDataAccessInterface;
import use_case.view_progress.ViewProgressDataAccessInterface;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * File-based data access object for saving and loading game progress.
 * Implements both SaveProgressDataAccessInterface and ViewProgressDataAccessInterface.
 */
public class FileGameDataAccessObject implements
        SaveProgressDataAccessInterface,
        ViewProgressDataAccessInterface {

    private final String filePath;
    private static final String DEFAULT_LOCATION = "Kings College Circle";

    // Current game state (in-memory)
    private String currentLocation = DEFAULT_LOCATION;
    private int keysCollected = 0;
    private Set<String> solvedPuzzles = new HashSet<>();

    /**
     * Creates a new FileGameDataAccessObject.
     * @param filePath the path to the save file
     */
    public FileGameDataAccessObject(String filePath) {
        this.filePath = filePath;
        // Try to load existing save on initialization
        loadProgress();
    }

    /**
     * Saves the current game progress to a file.
     * @return true if save was successful, false otherwise
     */
    @Override
    public boolean saveProgress(String currentLocation, int keysCollected, Set<String> solvedPuzzles) {
        this.currentLocation = currentLocation;
        this.keysCollected = keysCollected;
        this.solvedPuzzles = solvedPuzzles;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(this.currentLocation);
            writer.println(this.keysCollected);
            writer.println(String.join(",", this.solvedPuzzles));
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads game progress from the save file.
     * If no save file exists, uses default starting values.
     */
    private void loadProgress() {
        File file = new File(filePath);
        if (!file.exists()) {
            return; // No save file exists yet, use defaults
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            currentLocation = reader.readLine();
            if (currentLocation == null) {
                currentLocation = DEFAULT_LOCATION;
            }

            String keysLine = reader.readLine();
            if (keysLine != null) {
                keysCollected = Integer.parseInt(keysLine);
            }

            String puzzlesLine = reader.readLine();
            if (puzzlesLine != null && !puzzlesLine.isEmpty()) {
                String[] puzzles = puzzlesLine.split(",");
                solvedPuzzles = new HashSet<>();
                for (String puzzle : puzzles) {
                    if (!puzzle.trim().isEmpty()) {
                        solvedPuzzles.add(puzzle.trim());
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to load game: " + e.getMessage());
            // Reset to defaults on error
            currentLocation = DEFAULT_LOCATION;
            keysCollected = 0;
            solvedPuzzles = new HashSet<>();
        }
    }

    /**
     * Gets the player's current location.
     * @return the current location name
     */
    @Override
    public String getLocation() {
        return currentLocation;
    }

    /**
     * Gets the number of keys the player has collected.
     * @return the number of keys
     */
    @Override
    public int getKeysCollected() {
        return keysCollected;
    }

    /**
     * Gets the set of puzzles the player has solved.
     * @return a copy of the solved puzzles set
     */
    @Override
    public Set<String> getSolvedPuzzles() {
        return new HashSet<>(solvedPuzzles);
    }

    // Methods to update state (called by use cases when game state changes)

    /**
     * Updates the player's current location.
     * @param location the new location name
     */
    public void setLocation(String location) {
        this.currentLocation = location;
    }

    /**
     * Updates the number of keys collected.
     * @param keys the new key count
     */
    public void setKeysCollected(int keys) {
        this.keysCollected = keys;
    }

    /**
     * Adds a puzzle to the solved puzzles set.
     * @param puzzleName the name of the solved puzzle
     */
    public void addSolvedPuzzle(String puzzleName) {
        this.solvedPuzzles.add(puzzleName);
    }

    /**
     * Clears all game progress, resetting to starting values.
     */
    public void clearProgress() {
        this.currentLocation = DEFAULT_LOCATION;
        this.keysCollected = 0;
        this.solvedPuzzles.clear();
    }
}