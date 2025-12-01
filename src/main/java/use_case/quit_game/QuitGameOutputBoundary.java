package use_case.quit_game;

public interface QuitGameOutputBoundary {
    /**
     * Prepares the view to ask the user if they want to save.
     */
    void prepareSavePromptView();

    /**
     * Prepares the view to exit the program immediately (e.g., if no changes were made).
     */
    void prepareExitView();

    /**
     * Prepares the view to ask the user if they want to quit.
     */
    void prepareQuitView();
}
