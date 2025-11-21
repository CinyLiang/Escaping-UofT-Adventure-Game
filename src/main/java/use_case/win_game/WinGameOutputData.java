package use_case.win_game;

public class WinGameOutputData {
    private final boolean won;
    private final int keysCollected;
    private final int requiredKeys;
    private final String message;

    public WinGameOutputData(boolean won, int keysCollected, int requiredKeys, String message) {
        this.won = won;
        this.keysCollected = keysCollected;
        this.requiredKeys = requiredKeys;
        this.message = message;
    }

    public boolean isWon() {
        return won;
    }

    public int getKeysCollected() {
        return keysCollected;
    }

    public int getRequiredKeys() {
        return requiredKeys;
    }

    public String getMessage() {
        return message;
    }
}