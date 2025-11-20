package interface_adapter.quit_game;

/**
 * The controller for the Login Use Case.
 */
public class QuitGameController {
    private Runnable showSaveDialog;
    private Runnable showQuitDialog;

    public void setShowSaveDialog(Runnable r) {
        this.showSaveDialog = r;
    }

    public void setShowQuitDialog(Runnable r) {
        this.showQuitDialog = r;
    }

    public void showSave() {
        showSaveDialog.run();
    }

    public void showQuit() {
        showQuitDialog.run();
    }
}