package interfaceadapter.validate_card_answer;

import interfaceadapter.play_card_game.CardGameState;
import interfaceadapter.play_card_game.CardGameViewModel;
import use_case.validate_card_answer.ValidateCardAnswerOutputBoundary;
import use_case.validate_card_answer.ValidateCardAnswerOutputData;

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
        final String feedback = outputData.getMessage();
        final CardGameState current = this.cardGameViewModel.getState();
        current.setMessage(feedback);
        current.setSolved();
        current.setHint("");

        this.cardGameViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(ValidateCardAnswerOutputData outputData) {
        final String message = outputData.getMessage();
        final CardGameState current = this.cardGameViewModel.getState();
        current.setMessage(message);
        this.cardGameViewModel.setState(current);
        this.cardGameViewModel.firePropertyChange();
    }
}
