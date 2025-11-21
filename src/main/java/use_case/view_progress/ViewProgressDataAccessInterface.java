package use_case.view_progress;

public interface ViewProgressDataAccessInterface {
    String getLocation();
    int getKeysCollected();
    java.util.Set<String> getSolvedPuzzles();
}
