package use_case.card_return_to_home;


public class CardReturnInteractor implements CardReturnInputBoundary {
    private final CardReturnOutputBoundary outputBoundary;

    public CardReturnInteractor(CardReturnOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute() {
        this.outputBoundary.changeView();
    }
}
