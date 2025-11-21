package interface_adapter.clear_history;

import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import use_case.clear_history.ClearHistoryOutputBoundary;

public class ClearHistoryPresenter implements ClearHistoryOutputBoundary {
    private final ClearHistoryViewModel viewModel;

    public ClearHistoryPresenter(ClearHistoryViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void prepareSuccessView(String message) {
        viewModel.setMessage(message);  // UI update
    }
    public void prepareFailView(String errorMessage) {
        viewModel.setMessage(errorMessage);
    }

    @Override
    public void execute() {
        NavigateState state = viewModel.getState();
        state.setNumberOfKeys(0);
        state.resetPuzzlesSolved();

        viewModel.firePropertyChange();
    }
}