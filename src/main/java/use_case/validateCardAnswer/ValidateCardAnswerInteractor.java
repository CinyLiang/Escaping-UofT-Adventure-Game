package use_case.validateCardAnswer;

import data_access.ValidateCardAnswerDataAccessObject;
import entity.Card;
import entity.CardPuzzle;
import entity.Player;

import java.util.List;

public class ValidateCardAnswerInteractor implements ValidateCardAnswerInputBoundary {
    private final ValidateCardAnswerDataAccessObject validateDataAccess;
    private final ValidateCardAnswerOutputBoundary validatePresenter;

    public ValidateCardAnswerInteractor(ValidateCardAnswerDataAccessObject validateDataAccess,
                                        ValidateCardAnswerOutputBoundary validatePresenter) {
        this.validateDataAccess = validateDataAccess;
        this.validatePresenter = validatePresenter;
    }

    @Override
    public void execute(ValidateCardAnswerInputData validateInputData) {
        Player player = validateInputData.getPlayer();
        String expression =  validateInputData.getExpression();
        List<Card> cards = validateInputData.getCards();

        ValidateCardAnswerOutputData output;

        CardValidationResult validity = this.validateDataAccess.isSolution(expression, cards);
        String message = validity.getMessage();

        if (validity.isValid()) {
            player.update(validateInputData.getCardPuzzle().getName());
            output = new ValidateCardAnswerOutputData(true,  message);
            this.validatePresenter.prepareSuccessView(output);
        } else {
            output = new ValidateCardAnswerOutputData(false, message);
            this.validatePresenter.prepareFailView(output);
        }
    }
}