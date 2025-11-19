package entity;

import error.InvalidInputException;
import java.lang.Integer;
import java.util.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionEvaluator{
    public static int evaluate(String expression){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try{
            Object result = engine.eval(expression);
            return ((Number) result).intValue();
        } catch (ScriptException e) {
            throw new InvalidInputException("Invalid entry" + expression);
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

