package use_case.validate_card_answer;

/**
 * The Validate Card Answer Use Case.
 */

public interface ValidateCardAnswerInputBoundary {
    /**
     * Execute the Validate Card Answer Use Case.
     *
     * @param validateInputData the input data for this use case
     */
    void execute(ValidateCardAnswerInputData validateInputData);
}