package use_case.save_progress;

/**
 * Output data for the Save Progress use case.
 */
public class SaveProgressOutputData {

    private final boolean success;
    private final String message;

    public SaveProgressOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
