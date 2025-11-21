package interface_adapter.navigate;

import use_case.navigate.NavigateInputBoundary;
import use_case.navigate.NavigateInputData;

public class NavigateController {

    private final NavigateInputBoundary navigateInteractor;
    public NavigateController(NavigateInputBoundary navigateInteractor) {
        this.navigateInteractor = navigateInteractor;
    }
    /**
     * Called when the user selects a direction in NavigateView.
     * @param direction the direction chosen by the user (North, South, etc)
     */
    public void execute(String direction) {
        NavigateInputData inputData = new NavigateInputData(direction);
        navigateInteractor.execute(inputData);
    }
}
