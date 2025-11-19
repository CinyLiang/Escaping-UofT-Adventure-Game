package entity;

import java.util.*;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Arrays;
import entity.ExpressionEvaluator;
import entity.Card;

public class SolutionGenerator {
    private static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");

    public static List<String> find24Solutions(List<Card> cards) {
        List<Integer> numbers = new ArrayList<>();
        for (Card card : cards) {
            numbers.add(card.getValue());
        }

        Set<String> solutions = new HashSet<>();

        List<List<Integer>> perms = generatePermutations(numbers);

        for (List<Integer> perm : perms) {
            int a = perm.get(0), b = perm.get(1), c = perm.get(2), d = perm.get(3);

            for (String op1 : OPERATORS) {
                for (String op2 : OPERATORS) {
                    for (String op3 : OPERATORS) {
                        // Test the 5 most common expression patterns for 24 game
                        testExpression(solutions, "((" + a + op1 + b + ")" + op2 + c + ")" + op3 + d, 24);
                        testExpression(solutions, "(" + a + op1 + "(" + b + op2 + c + "))" + op3 + d, 24);
                        testExpression(solutions, "(" + a + op1 + b + ")" + op2 + "(" + c + op3 + d + ")", 24);
                        testExpression(solutions, a + op1 + "((" + b + op2 + c + ")" + op3 + d + ")", 24);
                        testExpression(solutions, a + op1 + "(" + b + op2 + "(" + c + op3 + d + "))", 24);
                    }
                }
            }
        }

        return new ArrayList<>(solutions);
    }

    public static boolean isSolvable(List<Card> cards) {
        int numSol = find24Solutions(cards).size();
        return (!(numSol == 0));
    }

    private static void testExpression(Set<String> solutions, String expr, int target) {
        try {
            if (Math.abs(ExpressionEvaluator.evaluate(expr) - target) < 0.0001) {
                solutions.add(expr);
            }
        } catch (Exception e) {

        }
    }

    private static List<List<Integer>> generatePermutations(List<Integer> numbers) {
        List<List<Integer>> perms = new ArrayList<>();
        int n = numbers.size();
        if (n == 0) {
            perms.add(new ArrayList<>());
            return perms;
        }
        if (n == 1) {
            perms.add(numbers);
            return perms;
        } else {
            for (int j = 0; j < n; j++) {
                int pivot = numbers.get(j);
                List<Integer> subnum = new ArrayList<>();
                subnum.addAll(numbers.subList(0, j));
                subnum.addAll(numbers.subList(j+1, n));

                List<List<Integer>> subperm = generatePermutations(subnum);
                for (List<Integer> numlst : subperm) {
                    numlst.add(pivot);
                    perms.add(numlst);
                }
            }

            return perms;
        }
    }
}