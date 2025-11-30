package interfaceadapter.play_card_game;

import interfaceadapter.ViewManagerModel;
import use_case.play_card_game.PlayCardGameOutputBoundary;
import use_case.play_card_game.PlayCardGameOutputData;

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
        final CardGameState current = cardGameViewModel.getState();
        current.setCardPuzzle(outputData.getCardPuzzle());
        current.setMessage(outputData.getMessage());
        current.setHint("");
        cardGameViewModel.firePropertyChange();

        viewManagerModel.setState(cardGameViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final CardGameState current = cardGameViewModel.getState();
        current.setMessage(errorMessage);
        current.setCardPuzzle(null);
        cardGameViewModel.firePropertyChange();

        viewManagerModel.setState(cardGameViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
