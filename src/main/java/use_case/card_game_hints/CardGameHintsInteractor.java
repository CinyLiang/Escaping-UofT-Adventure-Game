package use_case.card_game_hints;

import entity.Card;
import use_case.play_card_game.utilities.SolutionGenerator;

import java.util.List;

public class CardGameHintsInteractor {
    private final CardGameHintsOutputBoundary outputBoundary;

    public CardGameHintsInteractor(CardGameHintsOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void execute(CardGameHintsInputDataObject input) {
        try {
//            String hint = input.getCardPuzzle().giveHint();
            List<Card> cards = input.getCardPuzzle().getCards();
            String hint = getSampleSolution(cards);

            CardGameHintsOutputDataObject outputData = new CardGameHintsOutputDataObject(hint);
            this.outputBoundary.prepareSuccessView(outputData);
        }  catch (Exception e) {
            this.outputBoundary.prepareFailView("Failed to get hint: " + e.getMessage());
        }
    }

    public String getSampleSolution(List<Card> cards) {
        return SolutionGenerator.find24Solutions(cards).get(0);
    }

    public String extractInner(String solution) {
        int open;
        int close;
        if (solution.contains("))")) {
            open = solution.lastIndexOf("(");
            close = solution.indexOf("))");
        } else if (solution.contains("((")) {
            open = solution.indexOf(")");
            close = solution.indexOf("((");
        } else {
            open = solution.indexOf("(");
            close = solution.indexOf(")");
        }
        return solution.substring(open + 1, close);
    }

    public String generateHint(List<Card> cards) {
        String sampleSolution = getSampleSolution(cards);
        String substring = extractInner(sampleSolution);
        return "Maybe try"+substring+"first.";
    }
}
