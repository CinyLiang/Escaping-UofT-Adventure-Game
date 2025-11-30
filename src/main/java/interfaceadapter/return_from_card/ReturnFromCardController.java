package interfaceadapter.return_from_card;

import use_case.card_return_to_home.CardReturnInputBoundary;

public class ReturnFromCardController {
    private Runnable returnFromCardDialogue;
    private final CardReturnInputBoundary cardReturnInputBoundary;

    public ReturnFromCardController(CardReturnInputBoundary cardReturnInputBoundary) {
        this.cardReturnInputBoundary = cardReturnInputBoundary;
    }

    public void setShowQuitDialog(Runnable runnable) {
        this.returnFromCardDialogue = runnable;
    }

    /**
     * Show the quitting dialogue.
     */
    public void showQuit() {
        returnFromCardDialogue.run();
    }

    /**
     * Execute the interactor.
     */
    public void execute() {
        this.cardReturnInputBoundary.execute();
    }
}
