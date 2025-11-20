package interface_adapter.validate_card_answer;

import use_case.validateCardAnswer.ValidateCardAnswerInputData;
import use_case.validateCardAnswer.ValidateCardAnswerOutputBoundary;
import use_case.validateCardAnswer.ValidateCardAnswerOutputData;
import interface_adapter.ViewManagerModel;

/**
 * The Presenter for the Validate Card Answer Use Case.
 */
public class ValidateCardPresenter implements ValidateCardAnswerOutputBoundary {
    private final ValidateCardViewModel validateCardViewModel;

    public ValidateCardPresenter(ValidateCardViewModel validateCardViewModel) {
        this.validateCardViewModel = validateCardViewModel;
    }

    @Override
    public void prepareSuccessView(ValidateCardAnswerOutputData outputData) {
        String message = outputData.getMessage();
        ValidateCardState current = this.validateCardViewModel.getState();
        ValidateCardState newState = new ValidateCardState(current);
        newState.setMessage(message);
        newState.setSolved();

        this.validateCardViewModel.setState(newState);
        this.validateCardViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(ValidateCardAnswerOutputData outputData) {
        String message = outputData.getMessage();
        ValidateCardState state = this.validateCardViewModel.getState();
        ValidateCardState newState = new ValidateCardState(state);
        newState.setMessage(message);
        this.validateCardViewModel.setState(newState);
        this.validateCardViewModel.firePropertyChange();
    }
}