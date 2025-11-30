package interfaceadapter.return_from_card;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.navigate.NavigateState;
import interfaceadapter.navigate.NavigateViewModel;
import interfaceadapter.play_card_game.CardGameState;
import interfaceadapter.play_card_game.CardGameViewModel;
import use_case.card_return_to_home.CardReturnOutputBoundary;

public class ReturnFromCardPresenter implements CardReturnOutputBoundary {
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
        final CardGameState current = cardGameViewModel.getState();
        final NavigateState state = this.navigateViewModel.getState();
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
