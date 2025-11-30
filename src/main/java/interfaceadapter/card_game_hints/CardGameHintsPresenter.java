package interfaceadapter.card_game_hints;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.play_card_game.CardGameState;
import interfaceadapter.play_card_game.CardGameViewModel;
import use_case.card_game_hints.CardGameHintsOutputBoundary;
import use_case.card_game_hints.CardGameHintsOutputDataObject;

public class CardGameHintsPresenter implements CardGameHintsOutputBoundary {
    private final CardGameViewModel cardGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public CardGameHintsPresenter(CardGameViewModel cardGameViewModel, ViewManagerModel viewManagerModel) {
        this.cardGameViewModel = cardGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
        final CardGameState current = this.cardGameViewModel.getState();
        final String hint = outputData.getHint();
        current.setHint(hint);
        this.cardGameViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final CardGameState current = this.cardGameViewModel.getState();
        current.setMessage(errorMessage);
        this.cardGameViewModel.firePropertyChange();
    }
}
