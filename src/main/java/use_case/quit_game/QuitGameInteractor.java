package use_case.quit_game;

/**
 * The Interactor decides what happens when the user clicks "Quit".
 */
public class QuitGameInteractor implements QuitGameInputBoundary {

    final QuitGameOutputBoundary quitGamePresenter;

    // final GameStateDataAccessInterface gameStateDataAccessObject;

    public QuitGameInteractor(QuitGameOutputBoundary quitGamePresenter) {
        this.quitGamePresenter = quitGamePresenter;
    }

    @Override
    public void execute() {
        // check if there is unsaved changes (may take from DAO and input object containing stuff from navigate view model)
        // boolean hasUnsavedChanges = ... (call DAO)
        // if (hasUnsavedChanges) {
        //     userPresenter.prepareSavePromptView();
        // } else {
        //     userPresenter.prepareExitView();
        // }

        // always show dialog
        quitGamePresenter.prepareSavePromptView();
    }

    @Override
    public void executeRequestQuit() {
        quitGamePresenter.prepareQuitView();
    }
}