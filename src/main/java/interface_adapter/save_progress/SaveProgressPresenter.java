package interface_adapter.save_progress;

import use_case.save_progress.SaveProgressOutputBoundary;
import use_case.save_progress.SaveProgressOutputData;

/**
 * Presenter for Save Progress.
 * Currently just prints messages; later you can update this to modify a ViewModel.
 */
public class SaveProgressPresenter implements SaveProgressOutputBoundary {

    @Override
    public void prepareSuccessView(SaveProgressOutputData outputData) {
        System.out.println(outputData.getMessage());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.err.println(errorMessage);
    }
}
