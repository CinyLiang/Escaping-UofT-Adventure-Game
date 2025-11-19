package use_case.validateCardAnswer;

import entity.Card;

import java.util.*;

/**
 * The DAO interface for the Validate Card Answer Use Case.
 */
public interface ValidateCardAnswerDataAccessInterface {
    CardValidationResult isSolution(String expression, List<Card> cards);
}