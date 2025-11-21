package interface_adapter.view_progress;

import use_case.view_progress.ViewProgressInputBoundary;

public class ViewProgressController {

    private final ViewProgressInputBoundary interactor;

    public ViewProgressController(ViewProgressInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        interactor.execute();
    }
}
