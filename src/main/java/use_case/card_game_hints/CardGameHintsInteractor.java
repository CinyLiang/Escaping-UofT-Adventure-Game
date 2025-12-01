package use_case.card_game_hints;

import entity.Card;
import use_case.play_card_game.utilities.SolutionGenerator;
import interface_adapter.card_game_hints.CardGameHintsPresenter;
import java.util.*;
import java.util.stream.Collectors;

public class CardGameHintsInteractor implements CardGameHintsInputDataBoundary {
    private final CardGameHintsOutputBoundary presenter;

    public CardGameHintsInteractor(CardGameHintsOutputBoundary presenter) {
        this.presenter = presenter;
    }

    public void execute(CardGameHintsInputDataObject input) {
        try {
//            String hint = input.getCardPuzzle().giveHint();
            List<Card> cards = input.getCardPuzzle().getCards();
            String hint = generateHint(cards);

            CardGameHintsOutputDataObject outputData = new CardGameHintsOutputDataObject(hint);
            this.presenter.prepareSuccessView(outputData);
        }  catch (Exception e) {
            this.presenter.prepareFailView("Failed to get hint: " + e.getMessage());
        }
    }

    public String getSampleSolution(List<Card> cards) {
        return SolutionGenerator.find24Solutions(cards).get(0);
    }

    public String extractInner(String solution) {
        if (solution == null || solution.isEmpty()) {
            return "a simple calculation";
        }

        solution = solution.replaceAll("\\s+", "");


//        try {
            int lastOpen = solution.lastIndexOf('(');
            int firstCloseAfterLastOpen = solution.indexOf(')', lastOpen);

            // if (lastOpen != -1 && firstCloseAfterLastOpen != -1 && firstCloseAfterLastOpen > lastOpen) {
            if (lastOpen != -1 && firstCloseAfterLastOpen != -1) {
                String inner = solution.substring(lastOpen + 1, firstCloseAfterLastOpen);
                return inner;
            }

            if (solution.length() > 8) {
                return solution.substring(0, Math.min(6, solution.length()));
            }

            return solution;

//        } catch (Exception e) {
//            System.err.println("Fail to get hint: " + e.getMessage());
//            return "a basic operation";
//        }
    }

    public String generateHint(List<Card> cards) {
        try {
            List<String> solutions = SolutionGenerator.find24Solutions(cards);

            if (solutions == null || solutions.isEmpty()) {
                List<Integer> values = cards.stream()
                        .map(Card::getValue)
                        .collect(Collectors.toList());
                return "Try combining " + values + " with +, -, ×, or ÷ operations";
            }

            String sampleSolution = solutions.get(0);
            String substring = extractInner(sampleSolution);
            return "Maybe try " + substring + " first.";
        } catch (Exception e) {
            return "Try thinking independently before asking~";
        }
    }
}
