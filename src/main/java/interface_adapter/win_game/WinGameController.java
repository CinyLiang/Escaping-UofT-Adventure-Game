package interface_adapter.win_game;

import use_case.win_game.WinGameInputBoundary;
import use_case.win_game.WinGameInputData;

public class WinGameController {
    private final WinGameInputBoundary interactor;

    public WinGameController(WinGameInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(WinGameInputData inputData) {
        interactor.attemptWin(inputData);
    }
}