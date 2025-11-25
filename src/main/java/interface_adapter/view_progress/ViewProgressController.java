package interface_adapter.view_progress;

import use_case.view_progress.ViewProgressInputBoundary;
import use_case.view_progress.ViewProgressInputData;

import java.util.Set;

public class ViewProgressController {

    private final ViewProgressInputBoundary interactor;

    public ViewProgressController(ViewProgressInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String loc, int keys, Set<String> puzzles) {
        ViewProgressInputData inputData = new ViewProgressInputData(loc, keys, puzzles);
        interactor.execute(inputData);
    }
}
