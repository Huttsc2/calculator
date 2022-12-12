import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorUnitTest {

    @Test
    void inputDataAddMultiply() {
        Calculator calculator = new Calculator();
        String input = "1+(1+1(-1)3)+1";
        String expectedResult = "1+(1+1*(-1)*3)+1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualResult = calculator.addStringAndCheckIt();
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void inputDataDivisionByZero() {
        Calculator calculator = new Calculator();
        String input = "1+(1+1/0(-1)*3)+1";
        String expectedResult = "Infinity";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(expectedResult, calculator.addStringAndCheckIt());
    }
    @Test
    void inputDataEmpty() {
        Calculator calculator = new Calculator();
        String input = "";
        assertTrue(calculator.checkInputIsEmpty(input));
    }
    @Test
    void inputDataNotEmpty() {
        Calculator calculator = new Calculator();
        String input = "1";
        assertFalse(calculator.checkInputIsEmpty(input));
    }
    @Test
    void lastSymbolIncorrect() {
        Calculator calculator = new Calculator();
        String input = "1+1+";
        assertFalse(calculator.checkInputIsCorrect(input));
    }
    @Test
    void incorrectBrackets() {
        Calculator calculator = new Calculator();
        String input = "1+((1-1)";
        assertFalse(calculator.checkInputHasCorrectBrackets(input));
    }
    @Test
    void correctBrackets() {
        Calculator calculator = new Calculator();
        String input = "(1+(1+1)+1)";
        assertTrue(calculator.checkInputHasCorrectBrackets(input));
    }
    @Test
    void validSymbols() {
        Calculator calculator = new Calculator();
        String input = "150()/*-+.";
        assertTrue(calculator.checkHasOnlyCorrectSymbols(input));
    }
    @Test
    void invalidSymbols() {
        Calculator calculator = new Calculator();
        String input = "qtp@%&";
        assertFalse(calculator.checkHasOnlyCorrectSymbols(input));
    }
    @Test
    void mathSymbolsSequence() {
        Calculator calculator = new Calculator();
        String input = "1++1";
        assertFalse(calculator.checkMathSymbolsSequence(input));
    }
    @Test
    void correctFractionalNumbers() {
        Calculator calculator = new Calculator();
        String input = "1.1+1.3";
        assertTrue(calculator.checkFractionalNumbers(input));
    }
    @Test
    void incorrectFractionalNumbers() {
        Calculator calculator = new Calculator();
        String input = "1.1+1.3.3";
        assertFalse(calculator.checkFractionalNumbers(input));
    }
    @Test
    void mathSymbolBeforeClosingBracket() {
        Calculator calculator = new Calculator();
        String input = "(1+1+)";
        assertFalse(calculator.checkMathSymbolBeforeClosingBrackets(input));
    }
    @Test
    void substringInfinity() {
        Calculator calculator = new Calculator();
        String input = "1+Infinity";
        assertTrue(calculator.checkSubstringInfinity(input));
    }
    @Test
    void inputDataBrackets() {
        Checking checking = new Checking();
        String input = "1+(1+1)";
        assertTrue(checking.checkHasBracket(input));
    }
    @Test
    void moreThanSingleNumber() {
        Calculator calculator = new Calculator();
        String input = "(-1+1)";
        assertTrue(calculator.checkHasMoreThanSingleNumber(input));
    }
    @Test
    void openBracketsSinglePositiveNumber() {
        Calculator calculator = new Calculator();
        String input = "1+(1)+1";
        String expectedResult = "1+1+1";
        String actualResult = calculator.openBracketsWithPositiveNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void startClosedBracketsPosition() {
        Calculator calculator = new Calculator();
        String input = "1+(1+(1+2)+1)-2";
        int expectedResult = 5;
        int actualResult = calculator.localizeStartPositionBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void endClosedBracketsPosition() {
        Calculator calculator = new Calculator();
        String input = "1+(1+(1+2)+1)-2";
        int expectedResult = 9;
        int actualResult = calculator.localizeEndPositionBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void substringInClosedBrackets() {
        Calculator calculator = new Calculator();
        String input = "1+(1+(1+2)+1)-2";
        String expectedResult = "1+2";
        String actualResult = calculator.localizeClosedBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterPlusSymbol() {
        Calculator calculator = new Calculator();
        String input = "1+(1+(-2)+1)-2";
        String expectedResult = "1+(1-2+1)-2";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterMinusSymbol() {
        Calculator calculator = new Calculator();
        String input = "1+(1-(-2)+1)-2";
        String expectedResult = "1+(1+2+1)-2";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterBracket() {
        Calculator calculator = new Calculator();
        String input = "1+((-2)+1)-2";
        String expectedResult = "1+(-2+1)-2";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterBracketAndMinus() {
        Calculator calculator = new Calculator();
        String input = "1+(-(-2))-2";
        String expectedResult = "1+(2)-2";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterMultiplyOrDivide() {
        Calculator calculator = new Calculator();
        String input = "1+(1-2*(-2)+1)-2";
        String expectedResult = "1+(1-(-4.0)+1)-2";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsBeforeMultiplyOrDivide() {
        Calculator calculator = new Calculator();
        String input = "1+(1+(-2)*2+1)-2";
        String expectedResult = "1+(1-2*2+1)-2";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void moreThanTwoNumbers() {
        Calculator calculator = new Calculator();
        String input = "1+2+3";
        assertTrue(calculator.checkHasMoreThanTwoNumbers(input));
    }
    @Test
    void twoOrLessNumbers() {
        Calculator calculator = new Calculator();
        String input = "-2+3";
        assertFalse(calculator.checkHasMoreThanTwoNumbers(input));
    }
    @Test
    void startPositionToCount() {
        Calculator calculator = new Calculator();
        String input = "1+2*3+4";
        int expectedResult = 2;
        int actualResult = calculator.localizeStartingPositionToCount(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void endPositionToCount() {
        Calculator calculator = new Calculator();
        String input = "1+2*3+4";
        int expectedResult = 5;
        int actualResult = calculator.localizeEndingPositionToCount(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void substringToCount() {
        Calculator calculator = new Calculator();
        String input = "1+2*3+4";
        String expectedResult = "2*3";
        String actualResult = calculator.localizeSubstringToCount(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void multiplyOrDivideSymbol() {
        Calculator calculator = new Calculator();
        String input = "1+2*3+4";
        assertTrue(calculator.checkMultiplyOrDivideSymbol(input));
    }
    @Test
    void multiplyOrDivideSymbolPosition() {
        Calculator calculator = new Calculator();
        String input = "1+2*3+4";
        int expectedResult = 3;
        int actualResult = calculator.localizeMultipleOrDivideSymbol(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void plusOrMinusSymbolPosition() {
        Calculator calculator = new Calculator();
        String input = "1+2*3+4";
        int expectedResult = 1;
        int actualResult = calculator.localizePlusOrMinusSymbol(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void multiplySymbol() {
        Calculator calculator = new Calculator();
        String input = "2*3";
        assertTrue(calculator.checkHasMultiplySymbol(input));
    }
    @Test
    void divideSymbol() {
        Calculator calculator = new Calculator();
        String input = "2/3";
        assertTrue(calculator.checkHasDivideSymbol(input));
    }
    @Test
    void minusSymbol() {
        Calculator calculator = new Calculator();
        String input = "2-3";
        assertTrue(calculator.checkHasMinusSymbol(input));
    }
    @Test
    void plusSymbol() {
        Calculator calculator = new Calculator();
        String input = "3+4";
        assertTrue(calculator.checkHasPlusSymbol(input));
    }
    @Test
    void multiplySubstring() {
        Calculator calculator = new Calculator();
        String input = "-2*3";
        String expectedResult = "-6.0";
        String actualResult = calculator.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void divideSubstring() {
        Calculator calculator = new Calculator();
        String input = "6/3";
        String expectedResult = "2.0";
        String actualResult = calculator.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSubstring() {
        Calculator calculator = new Calculator();
        String input = "2-3";
        String expectedResult = "-1.0";
        String actualResult = calculator.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void plusSubstring() {
        Calculator calculator = new Calculator();
        String input = "-6+-3";
        String expectedResult = "-9.0";
        String actualResult = calculator.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSingleNumberInBracketsLastInString() {
        Calculator calculator = new Calculator();
        String input = "6-(-3)";
        String expectedResult = "6+3";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSingleNumberInBracketsFirstInString() {
        Calculator calculator = new Calculator();
        String input = "(-3)+6";
        String expectedResult = "-3+6";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void correctMathSymbolAfterOpenedBracket() {
        Calculator calculator = new Calculator();
        String input = "1+(1)";
        assertTrue(calculator.checkMathSymbolAfterOpenedBracket(input));
    }
}