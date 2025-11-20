package use_case.validateCardAnswer;

import java.util.ArrayList;

/**
 * Output Data for the Validate Card Answer Use Case.
 */
public class ValidateCardAnswerOutputData {
    private final boolean pass;
    private final String message;

    public ValidateCardAnswerOutputData(boolean pass, String message) {
        this.pass = pass;
        this.message = message;
    }

    public boolean getSuccess() {
        return this.pass;
    }
    public String getMessage() {return this.message;}
}
