package use_case.play_card_game.utilities;
import entity.Card;

import java.util.*;

public class TwentyFourChecker {

    private static final double TARGET = 24.0;
    private static final double EPS = 1e-6; // tolerance

    public static boolean canMake24(List<Card> cards) {
        return solve(cardParser(cards));
    }

    public static boolean canMake24(int a, int b, int c, int d) {
        List<Double> nums = Arrays.asList((double) a, (double) b,
                (double) c, (double) d);
        return solve(nums);
    }

    private static List<Double> cardParser(List<Card> cards) {
        List<Double> nums = new ArrayList<>();
        for (Card card : cards) {
            nums.add((double)card.getValue());
        }
        return nums;
    }

    private static List<List<Double>> generateNextStates(List<Double> nums) {
        int n = nums.size();
        List<List<Double>> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                double x = nums.get(i);
                double y = nums.get(j);

                // Remaining numbers (other than i, j)
                List<Double> rest = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) rest.add(nums.get(k));
                }

                for (double val : generateResults(x, y)) {
                    List<Double> next = new ArrayList<>(rest);
                    next.add(val);
                    results.add(next);
                }
            }
        }

        return results;
    }


    private static boolean solve(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - TARGET) < EPS;
        }

        for (List<Double> nextState : generateNextStates(nums)) {
            if (solve(nextState)) return true;
        }

        return false;
    }



    // returns all possible values from x op y
    private static List<Double> generateResults(double x, double y) {
        List<Double> results = new ArrayList<>();
        results.add(x + y);
        results.add(x - y);
        results.add(x * y);
        if (Math.abs(y) > EPS) results.add(x / y);
        return results;
    }
}