public class Checking {
    public boolean checkHasBracket(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '(')
                return true;
        }
        return false;
    }
    public boolean checkFirstSymbolMinus(String stringToCheck) {
        return stringToCheck.charAt(0) == '-';
    }
    public boolean checkHasMoreThanTwoNumbers(String stringToCheck) {
        Checking checking = new Checking();
        int firstSymbolIsMinusCorrection = checking.checkFirstSymbolMinus(stringToCheck) ? 1 : 0;
        int mathSymbolsCounter = 0;
        for (int i = firstSymbolIsMinusCorrection; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-')
                mathSymbolsCounter++;
        }
        return mathSymbolsCounter > 1;
    }
    public boolean checkSinglePositiveNumbersInBrackets(String stringToCheck) {
        boolean isItSinglePositiveNumber = false;
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '(')
                isItSinglePositiveNumber = true;
            if (isItSinglePositiveNumber && stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-' ||
                    stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '/')
                isItSinglePositiveNumber = false;
            if (stringToCheck.charAt(i) == ')' && isItSinglePositiveNumber)
                break;
        }
        return isItSinglePositiveNumber;
    }
    public boolean checkHasMoreThanSingleNumber(String stringToCheck) {
        int firstIsABracketCorrection = stringToCheck.charAt(0) == '(' ? 1 : 0;
        int firstIsAMinusCorrection =
                stringToCheck.charAt(firstIsABracketCorrection) == '-' ? 1 + firstIsABracketCorrection : firstIsABracketCorrection;
        for (int i = firstIsAMinusCorrection; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-')
                return true;
        }
        return false;
    }
    public boolean checkMultiplyOrDivideSymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '/')
                return true;
        }
        return false;
    }
    public boolean checkMathSymbolInSubstringIsFirstMathSymbol(String stringToCheck) {
        Localizing localizing = new Localizing();
        int firstIsBracketsCorrection = stringToCheck.charAt(0) == '(' ? 1 : 0;
        int firstIsMinusSymbolCorrection = stringToCheck.charAt(firstIsBracketsCorrection) == '-' ? 1 : 0;
        if (checkMultiplyOrDivideSymbol(stringToCheck)) {
            firstIsBracketsCorrection = localizing.localizeMultipleOrDivideSymbol(stringToCheck);
        } else {
            firstIsBracketsCorrection = localizing.localizePlusOrMinusSymbol(stringToCheck);
        }
        for (int i = firstIsMinusSymbolCorrection; i < firstIsBracketsCorrection; i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-') {
                return false;
            }
        }
        return true;
    }
    public boolean checkMathSymbolInSubstringIsLastMathSymbol(String stringToCheck) {
        Localizing localizing = new Localizing();
        int positionOfMathSymbol;
        if (checkMultiplyOrDivideSymbol(stringToCheck)) {
            positionOfMathSymbol = localizing.localizeMultipleOrDivideSymbol(stringToCheck);
        } else {
            positionOfMathSymbol = localizing.localizePlusOrMinusSymbol(stringToCheck);
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
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '*')
                return true;
        }
        return false;
    }
    public boolean checkHasDivideSymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/')
                return true;
        }
        return false;
    }
    public boolean checkHasPlusSymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '+')
                return true;
        }
        return false;
    }
    public boolean checkHasMinusSymbol(String stringToCheck) {
        int x = stringToCheck.charAt(0) == '-' ? 1 : 0;
        for (int i = x; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '-')
                return true;
        }
        return false;
    }
    public boolean checkInputIsEmpty(String stringToCheck) {
        return stringToCheck.length() == 0;
    }
    public boolean checkHasDivisionByZero(String stringToCheck) {
        for (int i = 1; i < stringToCheck.length()-1; i++) {
            if (stringToCheck.charAt(i) == '/' && stringToCheck.charAt(i+1) == '0') {
                if (stringToCheck.length() == i+2)
                    return true;
                if (stringToCheck.charAt(i+2) != '.')
                    return true;
            }
        }
        return false;
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
            }
        }
        return bracketsCounter == 0;
    }
    public boolean checkMathSymbolBeforeClosingBrackets(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == ')' && (stringToCheck.charAt(i-1) == '-' ||
                    stringToCheck.charAt(i-1) == '+' || stringToCheck.charAt(i-1) == '*' ||
                    stringToCheck.charAt(i-1) == '/' || stringToCheck.charAt(i-1) == '.' || stringToCheck.charAt(i-1) == '('))
                return false;
        }
        return true;
    }
    public boolean checkHasOnlyCorrectSymbols(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (!Character.isDigit(stringToCheck.charAt(i)) && stringToCheck.charAt(i) != '-' && stringToCheck.charAt(i) != '+' && stringToCheck.charAt(i) != '/'
                    && stringToCheck.charAt(i) != '*' && stringToCheck.charAt(i) != '(' && stringToCheck.charAt(i) != ')' && stringToCheck.charAt(i) != '.')
                return false;
        }
        return true;
    }
    public boolean checkMathSymbolsSequence(String stringToCheck) {
        for (int i = 1; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i-1) == '-' || stringToCheck.charAt(i-1) == '+' || stringToCheck.charAt(i-1) == '/' || stringToCheck.charAt(i-1) == '*') {
                if (stringToCheck.charAt(i) == '-' || stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*') {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkMathSymbolAfterOpenedBracket(String stringToCheck) {
        for (int i = 1; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i-1) == '(' && stringToCheck.charAt(i) != '-' &&
                    stringToCheck.charAt(i) != '(' && !Character.isDigit(stringToCheck.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public boolean checkFractionalNumbers(String stringToCheck) {
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
        return  (Character.isDigit(stringToCheck.charAt(0)) || stringToCheck.charAt(0) == '-' || stringToCheck.charAt(0) == '(') &&
                checkInputHasCorrectBrackets(stringToCheck) && checkHasOnlyCorrectSymbols(stringToCheck) &&
                checkMathSymbolsSequence(stringToCheck) && checkFractionalNumbers(stringToCheck) &&
                checkMathSymbolBeforeClosingBrackets(stringToCheck) && checkMathSymbolAfterOpenedBracket(stringToCheck) &&
                (stringToCheck.charAt(stringToCheck.length()-1) != '-' && stringToCheck.charAt(stringToCheck.length()-1) != '+' &&
                        stringToCheck.charAt(stringToCheck.length()-1) != '/' && stringToCheck.charAt(stringToCheck.length()-1) != '*' &&
                        stringToCheck.charAt(stringToCheck.length()-1) != '(' && stringToCheck.charAt(stringToCheck.length()-1) != '.');
    }
}
