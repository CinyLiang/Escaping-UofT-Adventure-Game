package entity;

import java.util.*;
import entity.Card;

/**
 * An entity representing a Card Puzzle
 */
public class CardPuzzle extends Puzzle {
    private final List<Card> cards;
    private String sampleSolution;
    private String hint;
    private String message;
    private static final String NAME = "CardPuzzle";

    /**
     * Creates a new card puzzle with the given id and cards.
     * @param cards the randomly drawn cards
     */

    public CardPuzzle(List<Card> cards) {
        super("", "Math 24 Game", NAME);
        this.cards = cards;
        this.sampleSolution = this.solve();
        String inner = this.extractInner();
        this.hint = "Maybe try " + inner + " first.";
        this.message = "\"Welcome to the Math24 Card Puzzle! \" +\n" +
                "                    \"Try to connect the four card numbers below\" +\n" +
                "                    \"using \\\"+\\\", \\\"-\\\", \\\"*\\\", \\\"/\\\", and parentheses \" +\n" +
                "                    \"to get an expression that evaluates to 24!\";" +
                this.getCardNumberString();
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Integer> getCardNumbers() {
        List<Integer> cardNumbers = new ArrayList<>();
        for (Card card : cards) {
            cardNumbers.add(card.getValue());
        }
        return cardNumbers;
    }

    public String getCardNumberString() {
        List<Integer> cardNumbers = this.getCardNumbers();
        return cardNumbers.toString();
    }

    public String getMessage() {
        return this.message;
    }

    private String extractInner() {
        String sol = this.sampleSolution;
        int open;
        int close;
        if (sol.contains("))")) {
            open = sol.lastIndexOf("(");
            close = sol.indexOf("))");
        } else if (sol.contains("((")) {
            open = sol.indexOf(")");
            close = sol.indexOf("((");
        } else {
            open = sol.indexOf("(");
            close = sol.indexOf(")");
        }
        return sol.substring(open + 1, close);
    }

    public String giveHint() {
        return this.hint;
    }

    @Override
    public String solve() {
        return SolutionGenerator.find24Solutions(this.cards).get(0);
    }

    @Override
    public boolean validateSolution(String solution) {
        int result = ExpressionEvaluator.evaluate(solution);
        return result == 24;
    }
}
