import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorUnitTest {

    @Test
    void addMultiplyCorrect() {
        Calculator calculator = new Calculator();
        assertEquals("1*(1+1)*1", calculator.addMultiplySymbolBeforeAndAfterBracketsIfInputStringDoesNotHaveAnyMathSymbolThere("1(1+1)1"));
    }
    @Test
    void firstCorrectLastSignFalse() {
        Calculator calculator = new Calculator();
        assertFalse(calculator.inputStringIsCorrect("1+"));
    }
    @Test
    void firstCorrectTrue() {
        Calculator calculator = new Calculator();
        assertTrue(calculator.inputStringIsCorrect("1+1"));
    }
    @Test
    void checkEmptyExampleTrue() {
        Calculator calculator = new Calculator();
        assertTrue(calculator.inputStringIsEmpty(""));
    }
    @Test
    void checkEmptyExampleFalse() {
        Calculator calculator = new Calculator();
        assertFalse(calculator.inputStringIsEmpty("1"));
    }
    /*@Test
    void checkBracketsTrue() {
        Calculator calculator = new Calculator();
        assertTrue(calculator.s("1+(1+1)"));
    }
    @Test
    void checkBracketsFalse() {
        assertFalse(Calculator.checkBrackets("1+((1+1)"));
    }
    @Test
    void checkSymbolsTrue() {
        assertTrue(Calculator.checkSymbol("157()-+/.*"));
    }
    @Test
    void checkSymbolsFalse() {
        assertFalse(Calculator.checkSymbol("123q&"));
    }
    @Test
    void checkSignsTrue() {
        assertTrue(Calculator.checkSigns("(1+1.1)"));
    }
    @Test
    void checkSignsFalse() {
        assertFalse(Calculator.checkSigns("1+-1"));
    }
    @Test
    void checkDotsTrue() {
        assertTrue(Calculator.checkDots("1.1+2.3+1.234"));
    }
    @Test
    void checkDotsFalse1() {
        assertFalse(Calculator.checkDots("1.1.1+123+1.123"));
    }
    @Test
    void checkDotsFalse2() {
        assertFalse(Calculator.checkDots(".11+123+1.123"));
    }
    @Test
    void checkDivisionByZeroTrue() {
        assertTrue(Calculator.checkDivisionByZero("1+1/0"));
    }
    @Test
    void checkDivisionByZeroFalse() {
        assertFalse(Calculator.checkDivisionByZero("1+1/0.1"));
    }
    @Test
    void checkDivisionByZeroTrue2() {
        assertTrue(Calculator.checkDivisionByZero("1+1/0+1"));
    }
    @Test
    void checkInfinityTrue() {
        assertTrue(Calculator.checkInfinity("1+Infinity*2"));
    }
    @Test
    void checkInfinityFalse() {
        assertFalse(Calculator.checkInfinity("1+23+1"));
    }
    @Test
    void isHasBracketsTrue() {
        assertTrue(Calculator.isHasBrackets("123+(123+"));
    }
    @Test
    void isHasBracketsFalse() {
        assertFalse(Calculator.isHasBrackets("+123)+123"));
    }
    @Test
    void isHasBracketsWithNoSingleNumberTure() {
        assertTrue(Calculator.isHasBracketsWithNoSingleNumber("1+(1+1)"));
    }
    @Test
    void isHasBracketsWithNoSingleMinNumbFalse() {
        assertFalse(Calculator.isHasBracketsWithNoSingleNumber("1+(-1)"));
    }
    @Test
    void isHasBracketsWithNoSinglePlusNumbFalse() {
        assertFalse(Calculator.isHasBracketsWithNoSingleNumber("1+(1)"));
    }
    @Test
    void startBracketsTest1() {
        assertEquals(3, Calculator.startBrackets("10+(1+1)+1+(1+1)"));
    }
    @Test
    void startBracketsTest2() {
        assertEquals(5, Calculator.startBrackets("1+(1+(1+1)+1)+1"));
    }
    @Test
    void startBracketsTest3() {
        assertEquals(0, Calculator.startBrackets("123"));
    }
    @Test
    void endBracketsTest1() {
        assertEquals(7, Calculator.endBrackets("1+1(1+1)"));
    }
    @Test
    void endBracketsTest2() {
        assertEquals(0, Calculator.endBrackets("1+1+1"));
    }
    @Test
    void localExampleinBracketsTest1() {
        assertEquals("1+1", Calculator.localExampleInBrackets("2+(1+1)+2"));
    }
    @Test
    void localExampleinBracketsTest2() {
        assertEquals("1+1", Calculator.localExampleInBrackets("2+(3+(1+1)+3)+2"));
    }
    @Test
    void localExampleinBracketsTest3() {
        assertEquals("1+1", Calculator.localExampleInBrackets("2+(1+1)+2+(2+2)"));
    }
    @Test
    void isHasMoreThanSingleNumberTrue() {
        assertTrue(Calculator.isHasMoreThanSingleNumber("1+2"));
    }
    @Test
    void isHasMoreThanSingleNumberMinFalse() {
        assertFalse(Calculator.isHasMoreThanSingleNumber("-1"));
    }
    @Test
    void isHasMoreThanSingleNumberPlusFalse() {
        assertFalse(Calculator.isHasMoreThanSingleNumber("1"));
    }
    @Test
    void isHasPlusNumberInBracketsTrue() {
        assertTrue(Calculator.isHasPlusNumbersInBrackets("1-(1+1+(1)+1)+1"));
    }
    @Test
    void isHasPlusNumberInBracketsFalse() {
        assertFalse(Calculator.isHasPlusNumbersInBrackets("1-(-1)+1"));
    }
    @Test
    void openPlusSingleNumberInBracketsTest1() {
        assertEquals("1+1+1", Calculator.openPlusSingleNumbersBrackets("1+(1)+1"));
    }
    @Test
    void openPlusSingleNumberInBracketsTest2() {
        assertEquals("1-1+1", Calculator.openPlusSingleNumbersBrackets("1-(1)+1"));
    }
    @Test
    void openPlusSingleNumberInBracketsTest3() {
        assertEquals("1*1-1", Calculator.openPlusSingleNumbersBrackets("1*(1)-1"));
    }
    @Test
    void openPlusSingleNumberInBracketsTest4() {
        assertEquals("1+(-2)+3+4", Calculator.openPlusSingleNumbersBrackets("1+(-2)+(3)+4"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest1() {
        assertEquals("1+1+1", Calculator.openMinusSingleNumberInBrackets("1-(-1)+1"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest2() {
        assertEquals("1-1", Calculator.openMinusSingleNumberInBrackets("1+(-1)"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest3() {
        assertEquals("1+1+1", Calculator.openMinusSingleNumberInBrackets("1-(-1)+1"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest4() {
        assertEquals("1+(-1)", Calculator.openMinusSingleNumberInBrackets("1+((-1))"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest5() {
        assertEquals("1+(1)+1", Calculator.openMinusSingleNumberInBrackets("1+(-(-1))+1"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest6() {
        assertEquals("2+(-6.0)+3", Calculator.openMinusSingleNumberInBrackets("2+2*(-3)+3"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest7() {
        assertEquals("2+(-6.0)+(-3)", Calculator.openMinusSingleNumberInBrackets("2+2*(-3)+(-3)"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest8() {
        assertEquals("2+(6.0)+3", Calculator.openMinusSingleNumberInBrackets("2+(-2)*(-3)+3"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest9() {
        assertEquals("2+(-6.0)+3", Calculator.openMinusSingleNumberInBrackets("2+(-2)*3+3"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest10() {
        assertEquals("((2.0)+3)", Calculator.openMinusSingleNumberInBrackets("(-4/(-2)+3)"));
    }
    @Test
    void openMinusSingleNumberInBracketsTest11() {
        assertEquals("(1-(-2.0))", Calculator.openMinusSingleNumberInBrackets("(1-4/(-2))"));
    }
    @Test
    void isHasMoreThanTwoNumbersTrue() {
        assertTrue(Calculator.isHasMoreThanTwoNumbers("-1.3+1.123+123"));
    }
    @Test
    void isHasMoreThanTwoNumbersFalse() {
        assertFalse(Calculator.isHasMoreThanTwoNumbers("-1.3+1.123"));
    }
    @Test
    void isHasMultiplyOrDivideTrueMult() {
        assertTrue(Calculator.isHasMultiplyOrDivide("1*1"));
    }
    @Test
    void isHasMultiplyOrDivideTrueDiv() {
        assertTrue(Calculator.isHasMultiplyOrDivide("1/1"));
    }
    @Test
    void isHasMultiplyOrDivideFalse() {
        assertFalse(Calculator.isHasMultiplyOrDivide("1+1"));
    }
    @Test
    void localMultiplyOrDivideTestMult() {
        assertEquals(3, Calculator.localMultiplyOrDivideSign("1+1*1+3"));
    }
    @Test
    void localMultiplyOrDivideTestDiv() {
        assertEquals(3, Calculator.localMultiplyOrDivideSign("1+1/1+3"));
    }
    @Test
    void localPlusOrMinusTestPlus() {
        assertEquals(2, Calculator.localPlusOrMinusSign("-1+3+2"));
    }
    @Test
    void localPlusOrMinusTestMinus() {
        assertEquals(2, Calculator.localPlusOrMinusSign("-1-3+2"));
    }
    @Test
    void isItFirstSigneTest1() {
        assertTrue(Calculator.isItFirstSign("(-1)+13+13)"));
    }
    @Test
    void isItFirstSigneTest2() {
        assertFalse(Calculator.isItFirstSign("1+1*3"));
    }
    @Test
    void isItLastSigneTest1() {
        assertTrue(Calculator.isItLastSign("(-1)*13)"));
    }
    @Test
    void isItLastSigneTest2() {
        assertFalse(Calculator.isItLastSign("1*1+3"));
    }
    @Test
    void countTest1() {
        assertEquals("5.0", Calculator.countTemp("2.5*2"));
    }
    @Test
    void countTest2() {
        assertEquals("-3.0", Calculator.countTemp("-6/2"));
    }
    @Test
    void countTest3() {
        assertEquals("6.0", Calculator.countTemp("-2+8"));
    }
    @Test
    void countTest4() {
        assertEquals("-5.0", Calculator.countTemp("5.5-10.5"));
    }
    @Test
    void countFinalTest1() {
        assertEquals("5.0", Calculator.countFinalExample("1+1+1+1+1"));
    }
    @Test
    void countFinalTest2() {
        assertEquals("division by zero error", Calculator.countFinalExample("3+Infinity"));
    }
    @Test
    void openSingleNumberInBracketTest1() {
        assertEquals("1*2/3", Calculator.openBrackets("1*(2)/3"));
    }
    @Test
    void countBracketsTest1() {
        assertEquals("1*2/3", Calculator.countBrackets("1*(2)/3"));
    }
    @Test
    void countBracketsTest2() {
        assertEquals("1-2+3", Calculator.countBrackets("1+(-2)+3"));
    }
    @Test
    void countBracketsTest3() {
        assertEquals("1+2+2.0+4", Calculator.countBrackets("1+2+(-2+2+2)+4"));
    }
    @Test
    void countBracketsTest4() {
    }
    @Test
    void localStartTest() {
        assertEquals(4, Calculator.localStart("1+2+13*14+5"));
    }
    @Test
    void localEndTest() {
        assertEquals(9, Calculator.localEnd("1+2+13*14+5"));
    }
    @Test
    void localMinusTrue() {
        assertTrue(Calculator.localMinus("1-1"));
    }
    @Test
    void localMinusFalse() {
        assertFalse(Calculator.localMinus("-1+1"));
    }
    @Test
    void firstCorrectEmptyExample() {
        assertFalse(Calculator.firstCorrect(""));
    }
    @Test
    void checkSignBeforeBracketsTrue() {
        assertTrue(Calculator.checkSignBeforeBrackets("1+(2+3)+4"));
    }
    @Test
    void checkSignBeforeBracketsFalse() {
        assertFalse(Calculator.checkSignBeforeBrackets("1+(2+3-)+4"));
    }
    @Test
    void checkExampleTest1() {
        String input = "1+(1+1(-1)*3)+1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("1+(1+1*(-1)*3)+1", Calculator.checkExample());
    }
    @Test
    void checkExampleTest2() {
        String input = "1+(1+1/0(-1)*3)+1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("Infinity", Calculator.checkExample());
    }*/
}