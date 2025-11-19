package use_case.validateCardAnswer;

import entity.Card;

import java.util.List;

/**
 * The DAO interface for the Validate Card Answer Use Case.
 */
public interface ValidateCardAnswerDataAccessInterface {
    List<Card> drawCards (int cardNum);
}