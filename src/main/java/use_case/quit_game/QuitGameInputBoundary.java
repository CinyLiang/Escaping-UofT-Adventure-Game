package use_case.quit_game;

public interface QuitGameInputBoundary {
    // Executes quit with or without save
    void execute();

    // Shows quit game prompt
    void executeRequestQuit();
}
