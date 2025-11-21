package use_case.win_game;

import entity.Player;

public class WinGameInputData {
    private final Player player;

    public WinGameInputData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}