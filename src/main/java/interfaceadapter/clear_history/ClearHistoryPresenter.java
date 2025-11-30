package interfaceadapter.clear_history;

import interfaceadapter.navigate.NavigateState;
import interfaceadapter.navigate.NavigateViewModel;
import use_case.clear_history.ClearHistoryOutputBoundary;

public class ClearHistoryPresenter implements ClearHistoryOutputBoundary {
    private final NavigateViewModel viewModel;

    public ClearHistoryPresenter(NavigateViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void prepareSuccessView(String message) {}
    public void prepareFailView(String errorMessage) {}

    @Override
    public void execute() {
        NavigateState state = viewModel.getState();
        state.setNumberOfKeys(0);
        state.resetPuzzlesSolved();

        viewModel.firePropertyChange();
    }
}