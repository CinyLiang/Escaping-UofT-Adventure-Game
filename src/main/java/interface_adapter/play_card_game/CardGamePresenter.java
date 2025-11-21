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
//        CardGameState newState = new CardGameState(current);
        current.setCardPuzzle(outputData.getCardPuzzle());
        current.setMessage(outputData.getMessage());
        // no need to set hint (remains an empty string)
//        cardGameViewModel.setState(current);
        cardGameViewModel.firePropertyChange();

        viewManagerModel.setState(cardGameViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        CardGameState current = cardGameViewModel.getState();
//        CardGameState newState = new CardGameState(current);
        current.setMessage(errorMessage);
        current.setCardPuzzle(null);
//        cardGameViewModel.setState(current);
        cardGameViewModel.firePropertyChange();

        viewManagerModel.setState(cardGameViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}