package interface_adapter.return_from_card;

import interface_adapter.navigate.NavigateState;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import use_case.card_return_to_home.CardReturnOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.navigate.NavigateViewModel;
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
        if (current.isSolved()) {
            NavigateState state = this.navigateViewModel.getState();
            state.addNumberOfKeys();
            state.addPuzzleSolved(current.getcardPuzzle().getName());
            // updates number of keys and puzzles solved in the view model if the puzzle is solved
        }
        this.viewManagerModel.setState(this.navigateViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
}
