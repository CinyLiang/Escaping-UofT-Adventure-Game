package use_case.validateCardAnswer;

import use_case.play_card_game.PlayCardGameInputData;
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