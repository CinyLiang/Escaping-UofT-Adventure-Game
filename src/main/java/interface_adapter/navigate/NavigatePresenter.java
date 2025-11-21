package interface_adapter.navigate;

import use_case.navigate.NavigateOutputBoundary;
import use_case.navigate.NavigateOutputData;

/*
 * Presenter for the Navigation use case.
 */
public class NavigatePresenter implements NavigateOutputBoundary {

    private final NavigateViewModel viewModel;

    public NavigatePresenter(NavigateViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(NavigateOutputData data) {

    }

    @Override
    public void prepareSuccessView(NavigateOutputData response) {

        NavigateState state = viewModel.getState();

        state.setStoryText(response.getStoryText());
        state.setDirection(response.getDirection());

        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Error navigating: " + error);
    }
}