package interface_adapter.view_progress;

import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import use_case.view_progress.ViewProgressOutputBoundary;
import use_case.view_progress.ViewProgressOutputData;

public class ViewProgressPresenter implements ViewProgressOutputBoundary {

    private final NavigateViewModel viewModel;

    // changed to nav view state bc my guy, this is not even used.
    public ViewProgressPresenter(NavigateViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(ViewProgressOutputData data) {
        String text = "Location: " + data.getLocation() + "\n"
                + "Keys collected: " + data.getKeysCollected() + "\n"
                + "Solved puzzles: " + data.getSolvedPuzzles();

        NavigateState current = viewModel.getState();
        current.setProgressText(text);
        current.setStoryText("Viewing progress... What's next?");
        viewModel.firePropertyChange();
    }
}
