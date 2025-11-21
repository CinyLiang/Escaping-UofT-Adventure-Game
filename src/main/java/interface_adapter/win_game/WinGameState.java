package interface_adapter.win_game;

public class WinGameState {
    private boolean won;
    private String message;
    private int keysCollected;

    public WinGameState() {
        this.won = false;
        this.message = "";
        this.keysCollected = 0;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getKeysCollected() {
        return keysCollected;
    }

    public void setKeysCollected(int keysCollected) {
        this.keysCollected = keysCollected;
    }
}