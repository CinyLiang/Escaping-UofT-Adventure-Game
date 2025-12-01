package data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.save_progress.SaveProgressDataAccessInterface;
import use_case.view_progress.ViewProgressDataAccessInterface;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

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
          
   public FileGameDataAccessObject(String filePath) {
        this.filePath = filePath;
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
    public boolean saveProgress() {
        try {
            JSONObject json = new JSONObject();
            json.put("location", currentLocation);
            json.put("keys", keysCollected);
            json.put("puzzles", solvedPuzzles);

            FileWriter writer = new FileWriter(filePath);
            writer.write(json.toString(4));  // pretty formatted JSON
            writer.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
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
