package interface_adapter.return_from_card;

import use_case.card_return_to_home.CardReturnInputBoundary;

public class ReturnFromCardController {
    private Runnable returnFromCardDialogue;
    private final CardReturnInputBoundary cardReturnInputBoundary;

    public ReturnFromCardController(CardReturnInputBoundary cardReturnInputBoundary) {
        this.cardReturnInputBoundary = cardReturnInputBoundary;
    }

    public void setShowQuitDialog(Runnable r) {
        this.returnFromCardDialogue = r;
    }

    public void showQuit() {
        returnFromCardDialogue.run();
    }

    public void execute() {
        this.cardReturnInputBoundary.execute();
    }
}
