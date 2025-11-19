package entity;

import java.util.*;
import entity.Card;

/**
 * An entity representing a Card Puzzle
 */
public class CardPuzzle extends Puzzle {
    private final List<Card> cards;
    private final String sampleSolution;
    public static String DESCRIPTION =
            "Welcome to the Math24 Card Puzzle! " +
                    "Do you see the 4 random cards presented to you? \n" +
                    "Try to connect them using \"+\", \"-\", \"*\", \"/\", and parentheses " +
                    "to get an expression that evaluates to 24!";
    private final String hint;
    private static final String NAME = "CardPuzzle";

    /**
     * Creates a new card puzzle with the given id and cards.
     * @param cards the randomly drawn cards
     */

    public CardPuzzle(List<Card> cards) {
        super("", DESCRIPTION, NAME);
        this.cards = cards;
        this.sampleSolution = this.solve();
        String inner = this.extractInner();
        this.hint = "Maybe try " + inner + " first.";
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
