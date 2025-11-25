package use_case.view_progress;

public class ViewProgressInteractor implements ViewProgressInputBoundary {

    private final ViewProgressOutputBoundary presenter;

    public ViewProgressInteractor(ViewProgressOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(ViewProgressInputData inputData) {
        String loc = inputData.getLocation();
        int keys = inputData.getKeys();
        var puzzles = inputData.getPuzzles();

        ViewProgressOutputData output = new ViewProgressOutputData(loc, keys, puzzles);
        presenter.present(output);
    }
}
