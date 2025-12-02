package interface_adapter.clear_history;

import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.quit_game.QuitGameState;
import use_case.clear_history.ClearHistoryOutputBoundary;

public class ClearHistoryPresenter implements ClearHistoryOutputBoundary {
    private final NavigateViewModel viewModel;
    private final ClearHistoryViewModel clearHistoryViewModel;

    public ClearHistoryPresenter(NavigateViewModel viewModel, ClearHistoryViewModel clearHistoryViewModel) {
        this.viewModel = viewModel;
        this.clearHistoryViewModel = clearHistoryViewModel;
    }

    @Override
    public void execute() {
        NavigateState state = viewModel.getState();
        state.setNumberOfKeys(0);
        state.resetPuzzlesSolved();
        state.setStoryText("Game reset. Where would you like to go?");

        viewModel.firePropertyChange();
    }

    @Override
    public void prepareClearHistoryView() {
        ClearHistoryState state = clearHistoryViewModel.getState();
        state.setClearDialogVisible(true);
        clearHistoryViewModel.setState(state);

        clearHistoryViewModel.fireShowRestartDialog();
    }
}