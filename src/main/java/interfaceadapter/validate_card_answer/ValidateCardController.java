package interfaceadapter.validate_card_answer;

import use_case.validate_card_answer.ValidateCardAnswerInputBoundary;
import use_case.validate_card_answer.ValidateCardAnswerInputData;

/**
 * Controller for the Validate Card Answer Use Case.
 */
public class ValidateCardController {
    private final ValidateCardAnswerInputBoundary interactor;

    public ValidateCardController(ValidateCardAnswerInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the Validate Card Answer Use Case.
     * @param validateInputData the input data
     */
    public void execute(ValidateCardAnswerInputData validateInputData) {
        interactor.execute(validateInputData);
    }
}
