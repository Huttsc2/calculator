import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checking {
    public boolean checkHasBracket(String stringToCheck) {
        return stringToCheck.contains("(");
    }
    public boolean checkHasMoreThanTwoNumbers(String stringToCheck) {
        Pattern pattern = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher matcher = pattern.matcher(stringToCheck);
        int numberCounter = 0;
        while (matcher.find()) {
            numberCounter++;
        }
        return numberCounter>2;
    }
    public boolean checkSinglePositiveNumbersInBrackets(String stringToCheck) {
        Pattern pattern = Pattern.compile("\\(\\d+\\.\\d+\\)|\\(\\d+\\)");
        Matcher matcher = pattern.matcher(stringToCheck);
        return matcher.find();
    }
    public boolean checkHasMoreThanSingleNumber(String stringToCheck) {
        Pattern pattern = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher matcher = pattern.matcher(stringToCheck);
        int numberCounter = 0;
        while (matcher.find()) {
            numberCounter++;
        }
        return numberCounter>1;
    }

    //return here later
    public boolean checkMathSymbolInSubstringIsFirstMathSymbol(String stringToCheck) {
        PositionSearch positionSearch = new PositionSearch();
        int firstIsBracketsCorrection = stringToCheck.charAt(0) == '(' ? 1 : 0;
        int firstIsMinusSymbolCorrection = stringToCheck.charAt(firstIsBracketsCorrection) == '-' ? 1 : 0;
        if (checkHasMultiplySymbol(stringToCheck)) {
            firstIsBracketsCorrection = stringToCheck.indexOf('*');
        } else if (checkHasDivideSymbol(stringToCheck)) {
            firstIsBracketsCorrection = stringToCheck.indexOf('/');
        } else {
            firstIsBracketsCorrection = positionSearch.searchPlusSymbolPosition(stringToCheck);
        }
        for (int i = firstIsMinusSymbolCorrection; i < firstIsBracketsCorrection; i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-') {
                return false;
            }
        }
        return true;
    }

    //return here later
    public boolean checkMathSymbolInSubstringIsLastMathSymbol(String stringToCheck) {
        PositionSearch positionSearch = new PositionSearch();
        int positionOfMathSymbol;
        if (checkHasMultiplySymbol(stringToCheck)) {
            positionOfMathSymbol = stringToCheck.indexOf('*');
        } else if (checkHasDivideSymbol(stringToCheck)) {
            positionOfMathSymbol = stringToCheck.indexOf('/');
        } else {
            positionOfMathSymbol = positionSearch.searchPlusSymbolPosition(stringToCheck);
        }
        for (int i = positionOfMathSymbol+1; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-') {
                return false;
            }
        }
        return true;
    }
    public boolean checkHasMultiplySymbol(String stringToCheck) {
        return stringToCheck.contains("*");
    }
    public boolean checkHasDivideSymbol(String stringToCheck) {
        return stringToCheck.contains("/");
    }
    public boolean checkHasPlusSymbol(String stringToCheck) {
        return stringToCheck.contains("+");
    }
    public boolean checkHasMinusSymbol(String stringToCheck) {
        if (stringToCheck.charAt(0) == '-')
            stringToCheck = stringToCheck.substring(1);
        return stringToCheck.contains("-");
    }
    public boolean checkInputIsEmpty(String stringToCheck) {
        return stringToCheck.length() == 0;
    }
    public boolean checkHasDivisionByZero(String stringToCheck) {
        Pattern pattern = Pattern.compile("/0[^\\d.]|/0$");
        Matcher matcher = pattern.matcher(stringToCheck);
        return matcher.find();
    }
    public boolean checkInputHasCorrectBrackets(String stringToCheck) {
        int bracketsCounter = 0;
        boolean isFirstRightBracket = false;
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '(') {
                isFirstRightBracket = true;
                bracketsCounter++;
            } else if (stringToCheck.charAt(i) == ')' && isFirstRightBracket) {
                bracketsCounter--;
            } else if (stringToCheck.charAt(i) == ')') {
                return false;
            }
        }
        return bracketsCounter == 0;
    }
    public boolean checkMathSymbolBeforeClosingBrackets(String stringToCheck) {
        Pattern pattern = Pattern.compile("[^)+-/*\\d]\\)");
        Matcher matcher = pattern.matcher(stringToCheck);
        return matcher.find();
    }
    public boolean checkHasIncorrectSymbols(String stringToCheck) {
        Pattern pattern = Pattern.compile("[^\\d+\\-*/.()]");
        Matcher matcher = pattern.matcher(stringToCheck);
        return matcher.find();
    }
    public boolean checkMathSymbolsSequence(String stringToCheck) {
        Pattern pattern = Pattern.compile("[-+/*][-+/*]");
        Matcher matcher = pattern.matcher(stringToCheck);
        return matcher.find();
    }
    public boolean checkMathSymbolAfterOpenedBracket(String stringToCheck) {
        Pattern pattern = Pattern.compile("\\([^\\d-(]");
        Matcher matcher = pattern.matcher(stringToCheck);
        return matcher.find();
    }
    public boolean oldCheckFractionalNumbers(String stringToCheck) {
        stringToCheck = stringToCheck.replaceAll("[-+*()/]", " ").replaceAll("\\s{2,}", " ");
        String[] numbersInString = stringToCheck.split(" ");
        int dotsCounter = 0;
        for (String value : numbersInString) {
            for (int j = 0; j < value.length(); j++) {
                if (value.charAt(0) == '.')
                    return false;
                if (value.charAt(j) == '.') {
                    dotsCounter++;
                }
                if (dotsCounter > 1)
                    return false;
            }
            dotsCounter = 0;
        }
        return true;
    }
    public boolean checkSubstringInfinity(String stringToCheck) {
        String con = "Infinity";
        return (stringToCheck.contains(con));
    }
    public boolean checkInputIsCorrect(String stringToCheck) {
        if (checkInputIsEmpty(stringToCheck))
            return false;
        Pattern pattern = Pattern.compile("[^\\d)]$");
        Matcher matcher = pattern.matcher(stringToCheck);
        if (matcher.find())
            return false;
        return  (Character.isDigit(stringToCheck.charAt(0)) || stringToCheck.charAt(0) == '-' || stringToCheck.charAt(0) == '(') &&
                checkInputHasCorrectBrackets(stringToCheck) && !checkHasIncorrectSymbols(stringToCheck) &&
                !checkMathSymbolsSequence(stringToCheck) && oldCheckFractionalNumbers(stringToCheck) &&
                !checkMathSymbolBeforeClosingBrackets(stringToCheck) && !checkMathSymbolAfterOpenedBracket(stringToCheck);
    }
}