package data_access;

import entity.ExpressionEvaluator;
import use_case.validateCardAnswer.ValidateCardAnswerDataAccessInterface;
import use_case.validateCardAnswer.CardValidationResult;
import entity.Card;
import java.util.ArrayList;
import java.util.List;

public class ValidateCardAnswerDataAccessObject implements ValidateCardAnswerDataAccessInterface{
    /**
     * The DAO for the Validate Card Answer Use Case.
     */
    @Override
    public CardValidationResult isSolution(String expression,  List<Card> cards) {
        try {
            List<Integer> cardVals = new ArrayList<>();
            CardValidationResult result;
            for (Card card : cards) {
                cardVals.add(card.getValue());
            }
            if (!ExpressionEvaluator.checkExprPrereq(expression, cardVals)) {
                result = new CardValidationResult(false, "Invalid use of card numbers and/or operators.");
                return result;
            }
            if (ExpressionEvaluator.evaluate(expression) != 24) {
                result = new CardValidationResult(false, "Expression does not add up to 24. Please try again.");
                return result;
            }
            return new CardValidationResult(true, "Correct Answer!! It's UofT's honour to have smart people like you!");
        } catch (Exception e){
            return new CardValidationResult(false, "Evaluation error: " + e.getMessage());
        }
    }
}
