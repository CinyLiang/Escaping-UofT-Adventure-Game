package interface_adapter.card_game_hints;

import interface_adapter.ViewManagerModel;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import use_case.card_game_hints.CardGameHintsOutputBoundary;
import use_case.card_game_hints.CardGameHintsOutputDataObject;

public class CardGameHintsPresenter implements CardGameHintsOutputBoundary {
    private final CardGameViewModel cardGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public CardGameHintsPresenter(CardGameViewModel cardGameViewModel,ViewManagerModel viewManagerModel) {
        this.cardGameViewModel = cardGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
        CardGameState current = this.cardGameViewModel.getState();
        String hint =  outputData.getHint();
        current.setHint(hint);
        this.cardGameViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        CardGameState current = this.cardGameViewModel.getState();
        current.setMessage(errorMessage);
        this.cardGameViewModel.firePropertyChange();
    }
}
