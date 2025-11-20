package use_case.validateCardAnswer;

public class CardValidationResult {
    /**
     * A class recording the result for a card expression validation.
     */
    private final boolean validity;
    private final String message;
    public CardValidationResult(boolean validity, String message) {
        this.validity = validity;
        this.message = message;
    }
    public boolean isValid() {
        return validity;
    }
    public String getMessage() {
        return message;
    }
}
