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
        Checking checking = new Checking();
        String input = "";
        assertTrue(checking.checkInputIsEmpty(input));
    }
    @Test
    void inputDataNotEmpty() {
        Checking checking = new Checking();
        String input = "1";
        assertFalse(checking.checkInputIsEmpty(input));
    }
    @Test
    void lastSymbolIncorrect() {
        Checking checking = new Checking();
        String input = "1+1+";
        assertFalse(checking.checkInputIsCorrect(input));
    }
    @Test
    void incorrectBrackets() {
        Checking checking = new Checking();
        String input = "1+((1-1)";
        assertFalse(checking.checkInputHasCorrectBrackets(input));
    }
    @Test
    void correctBrackets() {
        Checking checking = new Checking();
        String input = "(1+(1+1)+1)";
        assertTrue(checking.checkInputHasCorrectBrackets(input));
    }
    @Test
    void validSymbols() {
        Checking checking = new Checking();
        String input = "150()/*-+.";
        assertTrue(checking.checkHasOnlyCorrectSymbols(input));
    }
    @Test
    void invalidSymbols() {
        Checking checking = new Checking();
        String input = "qtp@%&";
        assertFalse(checking.checkHasOnlyCorrectSymbols(input));
    }
    @Test
    void mathSymbolsSequence() {
        Checking checking = new Checking();
        String input = "1++1";
        assertFalse(checking.checkMathSymbolsSequence(input));
    }
    @Test
    void correctFractionalNumbers() {
        Checking checking = new Checking();
        String input = "1.1+1.3";
        assertTrue(checking.checkFractionalNumbers(input));
    }
    @Test
    void incorrectFractionalNumbers() {
        Checking checking = new Checking();
        String input = "1.1+1.3.3";
        assertFalse(checking.checkFractionalNumbers(input));
    }
    @Test
    void mathSymbolBeforeClosingBracket() {
        Checking checking = new Checking();
        String input = "(1+1+)";
        assertFalse(checking.checkMathSymbolBeforeClosingBrackets(input));
    }
    @Test
    void substringInfinity() {
        Checking checking = new Checking();
        String input = "1+Infinity";
        assertTrue(checking.checkSubstringInfinity(input));
    }
    @Test
    void inputDataBrackets() {
        Checking checking = new Checking();
        String input = "1+(1+1)";
        assertTrue(checking.checkHasBracket(input));
    }
    @Test
    void moreThanSingleNumber() {
        Checking checking = new Checking();
        String input = "(-1+1)";
        assertTrue(checking.checkHasMoreThanSingleNumber(input));
    }
    @Test
    void openBracketsSinglePositiveNumber() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "1+(1)+1";
        String expectedResult = "1+1+1";
        String actualResult = countAndOpenBrackets.openBracketsWithPositiveNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void startClosedBracketsPosition() {
        Localizing localizing = new Localizing();
        String input = "1+(1+(1+2)+1)-2";
        int expectedResult = 5;
        int actualResult = localizing.localizeStartPositionBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void endClosedBracketsPosition() {
        Localizing localizing = new Localizing();
        String input = "1+(1+(1+2)+1)-2";
        int expectedResult = 9;
        int actualResult = localizing.localizeEndPositionBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void substringInClosedBrackets() {
        Localizing localizing = new Localizing();
        String input = "1+(1+(1+2)+1)-2";
        String expectedResult = "1+2";
        String actualResult = localizing.localizeClosedBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterPlusSymbol() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "1+(1+(-2)+1)-2";
        String expectedResult = "1+(1-2+1)-2";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterMinusSymbol() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "1+(1-(-2)+1)-2";
        String expectedResult = "1+(1+2+1)-2";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterBracket() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "1+((-2)+1)-2";
        String expectedResult = "1+(-2+1)-2";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterBracketAndMinus() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "1+(-(-2))-2";
        String expectedResult = "1+(2)-2";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterMultiplyOrDivide() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "1+(1-2*(-2)+1)-2";
        String expectedResult = "1+(1-(-4.0)+1)-2";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsBeforeMultiplyOrDivide() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "1+(1+(-2)*2+1)-2";
        String expectedResult = "1+(1-2*2+1)-2";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void moreThanTwoNumbers() {
        Checking checking = new Checking();
        String input = "1+2+3";
        assertTrue(checking.checkHasMoreThanTwoNumbers(input));
    }
    @Test
    void twoOrLessNumbers() {
        Checking checking = new Checking();
        String input = "-2+3";
        assertFalse(checking.checkHasMoreThanTwoNumbers(input));
    }
    @Test
    void startPositionToCount() {
        Localizing localizing = new Localizing();
        String input = "1+2*3+4";
        int expectedResult = 2;
        int actualResult = localizing.localizeStartingPositionToCount(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void endPositionToCount() {
        Localizing localizing = new Localizing();
        String input = "1+2*3+4";
        int expectedResult = 5;
        int actualResult = localizing.localizeEndingPositionToCount(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void substringToCount() {
        Localizing localizing = new Localizing();
        String input = "1+2*3+4";
        String expectedResult = "2*3";
        String actualResult = localizing.localizeSubstringToCount(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void multiplyOrDivideSymbol() {
        Checking checking = new Checking();
        String input = "1+2*3+4";
        assertTrue(checking.checkMultiplyOrDivideSymbol(input));
    }
    @Test
    void multiplyOrDivideSymbolPosition() {
        Localizing localizing = new Localizing();
        String input = "1+2*3+4";
        int expectedResult = 3;
        int actualResult = localizing.localizeMultipleOrDivideSymbol(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void plusOrMinusSymbolPosition() {
        Localizing localizing = new Localizing();
        String input = "1+2*3+4";
        int expectedResult = 1;
        int actualResult = localizing.localizePlusOrMinusSymbol(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void multiplySymbol() {
        Checking checking = new Checking();
        String input = "2*3";
        assertTrue(checking.checkHasMultiplySymbol(input));
    }
    @Test
    void divideSymbol() {
        Checking checking = new Checking();
        String input = "2/3";
        assertTrue(checking.checkHasDivideSymbol(input));
    }
    @Test
    void minusSymbol() {
        Checking checking = new Checking();
        String input = "2-3";
        assertTrue(checking.checkHasMinusSymbol(input));
    }
    @Test
    void plusSymbol() {
        Checking checking = new Checking();
        String input = "3+4";
        assertTrue(checking.checkHasPlusSymbol(input));
    }
    @Test
    void multiplySubstring() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "-2*3";
        String expectedResult = "-6.0";
        String actualResult = countAndOpenBrackets.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void divideSubstring() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "6/3";
        String expectedResult = "2.0";
        String actualResult = countAndOpenBrackets.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSubstring() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "2-3";
        String expectedResult = "-1.0";
        String actualResult = countAndOpenBrackets.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void plusSubstring() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "-6+-3";
        String expectedResult = "-9.0";
        String actualResult = countAndOpenBrackets.countSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSingleNumberInBracketsLastInString() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "6-(-3)";
        String expectedResult = "6+3";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSingleNumberInBracketsFirstInString() {
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        String input = "(-3)+6";
        String expectedResult = "-3+6";
        String actualResult = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void correctMathSymbolAfterOpenedBracket() {
        Checking checking = new Checking();
        String input = "1+(1)";
        assertTrue(checking.checkMathSymbolAfterOpenedBracket(input));
    }
}