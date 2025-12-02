package interface_adapter.quit_game;

import use_case.quit_game.QuitGameInputBoundary;

/**
 * The controller for the Login Use Case.
 */
public class QuitGameController {
    private QuitGameInputBoundary quitGameInteractor;

    public QuitGameController(QuitGameInputBoundary quitGameInteractor) {
        this.quitGameInteractor = quitGameInteractor;
    }

    public void execute() {
        quitGameInteractor.execute();
    }

    public void executeRequestQuit() {
        quitGameInteractor.executeRequestQuit();
    }

    public void exitGame() {
        quitGameInteractor.exitGame();
    }
}