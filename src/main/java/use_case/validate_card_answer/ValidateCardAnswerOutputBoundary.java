package use_case.validate_card_answer;

/**
 * The output boundary for the Validate Card Answer Use Case.
 */
public interface ValidateCardAnswerOutputBoundary {
    /**
     * Prepares the success view for the Validate Card Answer Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ValidateCardAnswerOutputData outputData);

    /**
     * Prepares the failure view for the Validate Card Answer Use Case.
     * @param outputData the record of the failure
     */
    void prepareFailView(ValidateCardAnswerOutputData outputData);
}