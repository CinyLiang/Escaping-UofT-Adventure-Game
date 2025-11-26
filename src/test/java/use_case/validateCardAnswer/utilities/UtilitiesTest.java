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
        assertTrue(Expression24Verifier.isValidSolution("2*3*4*1", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierValid2() {
        assertTrue(Expression24Verifier.isValidSolution("3+3*7*1", 3, 3, 7, 1));
    }

    @Test
    public void test24VerifierInvalidExpression() {
        assertFalse(Expression24Verifier.isValidSolution("(2*3)*4 =1", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierInvalidUsage() {
        assertFalse(Expression24Verifier.isValidSolution("3*8", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierInvalidUsage2() {
        assertFalse(Expression24Verifier.isValidSolution("---", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierValidCalc() {
        assertFalse(Expression24Verifier.isValidSolution("1*(2*(3/4))", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierInvalidCalc() {
        assertFalse(Expression24Verifier.isValidSolution("(2*(3*1))+4", 1, 2, 3, 4));
    }

    @Test
    public void test24VerifierError() {
        assertFalse(Expression24Verifier.isValidSolution("2/0*3*6", 6, 3, 2, 0));
    }

    @Test
    public void test24VerifierMinus() {
        assertFalse(Expression24Verifier.isValidSolution("2-0+3-6", 6, 3, 2, 0));
    }

    @Test
    public void testMinorIsValidSolution() {
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(4), new Card(1));
        assertTrue(Expression24Verifier.isValidSolution("1*2 * 3 * 4", cards));
    }

    // Test ExpressionEvaluator
    @Test
    public void testEvalCorrect() {
        assertEquals(24, ExpressionEvaluator.evaluate("((2-3)+4)*8"));
    }

    @Test
    public void testEvalWithDecimal() {
        assertEquals(10.0, ExpressionEvaluator.evaluate("2.5*4"));
    }

    @Test
    public void testEvalStrangeChar() {
        assertEquals(2, ExpressionEvaluator.evaluate("2="));
    }

    @Test
    public void testEvalStrangeChar2() {
        assertEquals(4, ExpressionEvaluator.evaluate("2-1+3"));
    }

    @Test
    public void testEvalPrecedence() {
        assertEquals(26, ExpressionEvaluator.evaluate("8*3+2"));
    }

    @Test
    public void testEvalPrecedence2() {
        assertEquals(26, ExpressionEvaluator.evaluate("2+8*3"));
    }

    @Test
    public void testEvalPrecedence3() {
        assertEquals(4, ExpressionEvaluator.evaluate("2+8/4"));
    }

    @Test
    public void testEvalPrecedence4() {
        assertEquals(0, ExpressionEvaluator.evaluate("8/4-2"));
    }

    @Test
    public void testEvalStrangeChar5() {
        assertEquals(2, ExpressionEvaluator.evaluate("5-1*3"));
    }


    @Test
    public void testEvalDivide() {
        assertEquals(4, ExpressionEvaluator.evaluate("2*8/4"));
    }

    @Test
    public void testApplyOp() {
        try {
            ExpressionEvaluator.evaluate("2*8/0");
            fail("Expected ArithmeticException was not thrown");
        } catch (ArithmeticException e) {
            assertEquals("Zero Division Error", e.getMessage());
        }
    }

    @Test
    public void testApplyIllegalOp() {
        try {
            ExpressionEvaluator.applyOperation('#', 2, 3);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Unknown operator"));
        }
    }

    @Test
    public void testApplyOpPlus() {
        double result = ExpressionEvaluator.applyOperation('+', 2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testApplyOpMinus() {
        double result = ExpressionEvaluator.applyOperation('-', 2, 3);
        assertEquals(1, result);
    }

    @Test
    public void testApplyOpMultiply() {
        double result = ExpressionEvaluator.applyOperation('*', 2, 3);
        assertEquals(6, result);
    }

    @Test
    public void testApplyOpDivide() {
        double result = ExpressionEvaluator.applyOperation('/',3 , 6);
        assertEquals(2, result);
    }

//    @Test
//    public void testValidExpression() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        assertTrue(checkExprPrereq("2*3*4*1", nums));
//    }
//
//    @Test
//    public void testNumberNotInCards() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        InvalidInputException exception = assertThrows(InvalidInputException.class,
//                () -> checkExprPrereq("2+5", nums));
//        assertTrue(exception.getMessage().contains("not one of the given card value"));
//    }
//
//    @Test
//    public void testNumberUsedTwice() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        InvalidInputException exception = assertThrows(InvalidInputException.class,
//                () -> checkExprPrereq("2+2", nums));
//        assertTrue(exception.getMessage().contains("used multiple times"));
//    }
//
//    @Test
//    public void testInvalidToken() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        InvalidInputException exception = assertThrows(InvalidInputException.class,
//                () -> checkExprPrereq("2+abc", nums));
//        assertTrue(exception.getMessage().contains("only use the four allowed operations"));
//    }
//
//    @Test
//    public void testNumberFormatException() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        // This might be hard to trigger since regex "\\d+" should prevent it
//        // But if you have edge cases like very large numbers:
//        InvalidInputException exception = assertThrows(InvalidInputException.class,
//                () -> checkExprPrereq("99999999999999999999", nums));
//        assertTrue(exception.getMessage().contains("Invalid input"));
//    }
//
//    @Test
//    public void testUnusedCards() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        InvalidInputException exception = assertThrows(InvalidInputException.class,
//                () -> checkExprPrereq("2+3", nums));
//        assertTrue(exception.getMessage().contains("unused card"));
//    }
//
//    @Test
//    public void testEmptyExpression() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        InvalidInputException exception = assertThrows(InvalidInputException.class,
//                () -> checkExprPrereq("", nums));
//        assertTrue(exception.getMessage().contains("unused card"));
//    }
//
//    @Test
//    public void testExpressionWithSpaces() {
//        List<Integer> nums = Arrays.asList(2, 3, 4, 1);
//        assertTrue(checkExprPrereq("2 + 3 * (4 - 1)", nums));
//    }


}
