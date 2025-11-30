package use_case.validateCardAnswer.utilities;

import entity.Card;
import error.InvalidInputException;
import org.junit.jupiter.api.Test;
import use_case.validateCardAnswer.utilities.Expression24Verifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//import static use_case.validateCardAnswer.utilities.ExpressionEvaluator.checkExprPrereq;

public class UtilitiesTest {
    @Test
    public void test24VerifierValid() {
        // Test isValidSolution with a correct expression
        assertTrue(Expression24Verifier.isValidSolution("2*3*4*1", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierValid2() {
        // Test isValidSolution with a correct expression with duplicate card numbers
        assertTrue(Expression24Verifier.isValidSolution("3+3*7*1", 3, 3, 7, 1));
    }

    @Test
    public void test24VerifierInvalidExpression() {
        // Test isValidSolution with illegal operators in the input
        assertFalse(Expression24Verifier.isValidSolution("(2*3)*4 =1", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierInvalidUsage() {
        // Test isValidSolution with incorrect usage of the cards
        assertFalse(Expression24Verifier.isValidSolution("3*8", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierInvalidUsage2() {
        // Test isValidSolution with an input that includes no digits
        assertFalse(Expression24Verifier.isValidSolution("---", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierValidCalc() {
        // Test isValidSolution with a correct expression that involves division
        assertFalse(Expression24Verifier.isValidSolution("1*(2*(3/4))", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierInvalidCalc() {
        // Test isValidSolution with an incorrect expression with nested parentheses
        assertFalse(Expression24Verifier.isValidSolution("(2*(3*1))+4", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierError() {
        // Test isValidSolution with an input expression that causes the ZeroDivisionError
        assertFalse(Expression24Verifier.isValidSolution("2/0*3*6", 6, 3, 2, 0));
    }

    @Test
    public void test24VerifierMinus() {
        // Test isValidSolution with an incorrect expression that involves substraction
        assertFalse(Expression24Verifier.isValidSolution("2-0+3-6", 6, 3, 2, 0));
    }

    @Test
    public void testMinorIsValidSolution() {
        // Test isValidSolution with a correct expression that includes spaces
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(4), new Card(1));
        assertTrue(Expression24Verifier.isValidSolution("1*2 * 3 * 4", cards));
    }

    // Test ExpressionEvaluator
    @Test
    public void testEvalCorrect() {
        // Test evaluate with a valid expression that involves only integers
        assertEquals(24, ExpressionEvaluator.evaluate("((2-3)+4)*8"));
    }

    @Test
    public void testEvalWithDecimal() {
        // Test evaluate with a valid expression that includes decimals
        assertEquals(10.0, ExpressionEvaluator.evaluate("2.5*4"));
    }

    @Test
    public void testEvalStrangeChar() {
        // Test evaluate with an invalid expression that involves illegal operators
        assertEquals(2, ExpressionEvaluator.evaluate("2="));
    }

    @Test
    public void testEvalPrecedencePM() {
        // Test evaluate with a valid expression with an expression that involves operators of the same precedence (additiona and subtraction)
        assertEquals(4, ExpressionEvaluator.evaluate("2-1+3"));
    }

    @Test
    public void testEvalPrecedence() {
        // Test evaluate with a valid expression with an expression that involves multiplication before addition
        assertEquals(26, ExpressionEvaluator.evaluate("8*3+2"));
    }

    @Test
    public void testEvalPrecedence2() {
        // Test evaluate with a valid expression with an expression that involves addition before multiplication
        assertEquals(26, ExpressionEvaluator.evaluate("2+8*3"));
    }

    @Test
    public void testEvalPrecedence3() {
        // Test evaluate with a valid expression with an expression that involves addition before division
        assertEquals(4, ExpressionEvaluator.evaluate("2+8/4"));
    }

    @Test
    public void testEvalPrecedence4() {
        // Test evaluate with a valid expression with an expression that involves division before subtraction
        assertEquals(0, ExpressionEvaluator.evaluate("8/4-2"));
    }

    @Test
    public void testEvalStrangeChar5() {
        // Test evaluate with a valid expression with an expression that involves subtraction before multiplication
        assertEquals(2, ExpressionEvaluator.evaluate("5-1*3"));
    }


    @Test
    public void testEvalDivide() {
        // Test evaluate with a valid expression with an expression that involves operators of the same precedence (multiplication and division)
        assertEquals(4, ExpressionEvaluator.evaluate("2*8/4"));
    }

    @Test
    public void testApplyOp() {
        // Test evaluate with an invalid expression that causes ZeroDivisionError
        try {
            ExpressionEvaluator.evaluate("2*8/0");
            fail("Expected ArithmeticException was not thrown");
        } catch (ArithmeticException e) {
            assertEquals("Zero Division Error", e.getMessage());
        }
    }

    @Test
    public void testApplyIllegalOp() {
        // Test applyOperation with an invalid operator
        try {
            ExpressionEvaluator.applyOperation('#', 2, 3);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unknown operator"));
        }
    }

    @Test
    public void testApplyOpPlus() {
        // Test applyOperation with plus
        double result = ExpressionEvaluator.applyOperation('+', 2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testApplyOpMinus() {
        // Test applyOperation with minus
        double result = ExpressionEvaluator.applyOperation('-', 2, 3);
        assertEquals(1, result);
    }

    @Test
    public void testApplyOpMultiply() {
        // Test applyOperation with multiply
        double result = ExpressionEvaluator.applyOperation('*', 2, 3);
        assertEquals(6, result);
    }

    @Test
    public void testApplyOpDivide() {
        // Test applyOperation with divide
        double result = ExpressionEvaluator.applyOperation('/',3 , 6);
        assertEquals(2, result);
    }

}
