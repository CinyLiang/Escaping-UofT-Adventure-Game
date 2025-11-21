package interface_adapter.view_progress;

import use_case.view_progress.ViewProgressOutputBoundary;
import use_case.view_progress.ViewProgressOutputData;

public class ViewProgressPresenter implements ViewProgressOutputBoundary {

    private final ViewProgressViewModel viewModel;

    public ViewProgressPresenter(ViewProgressViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(ViewProgressOutputData data) {
        String text = "=== Your Progress ===\n"
                + "Location: " + data.getLocation() + "\n"
                + "Keys collected: " + data.getKeysCollected() + "\n"
                + "Solved puzzles: " + data.getSolvedPuzzles();

        viewModel.setProgressText(text);
    }
}
