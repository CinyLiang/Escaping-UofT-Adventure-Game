package main.java.interface_adapter.quit_game;

import main.java.use_case.quit_game.QuitGameInputBoundary;

/**
 * The controller for the Login Use Case.
 */
public class QuitGameController {

    private final QuitGameInputBoundary quitGameUseCaseInteractor;

    public QuitGameController(QuitGameInputBoundary quitGameUseCaseInteractor) {
        this.quitGameUseCaseInteractor = quitGameUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     */
    public void execute() {
        quitGameUseCaseInteractor.execute();
        // TODO: implement the actual function (aka prompt "save game")
    }
}