package interface_adapter.quit_game;

import use_case.quit_game.QuitGameOutputBoundary;

public class QuitGamePresenter implements QuitGameOutputBoundary {

    private final QuitGameViewModel quitViewModel;

    public QuitGamePresenter(QuitGameViewModel quitViewModel) {
        this.quitViewModel = quitViewModel;
    }

    @Override
    public void prepareSavePromptView() {
        QuitGameState state = quitViewModel.getState();
        state.setSaveDialogRequired(true);
        quitViewModel.setState(state);

        quitViewModel.fireShowSavePrompt();
    }

    @Override
    public void prepareExitView() {
        QuitGameState state = quitViewModel.getState();
        state.setShouldExitGame(true);
        quitViewModel.setState(state);

        quitViewModel.fireExitGame();
    }

    @Override
    public void prepareQuitView() {
        QuitGameState state = quitViewModel.getState();
        state.setQuitDialogVisible(true);
        quitViewModel.setState(state);

        quitViewModel.fireShowQuitDialog();
    }
}