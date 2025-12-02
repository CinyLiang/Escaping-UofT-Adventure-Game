package data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.save_progress.SaveProgressDataAccessInterface;
import use_case.view_progress.ViewProgressDataAccessInterface;

import java.util.*;

import use_case.save_progress.SaveProgressDataAccessInterface;
import use_case.view_progress.ViewProgressDataAccessInterface;

import java.io.*;

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
    private Set<String> solvedPuzzles = new HashSet<>();

//     private String currentLocation = "Unknown";
    private int keysCollected = 0;
//     private List<String> solvedPuzzles = new ArrayList<>();

    /**
     * Default constructor: use default file "progress.json"
     */
    public FileGameDataAccessObject() {
        this.filePath = "progress.json";
        loadProgress();
    } 

    /**
     * Constructor with custom file path (used by AppBuilder)
     */
    public FileGameDataAccessObject(String filePath) {
        this.filePath = filePath;
        loadProgress();
    }

    /**
     * Load JSON file if exists, otherwise keep default empty progress
     */
    private void loadProgress() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("No save file found → starting fresh.");
                return;
            }

            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) sb.append(sc.nextLine());
            sc.close();

            JSONObject json = new JSONObject(sb.toString());

            currentLocation = json.getString("location");
            keysCollected = json.getInt("keys");

            solvedPuzzles.clear();
            JSONArray puzzlesArray = json.getJSONArray("puzzles");
            for (Object o : puzzlesArray) solvedPuzzles.add((String) o);

        } catch (Exception e) {
            System.err.println("Failed to load progress → starting fresh.");
        }
    }

    // SAVE PROGRESS IMPLEMENTATION
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

    // VIEW PROGRESS IMPLEMENTATION
    @Override
    public String getLocation() {
        return currentLocation;
    }

    @Override
    public int getKeysCollected() {
        return keysCollected;
    }

    @Override
    public Set<String> getSolvedPuzzles() {
        return new HashSet<>(solvedPuzzles);
    }
}
