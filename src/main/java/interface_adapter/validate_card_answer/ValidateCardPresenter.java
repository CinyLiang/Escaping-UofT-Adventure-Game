package interface_adapter.validate_card_answer;

import interface_adapter.play_card_game.CardGameState;
import interface_adapter.play_card_game.CardGameViewModel;
import use_case.validateCardAnswer.ValidateCardAnswerOutputBoundary;
import use_case.validateCardAnswer.ValidateCardAnswerOutputData;

/**
 * The Presenter for the Validate Card Answer Use Case.
 */
public class ValidateCardPresenter implements ValidateCardAnswerOutputBoundary {
    private final CardGameViewModel cardGameViewModel;

    public ValidateCardPresenter(CardGameViewModel cardGameViewModel) {
        this.cardGameViewModel = cardGameViewModel;
    }

    @Override
    public void prepareSuccessView(ValidateCardAnswerOutputData outputData) {
        String feedback = outputData.getMessage();
        CardGameState current = this.cardGameViewModel.getState();
        CardGameState newState = new CardGameState(current);
        newState.setMessage(feedback);
        newState.setSolved();

        this.cardGameViewModel.setState(newState);
        this.cardGameViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(ValidateCardAnswerOutputData outputData) {
        String message = outputData.getMessage();
        CardGameState state = this.cardGameViewModel.getState();
        CardGameState newState = new CardGameState(state);
        newState.setMessage(message);
        this.cardGameViewModel.setState(newState);
        this.cardGameViewModel.firePropertyChange();
    }
}