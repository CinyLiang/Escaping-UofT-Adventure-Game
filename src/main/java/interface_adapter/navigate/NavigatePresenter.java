package interface_adapter.navigate;

import use_case.navigate.NavigateOutputBoundary;
import use_case.navigate.NavigateOutputData;

public class NavigatePresenter implements NavigateOutputBoundary {
    private final NavigateViewModel navigateViewModel;

    public NavigatePresenter(NavigateViewModel navigateViewModel) {
        this.navigateViewModel = navigateViewModel;
    }

    @Override
    public void prepareSuccessView(NavigateOutputData outputData) {
        NavigateState state = navigateViewModel.getState();
        state.setStoryText(outputData.getStoryText());
        state.setDirection(outputData.getDirection());
        navigateViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        NavigateState state = navigateViewModel.getState();
        state.setStoryText(error);
        navigateViewModel.firePropertyChanged();
    }
}