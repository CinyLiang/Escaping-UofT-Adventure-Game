package interface_adapter.quit_game;

public class QuitGameState {
    private boolean quitDialogVisible = false;
    private boolean isSaveDialogRequired = false;
    private boolean shouldExitGame = false;

    public QuitGameState() {}

    // Copy constructor (standard practice in CA to avoid mutating state directly)
    public QuitGameState(QuitGameState copy) {
        this.isSaveDialogRequired = copy.isSaveDialogRequired;
    }

    public boolean isSaveDialogRequired() {
        return isSaveDialogRequired;
    }

    public void setSaveDialogRequired(boolean saveDialogRequired) {
        isSaveDialogRequired = saveDialogRequired;
    }

    public boolean getShouldExitGame() {
        return shouldExitGame;
    }

    public void setShouldExitGame(boolean shouldExitGame) {
        this.shouldExitGame = shouldExitGame;
    }

    public boolean isQuitDialogVisible() {
        return quitDialogVisible;
    }

    public void setQuitDialogVisible(boolean quitDialogVisible) {
        this.quitDialogVisible = quitDialogVisible;
    }
}