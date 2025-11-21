package interface_adapter.navigate;

import use_case.navigate.NavigateOutputBoundary;
import use_case.navigate.NavigateOutputData;

/**
 * Presenter for the Navigation use case.
 * Receives output from the Interactor and updates the ViewModel.
 */
public class NavigatePresenter implements NavigateOutputBoundary {
    private final NavigateViewModel navigateViewModel;
    public NavigatePresenter(NavigateViewModel navigateViewModel) {
        this.navigateViewModel = navigateViewModel;
    }
    @Override
    public void present(NavigateOutputData outputData) {

        // Getting the current state
        NavigateState state = navigateViewModel.getState();
        // Update fields (story + direction)
        state.setStoryText(outputData.getStoryText());
        state.setDirection(outputData.getDirection());
        // Notify NavigateView to refresh UI
        navigateViewModel.firePropertyChanged();
    }
}
