package interface_adapter.return_from_card;

import interface_adapter.navigate.NavigateState;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.navigate.NavigateViewModel;

import use_case.card_return_to_home.CardReturnOutputBoundary;

public class ReturnFromCardPresenter implements CardReturnOutputBoundary{
    private final ViewManagerModel viewManagerModel;
    private final NavigateViewModel navigateViewModel;
    private final CardGameViewModel cardGameViewModel;

    public ReturnFromCardPresenter(ViewManagerModel viewManagerModel,
                                   NavigateViewModel navigateViewModel, CardGameViewModel cardGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.navigateViewModel = navigateViewModel;
        this.cardGameViewModel = cardGameViewModel;
    }

    @Override
    public void changeView() {
        CardGameState current = cardGameViewModel.getState();
        NavigateState state = this.navigateViewModel.getState();
        if (current.isSolved() && !state.getPuzzlesSolved().contains(current.getcardPuzzle().getName())) {
            state.addNumberOfKeys();
            state.addPuzzleSolved(current.getcardPuzzle().getName());
            state.setStoryText("You solved the Math 24 puzzle! Where to next?");
        }
        state.setLocation();
        this.navigateViewModel.firePropertyChange();

        this.viewManagerModel.setState(this.navigateViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
}
