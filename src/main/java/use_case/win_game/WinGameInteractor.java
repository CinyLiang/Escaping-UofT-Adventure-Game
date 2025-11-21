package use_case.win_game;

import entity.Player;
import entity.WinCondition;

public class WinGameInteractor implements WinGameInputBoundary {
    private final WinGameOutputBoundary presenter;
    private final WinCondition winCondition;

    public WinGameInteractor(WinGameOutputBoundary presenter, WinCondition winCondition) {
        this.presenter = presenter;
        this.winCondition = winCondition;
    }

    @Override
    public void attemptWin(WinGameInputData inputData) {
        Player player = inputData.getPlayer();

        if (winCondition.isMet(player)) {
            WinGameOutputData outputData = new WinGameOutputData(
                    true,
                    player.getKeysCollected(),
                    winCondition.getRequiredKeys(),
                    "Congratulations! You've collected all the keys and escaped campus!"
            );
            presenter.prepareWinView(outputData);
        } else {
            int keysNeeded = winCondition.getRequiredKeys() - player.getKeysCollected();
            String message = "You need " + keysNeeded + " more key(s) to enter Convocation Hall.";
            presenter.prepareFailView(message);
        }
    }
}