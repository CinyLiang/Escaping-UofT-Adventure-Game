package interface_adapter.card_game_hints;

import interface_adapter.ViewManagerModel;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import use_case.card_game_hints.CardGameHintsOutputBoundary;
import use_case.card_game_hints.CardGameHintsOutputDataObject;

public class CardGameHintsPresenter implements CardGameHintsOutputBoundary {
    private final CardGameViewModel cardGameViewModel;

    public CardGameHintsPresenter(CardGameViewModel cardGameViewModel) {
        this.cardGameViewModel = cardGameViewModel;
    }

    @Override
    public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
        CardGameState current = this.cardGameViewModel.getState();
        CardGameState newState = new CardGameState(current);

        String hint =  outputData.getHint();
        newState.setHint(hint);

        this.cardGameViewModel.setState(newState);
        this.cardGameViewModel.firePropertyChange();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        CardGameState current = this.cardGameViewModel.getState();
        CardGameState newState = new CardGameState(current);

        newState.setMessage(errorMessage);

        this.cardGameViewModel.setState(newState);
        this.cardGameViewModel.firePropertyChange();
    }
}
