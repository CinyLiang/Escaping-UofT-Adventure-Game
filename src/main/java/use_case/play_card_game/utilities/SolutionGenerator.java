package use_case.play_card_game.utilities;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

import entity.Card;
import use_case.validateCardAnswer.utilities.ExpressionEvaluator;

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
            int a = perm.get(0);
            int b = perm.get(1);
            int c = perm.get(2);
            int d = perm.get(3);

            for (String op1 : OPERATORS) {
                for (String op2 : OPERATORS) {
                    for (String op3 : OPERATORS) {
                        // Test the 5 most common expression patterns for 24 game
                        String exp1 = "((" + a + op1 + b + ")" + op2 + c + ")" + op3 + d;
                        String exp2 = "(" + a + op1 + "(" + b + op2 + c + "))" + op3 + d;
                        String exp3 = "(" + a + op1 + b + ")" + op2 + "(" + c + op3 + d + ")";
                        String exp4 = a + op1 + "((" + b + op2 + c + ")" + op3 + d + ")";
                        String exp5 = a + op1 + "(" + b + op2 + "(" + c + op3 + d + "))";
                        List<String> exps = Arrays.asList(exp1, exp2, exp3, exp4, exp5);
                        for (String exp : exps) {
                            if (testExpression(exp, 24)) {
                                solutions.add(exp);
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(solutions);
    }

    public static boolean isSolvable(List<Card> cards) {
        int numSol = find24Solutions(cards).size();
        return numSol != 0;
    }

    private static boolean testExpression(String expr, int target) {
        try {
            return Math.abs(ExpressionEvaluator.evaluate(expr) - target) < 0.0001;
        } catch (Exception ignored) {
            return false;
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
                List<Integer> numberSubset = new ArrayList<>();
                numberSubset.addAll(numbers.subList(0, j));
                numberSubset.addAll(numbers.subList(j+1, n));

                List<List<Integer>> permSubset = generatePermutations(numberSubset);
                for (List<Integer> numList : permSubset) {
                    numList.add(pivot);
                    perms.add(numList);
                }
            }

            return perms;
        }
    }


//    public static void main(String[] args) {
//        Set<String> solutions = new HashSet<>();
//        boolean yn = SolutionGenerator.testExpression("(5+3)*(6-3)", 24);
//        System.out.println(yn);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(ExpressionEvaluator.evaluate("(5+3)*(6-3)"));
//    }
}

