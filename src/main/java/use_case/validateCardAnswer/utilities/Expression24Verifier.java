package use_case.validateCardAnswer.utilities;

import entity.Card;

import java.util.*;

public class Expression24Verifier {

    private static final double TARGET = 24.0;
    private static final double EPS = 1e-6;

    public static boolean isValidSolution(String expr, List<Card> cards) {
        int a = cards.get(0).getValue();
        int b = cards.get(1).getValue();
        int c = cards.get(2).getValue();
        int d = cards.get(3).getValue();

        return isValidSolution(expr, a, b, c, d);

        // for now, use this. will make better later
    }

    // MAIN PUBLIC METHOD
    public static boolean isValidSolution(String expr, int... cards) {
        expr = expr.replaceAll("\\s+", "");

        // 1. Check illegal characters
        if (!expr.matches("[0-9+\\-*/()]+")) {
            return false;
        }

        // 2. Extract numbers from expression
        List<Integer> used = extractNumbers(expr);
        List<Integer> required = new ArrayList<>();
        for (int c : cards) required.add(c);

        Collections.sort(used);
        Collections.sort(required);

        if (!used.equals(required)) {
            return false;  // used numbers don't match the cards
        }

        // 3. Evaluate expression safely
        try {
            double result = evaluate(expr);
            return Math.abs(result - TARGET) < EPS;
        } catch (Exception e) {
            return false; // malformed or invalid math (e.g. /0)
        }
    }

    // Extracts integer tokens from the expression
    private static List<Integer> extractNumbers(String expr) {
        List<Integer> nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    nums.add(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                }
            }
        }
        if (sb.length() > 0) {
            nums.add(Integer.parseInt(sb.toString()));
        }
        return nums;
    }

    // Evaluate using shunting-yard → RPN → calculator
    private static double evaluate(String expr) {
        List<String> rpn = toRPN(expr);
        return evalRPN(rpn);
    }

    private static List<String> toRPN(String expr) {
        List<String> output = new ArrayList<>();
        Stack<Character> ops = new Stack<>();
        StringBuilder num = new StringBuilder();

        Map<Character, Integer> prec = Map.of(
                '+', 1, '-', 1,
                '*', 2, '/', 2
        );

        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) {
                num.append(c);
                continue;
            }
            if (num.length() > 0) {
                output.add(num.toString());
                num.setLength(0);
            }

            if ("+-*/".indexOf(c) >= 0) {
                while (!ops.isEmpty() &&
                        ops.peek() != '(' &&
                        prec.get(ops.peek()) >= prec.get(c)) {
                    output.add("" + ops.pop());
                }
                ops.push(c);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    output.add("" + ops.pop());
                }
                ops.pop(); // discard '('
            }
        }

        if (num.length() > 0) output.add(num.toString());
        while (!ops.isEmpty()) output.add("" + ops.pop());

        return output;
    }

    private static double evalRPN(List<String> rpn) {
        Stack<Double> stack = new Stack<>();
        for (String token : rpn) {
            if (token.matches("[0-9]+")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> {
                        if (Math.abs(b) < EPS) throw new ArithmeticException("div0");
                        stack.push(a / b);
                    }
                }
            }
        }
        return stack.pop();
    }

    // quick demo
    public static void main(String[] args) {
        System.out.println(isValidSolution("3*(7+6)-9", 3,6,7,9)); // true
        System.out.println(isValidSolution("6+6+6+6", 6,6,6,6));   // true
        System.out.println(isValidSolution("3+3+3+3", 3,6,7,9));   // false
        System.out.println(isValidSolution("3//3+3+3", 3,3,3,3));  // false (illegal)
    }
}
