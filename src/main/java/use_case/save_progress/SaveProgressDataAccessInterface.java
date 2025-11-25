package use_case.save_progress;

import java.util.Set;

/**
 * Gateway for saving the current game progress.
 * The implementation is responsible for knowing where the current game state is
 * and how to serialize it (e.g., to JSON).
 */
public interface SaveProgressDataAccessInterface {

    /**
     * Save the current game progress.
     *
     * @return true if saving succeeded, false otherwise
     */
    boolean saveProgress(String currentLocation, int keysCollected, Set<String> solvedPuzzles);
}
