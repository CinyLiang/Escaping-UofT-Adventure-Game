package use_case.card_game_hints;

/**
 * The Input Data Boundary for the Get Hints Use Case.
 */
public interface CardGameHintsInputDataBoundary {
    /**
     * Execute the Get Hints Use Case.
     * @param hintInput the input data for this use case.
     */
    void execute(CardGameHintsInputDataObject hintInput);
}
