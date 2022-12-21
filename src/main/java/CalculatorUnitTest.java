import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorUnitTest {

    @Test
    void inputDataAddMultiply() {
        AddStringToCalculate addStringToCalculate = new AddStringToCalculate();
        String input = "1+(1+1(-1)3)+1";
        String expectedResult = "1+(1+1*(-1)*3)+1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String actualResult = addStringToCalculate.addStringAndCheckIt();
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void inputDataDivisionByZero() {
        AddStringToCalculate addStringToCalculate = new AddStringToCalculate();
        String input = "1+(1+1/0(-1)*3)+1";
        String expectedResult = "Infinity";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(expectedResult, addStringToCalculate.addStringAndCheckIt());
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
        String input = "1)2";
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
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "1+(1)+1";
        String expectedResult = "1+1+1";
        String actualResult = calculateAndOpenBrackets.openBracketsWithPositiveNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void startClosedBracketsPosition() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+(1+(1+2)+1)-2";
        int expectedResult = 5;
        int actualResult = positionSearch.searchStartPositionClosedBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void endClosedBracketsPosition() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+(1+(1+2)+1)-2";
        int expectedResult = 9;
        int actualResult = positionSearch.searchEndPositionBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void substringInClosedBrackets() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+(1+(1+2)+1)-2";
        String expectedResult = "1+2";
        String actualResult = positionSearch.searchClosedBrackets(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterPlusSymbol() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "1+(1+(-2)+1)-2";
        String expectedResult = "1+(1-2+1)-2";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterMinusSymbol() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "1+(1-(-2)+1)-2";
        String expectedResult = "1+(1+2+1)-2";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterBracket() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "1+((-2)+1)-2";
        String expectedResult = "1+(-2+1)-2";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterBracketAndMinus() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "1+(-(-2))-2";
        String expectedResult = "1+(2)-2";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsAfterMultiplyOrDivide() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "1+(1-2*(-2)+1)-2";
        String expectedResult = "1+(1-(-4.0)+1)-2";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void openBracketsBeforeMultiplyOrDivide() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "1+(1+(-2)*2+1)-2";
        String expectedResult = "1+(1-2*2+1)-2";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
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
    void startPositionToCalculate() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+2*3+4";
        int expectedResult = 2;
        int actualResult = positionSearch.searchStartingPositionForCalculations(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void endPositionToCalculate() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+2*3+4";
        int expectedResult = 5;
        int actualResult = positionSearch.searchEndPositionForCalculations(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void substringToCalculate() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+2*3+4";
        String expectedResult = "2*3";
        String actualResult = positionSearch.searchSubstringForCalculations(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void multiplyOrDivideSymbolPosition() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+2*3+4";
        int expectedResult = 3;
        int actualResult = positionSearch.searchMultipleOrDivideSymbol(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void plusOrMinusSymbolPosition() {
        PositionSearch positionSearch = new PositionSearch();
        String input = "1+2*3+4";
        int expectedResult = 1;
        int actualResult = positionSearch.searchPlusOrMinusSymbol(input);
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
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "-2*3";
        String expectedResult = "-6.0";
        String actualResult = calculateAndOpenBrackets.calculatedSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void divideSubstring() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "6/3";
        String expectedResult = "2.0";
        String actualResult = calculateAndOpenBrackets.calculatedSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSubstring() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "2-3";
        String expectedResult = "-1.0";
        String actualResult = calculateAndOpenBrackets.calculatedSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void plusSubstring() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "-6+-3";
        String expectedResult = "-9.0";
        String actualResult = calculateAndOpenBrackets.calculatedSubstring(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSingleNumberInBracketsLastInString() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "6-(-3)";
        String expectedResult = "6+3";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void minusSingleNumberInBracketsFirstInString() {
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        String input = "(-3)+6";
        String expectedResult = "-3+6";
        String actualResult = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(input);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void correctMathSymbolAfterOpenedBracket() {
        Checking checking = new Checking();
        String input = "1+(1)";
        assertTrue(checking.checkMathSymbolAfterOpenedBracket(input));
    }
}