package interface_adapter.play_card_game;

import use_case.play_card_game.PlayCardGameOutputBoundary;
import use_case.play_card_game.PlayCardGameOutputData;
import interface_adapter.ViewManagerModel;

/**
 * The Presenter for the Play Card Game Use Case.
 */
public class CardGamePresenter implements PlayCardGameOutputBoundary {

    private final CardGameViewModel cardGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public CardGamePresenter(CardGameViewModel cardGameViewModel,
                             ViewManagerModel viewManagerModel) {
        this.cardGameViewModel = cardGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PlayCardGameOutputData outputData) {
        CardGameState current = cardGameViewModel.getState();
        CardGameState newState = new CardGameState(current);
        newState.setCardPuzzle(outputData.getCardPuzzle());
        newState.setMessage(outputData.getCardPuzzle().getMessage());
        cardGameViewModel.setState(newState);
        cardGameViewModel.firePropertyChange();

        viewManagerModel.setState("Math24 Card Game");
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        CardGameState current = cardGameViewModel.getState();
        CardGameState newState = new CardGameState(current);
        newState.setMessage(errorMessage);
        newState.setCardPuzzle(null);
        cardGameViewModel.setState(newState);
        cardGameViewModel.firePropertyChange();
    }
}