package interface_adapter.card_game_hints;

import interface_adapter.ViewManagerModel;
import use_case.card_game_hints.CardGameHintsOutputBoundary;
import use_case.card_game_hints.CardGameHintsOutputDataObject;

public class CardGameHintsPresenter implements CardGameHintsOutputBoundary {
    private final CardGameHintsViewModel  cardGameHintsViewModel;

    public CardGameHintsPresenter(CardGameHintsViewModel cardGameHintsViewModel) {
        this.cardGameHintsViewModel = cardGameHintsViewModel;
    }

    @Override
    public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
        CardGameHintsState current = this.cardGameHintsViewModel.getState();
        CardGameHintsState newState = new CardGameHintsState(current);

        String hint =  outputData.getHint();
        newState.setHint(hint);

        this.cardGameHintsViewModel.setState(newState);
        this.cardGameHintsViewModel.firePropertyChange();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        CardGameHintsState current = this.cardGameHintsViewModel.getState();
        CardGameHintsState newState = new CardGameHintsState(current);

        newState.setErrorMessage(errorMessage);

        this.cardGameHintsViewModel.setState(newState);
        this.cardGameHintsViewModel.firePropertyChange();
    }
}
