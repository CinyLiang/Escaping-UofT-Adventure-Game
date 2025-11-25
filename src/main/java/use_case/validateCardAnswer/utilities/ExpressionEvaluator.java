package use_case.validateCardAnswer.utilities;

import error.InvalidInputException;
import java.lang.Integer;
import java.util.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionEvaluator{

    public static double evaluate(String expression) {
        return evaluateWithStacks(expression);
    }

    private static double evaluateWithStacks(String expression) {
//        System.out.println("Evaluation Result:  " + expression);

        // trim and deal with spaces
        expression = expression.replaceAll("\\s+", "");

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                // read digits
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                // deal with calculations wrapped in parentheses
                while (!operators.isEmpty() && operators.peek() != '(') {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop(); // pop '('
            } else if (isOperator(c)) {
                // prioritized calculations
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);
            }
        }

        // other calculations
        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        double result = numbers.pop();
//        System.out.println("Evaluation Result: " + result);
        return result;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        // prioritize multiplications and divisions
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (Math.abs(b) < 1e-10) {
                    throw new ArithmeticException("Zero Division Error");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
    // Checks whether the expression contains and only contains each of the four
    // given values once and whether the expression only contains the four
    // allowed operations
    public static boolean checkExprPrereq(String expression, List<Integer> nums){
        List<Integer> remainingNums = new ArrayList<>(nums);
        List<String> signs = Arrays.asList("+", "-", "*", "/", "(", ")");

        expression = expression.replaceAll("\\s+", "");
        String[] tokens = expression.split("(?=[+*/)(\\-])|(?<=[+*/)(\\-])");
        for (String token: tokens) {
            if (token.matches("\\d+")) {
                try {
                    int num = Integer.parseInt(token);
                    if (!remainingNums.remove(Integer.valueOf(num))) {
                        throw new InvalidInputException("Number " + num + " is " +
                                "not one of the given card value or was used " +
                                "multiple times!");
                    } else {
                        if (!signs.contains(token)) {
                            throw new InvalidInputException("Please only use the four " +
                                    "allowed operations and parentheses!");
                        }
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid input!");
                }
            }
            if (!remainingNums.isEmpty()) {
                throw new InvalidInputException("There is at least one unused card!");
            }
        }
        return true;
    }
}

