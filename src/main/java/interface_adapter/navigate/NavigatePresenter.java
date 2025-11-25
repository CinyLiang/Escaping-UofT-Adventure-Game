package interface_adapter.navigate;

import interface_adapter.ViewManagerModel;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.trivia_game.TriviaGameViewModel;
import interface_adapter.win_game.WinGameViewModel;
import use_case.navigate.NavigateOutputBoundary;
import use_case.navigate.NavigateOutputData2;

/**
 * Presenter for the Navigation use case.
 * Receives output from the Interactor and updates the ViewModel.
 */
public class NavigatePresenter implements NavigateOutputBoundary {
    private final NavigateViewModel navigateViewModel;
    private final ViewManagerModel viewManagerModel;

    private final WinGameViewModel winGameViewModel;
    private final CardGameViewModel cardGameViewModel;
    private final TriviaGameViewModel triviaGameViewModel;

    public NavigatePresenter(NavigateViewModel navigateViewModel, ViewManagerModel viewManagerModel, WinGameViewModel winGameViewModel, CardGameViewModel cardGameViewModel, TriviaGameViewModel triviaGameViewModel) {
        this.navigateViewModel = navigateViewModel;
        this.viewManagerModel = viewManagerModel;
        this.winGameViewModel = winGameViewModel;
        this.cardGameViewModel = cardGameViewModel;
        this.triviaGameViewModel = triviaGameViewModel;
    }

    @Override
    public void prepareSuccessView(NavigateOutputData2 outputData) {
        String target = outputData.getTargetView().toLowerCase();

        switch (target) {
            case "win game" -> {
                NavigateState state = this.navigateViewModel.getState();
                if (state.getNumberOfKeys() == state.getTargetNumberOfKeys()) {
                    viewManagerModel.setState(winGameViewModel.getViewName());
                } else {
                    state.setStoryText("Oh no, you can't enter yet. You need to get " + (2-state.getNumberOfKeys()) + " more keys");
                    this.navigateViewModel.firePropertyChange();

                    // ok so this makes the whole controller then interactor bit useless lmaoaoaoa
                }
            }
            case "card game" -> {
                updateNavigation(cardGameViewModel.getState().getLocationName());
                viewManagerModel.setState(cardGameViewModel.getViewName());
            }
            case "trivia game" -> {
                updateNavigation(triviaGameViewModel.getState().getLocationName());
                viewManagerModel.setState(triviaGameViewModel.getViewName());
            }
            default -> {
                // optional: ignore or throw
            }
        }

        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) { }

    @Override
    public void updateNavigation(String newLocation) {
        NavigateState state = navigateViewModel.getState();
        state.setLocation(newLocation);
        navigateViewModel.firePropertyChange();
    }

}
