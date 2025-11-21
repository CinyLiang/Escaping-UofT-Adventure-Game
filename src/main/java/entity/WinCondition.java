package entity;

public class WinCondition {
    private final int requiredKeys;

    public WinCondition(int requiredKeys) {
        this.requiredKeys = requiredKeys;
    }

    public boolean isMet(Player player) {
        return player.getKeysCollected() >= requiredKeys;
    }

    public int getRequiredKeys() {
        return requiredKeys;
    }
}