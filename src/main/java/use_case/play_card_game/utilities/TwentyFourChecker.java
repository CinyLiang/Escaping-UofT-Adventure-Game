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

    static List<Double> cardParser(List<Card> cards) {
        List<Double> nums = new ArrayList<>();
        for (Card card : cards) {
            nums.add((double)card.getValue());
        }
        return nums;
    }

    private static boolean solve(List<Double> nums) {
        int n = nums.size();

        if (n == 1) {
            return Math.abs(nums.get(0) - TARGET) < EPS;
        }

        // Try all ordered pairs to combine
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                // Build the list of the remaining numbers
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) next.add(nums.get(k));
                }

                // Pick operands
                double x = nums.get(i);
                double y = nums.get(j);

                // Try all operations
                for (double val : generateResults(x, y)) {
                    next.add(val);
                    if (solve(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
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

    // quick manual test
    public static void main(String[] args) {
        System.out.println(canMake24(6, 6, 6, 6));   // -> true
        System.out.println(canMake24(1, 1, 1, 1));   // -> false
        System.out.println(canMake24(3, 9, 6, 7));   // -> true
    }
}
