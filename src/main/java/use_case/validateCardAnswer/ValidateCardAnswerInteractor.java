package use_case.validateCardAnswer;

import entity.Card;
import use_case.play_card_game.utilities.ExpressionEvaluator;
import entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ValidateCardAnswerInteractor implements ValidateCardAnswerInputBoundary {
    private final ValidateCardAnswerOutputBoundary validatePresenter;

    public ValidateCardAnswerInteractor(ValidateCardAnswerOutputBoundary validatePresenter) {
        this.validatePresenter = validatePresenter;
    }

    @Override
    public void execute(ValidateCardAnswerInputData validateInputData) {
        Player player = validateInputData.getPlayer();
        String expression =  validateInputData.getExpression();
        List<Card> cards = validateInputData.getCards();

        ValidateCardAnswerOutputData output;

        CardValidationResult validity = isSolution(expression, cards);
        String message = validity.getMessage();

        if (validity.isValid()) {
            player.markPuzzleSolved(validateInputData.getCardPuzzle().getName());
            // consider not doing this and instead updating the nav state (not sure about the player entity here)
            output = new ValidateCardAnswerOutputData(true,  message);
            this.validatePresenter.prepareSuccessView(output);
        } else {
            output = new ValidateCardAnswerOutputData(false, message);
            this.validatePresenter.prepareFailView(output);
        }
    }

    public CardValidationResult isSolution(String expression,  List<Card> cards) {
        try {
            List<Integer> cardVals = new ArrayList<>();
            for (Card card : cards) {
                cardVals.add(card.getValue());
            }

            if (!ExpressionEvaluator.checkExprPrereq(expression, cardVals)) {
                return new CardValidationResult(false, "Invalid use of card numbers and/or operators.");
            }
            if (ExpressionEvaluator.evaluate(expression) != 24) {
                return new CardValidationResult(false, "Expression does not add up to 24. Please try again.");
            }
            return new CardValidationResult(true, "Correct Answer!! It's UofT's honour to have smart people like you!");
        } catch (Exception e){
            return new CardValidationResult(false, "Evaluation error: " + e.getMessage());
        }
    }
}