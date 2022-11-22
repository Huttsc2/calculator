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
        Calculator calculator = new Calculator();
        String input = "1+(1+1)";
        assertTrue(calculator.checkHasBracket(input));
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
        String input = "1+(2*(-2))-2";
        String expectedResult = "1+((-4.0))-2";
        String actualResult = calculator.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
}