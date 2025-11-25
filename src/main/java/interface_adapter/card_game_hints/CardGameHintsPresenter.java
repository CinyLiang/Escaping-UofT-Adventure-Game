package interface_adapter.card_game_hints;

import interface_adapter.ViewManagerModel;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
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
        CardGameState current = this.cardGameViewModel.getState();
//        CardGameState newState = new CardGameState(current);

        String hint = outputData.getHint();
        current.setHint(hint);

//        this.cardGameViewModel.setState(newState);
        this.cardGameViewModel.firePropertyChange();

        // if now changes in view seen, firePropertyChange in viewManagerModel
    }

    @Override
    public void prepareFailView(String errorMessage) {
        CardGameState current = this.cardGameViewModel.getState();
//        CardGameState newState = new CardGameState(current);

        current.setMessage(errorMessage);

//        this.cardGameViewModel.setState(current);
        this.cardGameViewModel.firePropertyChange();
    }
}
