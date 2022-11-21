import java.util.Scanner;

public  class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String stringToCount;
        stringToCount = calculator.inputStringAndCheckIt();
        stringToCount = calculator.countStringWhileItHasBrackets(stringToCount);
        stringToCount = calculator.countStringWithoutBrackets(stringToCount);
        System.out.println(stringToCount);
    }
    public String inputStringAndCheckIt() {
        String inputString = null;
        boolean isCorrect = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your example");
        System.out.println("You can use numbers and next symbols:");
        System.out.println("'.' '/' '*' '-' '+' '(' ')'");
        while (!isCorrect) {
            inputString = sc.nextLine().replaceAll(" ", "");
            isCorrect = inputStringIsCorrect(inputString);
            if (!isCorrect) {
                System.out.println("incorrect example, try again");
                isCorrect = inputStringIsCorrect(inputString);
            }
        }
        sc.close();
        inputString = addMultiplySymbolBeforeAndAfterBracketsIfInputStringDoesNotHaveAnyMathSymbolThere(inputString);
        if (stringIncludeDivisionByZero(inputString))
            inputString = "Infinity";
        return inputString;
    }
    public String countStringWhileItHasBrackets(String stringToCount) {
        String remainderAfterClosedBracket;
        String substringInBrackets;
        String solvedSubstringInBrackets;
        while (!stringHasSubstringInfinity(stringToCount) &&
                (stringHasBracketsWithNoSingleNumber(stringToCount) || stringHasABracket(stringToCount))) {
            substringInBrackets = firstClosedBracketsInString(stringToCount);
            remainderAfterClosedBracket = stringToCount.substring(localizeEndPositionOfFirstClosedBracketsInString(stringToCount) + 1);
            if (!stringHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) >= 0) {
                stringToCount = openBracketsWithAPositiveNumber(stringToCount);
                continue;
            }
            if (!stringHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) < 0) {
                stringToCount = openBracketsWithASingleNegativeNumber(stringToCount);
                continue;
            }
            if (!stringHasSubstringInfinity(stringToCount) && stringHasMoreThanTwoNumbers(substringInBrackets)) {
                int substringLength = substringInBrackets.length()+1;
                substringInBrackets = substringInBrackets.substring(0, startingPositionToCount(substringInBrackets)) +
                        countSubstring(localizeSubstringToCount(substringInBrackets)) +
                        substringInBrackets.substring(endingPositionToCount(substringInBrackets));
                stringToCount = stringToCount.substring(0, stringToCount.length()-remainderAfterClosedBracket.length()-substringLength) +
                        substringInBrackets + stringToCount.substring(stringToCount.length()-remainderAfterClosedBracket.length()-1);
                continue;
            }
            if (stringHasMoreThanSingleNumber(substringInBrackets)) {
                solvedSubstringInBrackets = countSubstring(substringInBrackets);
                stringToCount = stringToCount.substring(0, stringToCount.length()-remainderAfterClosedBracket.length()-substringInBrackets.length()-1)
                        + solvedSubstringInBrackets + stringToCount.substring(stringToCount.length()-remainderAfterClosedBracket.length()-1);
            }
        }
        return stringToCount;
    }
    public String countStringWithoutBrackets(String stringToCount) {
        String substringInBrackets, solvedSubstringInBrackets;
        while (!stringHasSubstringInfinity(stringToCount) && stringHasMoreThanSingleNumber(stringToCount)) {
            substringInBrackets = localizeSubstringToCount(stringToCount);
            solvedSubstringInBrackets = countSubstring(substringInBrackets);
            if (stringHasMoreThanTwoNumbers(stringToCount))
                stringToCount = stringToCount.substring(0, startingPositionToCount(stringToCount)) +
                        solvedSubstringInBrackets + stringToCount.substring(endingPositionToCount(stringToCount));
            else
                 stringToCount = countSubstring(stringToCount);
        }
        if (stringHasSubstringInfinity(stringToCount)) {
            stringToCount = "division by zero error";
        }
        return stringToCount;
    }
    public String openBracketsWithAPositiveNumber(String stringToOpenBrackets) {
        while (!stringHasSubstringInfinity(stringToOpenBrackets) && stringHasPositiveNumbersInBrackets(stringToOpenBrackets)) {
            stringToOpenBrackets = openBracketsWithASinglePositiveNumber(stringToOpenBrackets);
        }
        return stringToOpenBrackets;
    }
    public boolean stringHasPositiveNumbersInBrackets(String stringToCheck) {
        boolean isItSingle = false;
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '(')
                isItSingle = true;
            if (isItSingle && stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-' ||
                    stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '/')
                isItSingle = false;
            if (stringToCheck.charAt(i) == ')' && isItSingle)
                break;
        }
        return isItSingle;
    }
    public String openBracketsWithASinglePositiveNumber(String stringToOpenBrackets) {
        int startPositionToOpenBrackets = 0, endPositionToOpenBrackets = 0;
        boolean isPlusNumber = false;
        while (!isPlusNumber) {
            for (int i = endPositionToOpenBrackets; i < stringToOpenBrackets.length(); i++) {
                if (stringToOpenBrackets.charAt(i) == '(')
                    startPositionToOpenBrackets = i;
                if (stringToOpenBrackets.charAt(i) == ')') {
                    endPositionToOpenBrackets = i;
                    break;
                }
            }
            isPlusNumber = true;
            for (int i = startPositionToOpenBrackets; i < endPositionToOpenBrackets; i++) {
                if (stringToOpenBrackets.charAt(i) == '-' || stringToOpenBrackets.charAt(i) == '+' ||
                        stringToOpenBrackets.charAt(i) == '/' || stringToOpenBrackets.charAt(i) == '*' ) {
                    isPlusNumber = false;
                    endPositionToOpenBrackets+=1;
                    break;
                }
            }
        }
        stringToOpenBrackets = stringToOpenBrackets.substring(0, startPositionToOpenBrackets) +
                stringToOpenBrackets.substring(startPositionToOpenBrackets+1, endPositionToOpenBrackets) +
                stringToOpenBrackets.substring(endPositionToOpenBrackets+1);
        return stringToOpenBrackets;
    }
    public String openBracketsWithASingleNegativeNumber(String stringToOpenBrackets) {
        int startPosition = localizeStartPositionOfFirstClosedBracketsInString(stringToOpenBrackets);
        int endPosition = localizeEndPositionOfFirstClosedBracketsInString(stringToOpenBrackets);
        int stringLengthCorrection = endPosition+1 == stringToOpenBrackets.length() ? 0 : 1;
        int startPositionCorrection = startPosition == 0 ? 0 : 1;
        String substringToCount;
        if (stringToOpenBrackets.charAt(startPosition-startPositionCorrection) == '+' &&
                stringToOpenBrackets.charAt(endPosition+stringLengthCorrection) != '*' &&
                stringToOpenBrackets.charAt(endPosition+stringLengthCorrection) != '/') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0, startPosition-1) +
                    stringToOpenBrackets.substring(startPosition+1, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-startPositionCorrection) == '-'
                && stringToOpenBrackets.charAt(startPosition-startPositionCorrection-startPositionCorrection)
                != '(' && stringToOpenBrackets.charAt(endPosition+stringLengthCorrection) != '*' &&
                stringToOpenBrackets.charAt(endPosition+stringLengthCorrection) != '/') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition-startPositionCorrection) + '+' +
                    stringToOpenBrackets.substring(startPosition+2, endPosition) + stringToOpenBrackets.substring(endPosition+stringLengthCorrection);
        } else if (stringToOpenBrackets.charAt(startPosition-startPositionCorrection) == '(') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition) +
                    stringToOpenBrackets.substring(startPosition+1, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-startPositionCorrection) == '-' && stringToOpenBrackets.charAt(startPosition-2) == '(') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition-startPositionCorrection) +
                    stringToOpenBrackets.substring(startPosition+2, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-startPositionCorrection) == '*' ||
                stringToOpenBrackets.charAt(startPosition-startPositionCorrection) == '/') {
            int startPositionIncludingBrackets = 0;
            for (int i = startPosition-2; i > 0; i--) {
                if (stringToOpenBrackets.charAt(i) == '+' || stringToOpenBrackets.charAt(i) == '/' || stringToOpenBrackets.charAt(i) == '*') {
                    startPositionIncludingBrackets = i+1;
                    break;
                } else if (stringToOpenBrackets.charAt(i) == '-' &&  Character.isDigit(stringToOpenBrackets.charAt(i-1))){
                    startPositionIncludingBrackets = i+1;
                    break;
                } else if (stringToOpenBrackets.charAt(i) == '-') {
                    startPositionIncludingBrackets = i;
                    break;
                }
            }
            substringToCount = stringToOpenBrackets.substring(startPositionIncludingBrackets, endPosition).replaceAll("[)(]", "");
            substringToCount = countSubstring(substringToCount);
            stringToOpenBrackets = stringToOpenBrackets.substring(0, startPositionIncludingBrackets) +
                    '(' + substringToCount + stringToOpenBrackets.substring(endPosition);
        } else if ((stringToOpenBrackets.charAt(endPosition+stringLengthCorrection) == '*' ||
                stringToOpenBrackets.charAt(endPosition+stringLengthCorrection) == '/') &&
                stringToOpenBrackets.charAt(endPosition+stringLengthCorrection+stringLengthCorrection) != '(') {
            int endPositionIncludingBrackets = endPosition + 3;
            for (int i = endPosition + 2; i < stringToOpenBrackets.length(); i++) {
                if (stringToOpenBrackets.charAt(i) == '+' || stringToOpenBrackets.charAt(i) == '-' || stringToOpenBrackets.charAt(i) == '/' ||
                        stringToOpenBrackets.charAt(i) == '*' || stringToOpenBrackets.charAt(i) == ')' ) {
                    endPositionIncludingBrackets = i;
                    break;
                }
            }
            for (int i = endPosition; i > 0; i--) {
                if (stringToOpenBrackets.charAt(i) == '(') {
                    endPosition = i + 1;
                    break;
                }
            }
            substringToCount = stringToOpenBrackets.substring(endPosition, endPositionIncludingBrackets).replaceAll("[)(]", "");
            substringToCount = countSubstring(substringToCount);
            stringToOpenBrackets = stringToOpenBrackets.substring(0, startPosition + 1) + substringToCount + ')' +
                    stringToOpenBrackets.substring(endPositionIncludingBrackets);
        } else if (stringToOpenBrackets.charAt(endPosition+stringLengthCorrection) == '*' &&
                stringToOpenBrackets.charAt(endPosition+stringLengthCorrection+stringLengthCorrection) == '(') {
            for (int i = endPosition+4; i < stringToOpenBrackets.length(); i++) {
                if (!Character.isDigit(stringToOpenBrackets.charAt(i)) && stringToOpenBrackets.charAt(i) != '.') {
                    endPosition = i;
                    break;
                }
            }
            substringToCount = stringToOpenBrackets.substring(startPosition, endPosition+1).replaceAll("[)(]", "");
            substringToCount = countSubstring(substringToCount);
            stringToOpenBrackets = stringToOpenBrackets.substring(0, startPosition) + '(' + substringToCount + stringToOpenBrackets.substring(endPosition);
        }
        return stringToOpenBrackets;
    }
    public boolean stringHasMoreThanTwoNumbers(String stringToCheck) {
        int firstSymbolIsMinusCorrection = firstSymbolInStringIsMinus(stringToCheck) ? 1 : 0;
        int mathSymbolsCounter = 0;
        for (int i = firstSymbolIsMinusCorrection; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-')
                mathSymbolsCounter++;
        }
        return mathSymbolsCounter > 1;
    }
    public boolean stringHasBracketsWithNoSingleNumber(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length()-1; i++) {
            int firstSymbolInBracketIsMinusCorrection = stringToCheck.charAt(i+1) == '-' ? 2 : 1;
            if(stringToCheck.charAt(i) == '(') {
                for (int j = i+firstSymbolInBracketIsMinusCorrection; stringToCheck.charAt(j) != ')'; j++) {
                    if (stringToCheck.charAt(j) == '-' || stringToCheck.charAt(j) == '+' ||
                            stringToCheck.charAt(j) == '*' || stringToCheck.charAt(j) == '/')
                        return true;
                }
            }
        }
        return false;
    }
    public boolean stringHasMoreThanSingleNumber(String stringToCheck) {
        int firstIsABracketCorrection = stringToCheck.charAt(0) == '(' ? 1 : 0;
        int firstIsAMinusCorrection =
                stringToCheck.charAt(firstIsABracketCorrection) == '-' ? 1 + firstIsABracketCorrection : firstIsABracketCorrection;
        int mathSymbolsCounter = 0;
        for (int i = firstIsAMinusCorrection; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-')
                mathSymbolsCounter++;
        }
        return mathSymbolsCounter != 0;
    }
    public boolean stringHasABracket(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '(')
                return true;
        }
        return false;
    }

    public int localizeEndPositionOfFirstClosedBracketsInString(String stringToLocolize) {
        int endBracketsPosition = 0;
        for (int i = 0; i < stringToLocolize.length(); i++) {
            if (stringToLocolize.charAt(i) == ')') {
                endBracketsPosition = i;
                break;
            }
        }
        return endBracketsPosition;
    }
    public int localizeStartPositionOfFirstClosedBracketsInString(String stringToLocolize) {
        int startBracketsPosition = 0;
        for (int i = 0; i < stringToLocolize.length(); i++) {
            if (stringToLocolize.charAt(i) == '('){
                startBracketsPosition = i;
            }
            if (stringToLocolize.charAt(i) == ')')
                break;
        }
        return startBracketsPosition;
    }
    public String firstClosedBracketsInString(String locolizedString) {
        int startBracketsPosition = localizeStartPositionOfFirstClosedBracketsInString(locolizedString);
        int endBracketsPosition = localizeEndPositionOfFirstClosedBracketsInString(locolizedString);
        locolizedString = locolizedString.substring(startBracketsPosition+1, endBracketsPosition);
        return locolizedString;
    }
    public boolean firstSymbolInStringIsMinus(String stringToCheck) {
        return stringToCheck.charAt(0) == '-';
    }
    public boolean stringHasMultiplyOrDivideSymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '/')
                return true;
        }
        return false;
    }
    public int localizePlusOrMinusSymbolInString(String stringToLocalize) {
        int firstMinusSymbolCorrection = stringToLocalize.charAt(0) == '-' ? 1 : 0;
        for (int i = firstMinusSymbolCorrection; i < stringToLocalize.length(); i++) {
            if (stringToLocalize.charAt(i) == '+' || stringToLocalize.charAt(i) == '-') {
                firstMinusSymbolCorrection = i;
                break;
            }
        }
        return firstMinusSymbolCorrection;
    }
    public int localizeMultipleOrDivideSymbolInString(String stringToLocalize) {
        int positionOfMultiplySymbol = 0;
        for (int i = 0; i < stringToLocalize.length(); i++) {
            if (stringToLocalize.charAt(i) == '*' || stringToLocalize.charAt(i) == '/') {
                positionOfMultiplySymbol = i;
                break;
            }
        }
        return positionOfMultiplySymbol;
    }
    public String localizeSubstringToCount(String stringToLocalize) {
        stringToLocalize = stringToLocalize.substring(startingPositionToCount(stringToLocalize), endingPositionToCount(stringToLocalize));
        return stringToLocalize;
    }
    public boolean mathSymbolInSubstringIsFirstMathSymbolInString(String stringToCheck) {
        int firstIsBracketsCorrection = stringToCheck.charAt(0) == '(' ? 1 : 0;
        int firstIsMinusSymbolCorrection = stringToCheck.charAt(firstIsBracketsCorrection) == '-' ? 1 : 0;
        int mathSymbolCounter = 0;
        if (stringHasMultiplyOrDivideSymbol(stringToCheck)) {
            firstIsBracketsCorrection = localizeMultipleOrDivideSymbolInString(stringToCheck);
        } else {
            firstIsBracketsCorrection = localizePlusOrMinusSymbolInString(stringToCheck);
        }
        for (int i = firstIsMinusSymbolCorrection; i < firstIsBracketsCorrection; i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-') {
                mathSymbolCounter++;
            }
        }
        return mathSymbolCounter == 0;
    }
    public boolean mathSymbolInSubstringIsLastMathSymbolInString(String stringToCheck) {
        int positionOfMathSymbol;
        int mathSymbolCounter = 0;
        if (stringHasMultiplyOrDivideSymbol(stringToCheck)) {
            positionOfMathSymbol = localizeMultipleOrDivideSymbolInString(stringToCheck);
        } else {
            positionOfMathSymbol = localizePlusOrMinusSymbolInString(stringToCheck);
        }
        for (int i = positionOfMathSymbol+1; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-') {
                mathSymbolCounter++;
            }
        }
        return mathSymbolCounter == 0;
    }
    public int startingPositionToCount(String stringToLocalize) {
        int startingPosition;
        if (stringHasMultiplyOrDivideSymbol(stringToLocalize)) {
            startingPosition = localizeMultipleOrDivideSymbolInString(stringToLocalize);
        } else {
            startingPosition = localizePlusOrMinusSymbolInString(stringToLocalize);
        }
        if (mathSymbolInSubstringIsFirstMathSymbolInString(stringToLocalize)) {
            startingPosition = 0;
            return startingPosition;
        }
        for (int i = startingPosition; i > 1; i--) {
            if (!Character.isDigit(stringToLocalize.charAt(i-1)) && stringToLocalize.charAt(i-1) != '.') {
                startingPosition = i;
                break;
            }
        }
        return startingPosition;
    }
    public int endingPositionToCount(String stringToLocalize) {
        int endingPosition;
        if (stringHasMultiplyOrDivideSymbol(stringToLocalize)) {
            endingPosition = localizeMultipleOrDivideSymbolInString(stringToLocalize);
        } else {
            endingPosition = localizePlusOrMinusSymbolInString(stringToLocalize);
        }
        if (mathSymbolInSubstringIsLastMathSymbolInString(stringToLocalize)) {
            endingPosition = stringToLocalize.length();
            return endingPosition;
        }
        for (int i = endingPosition+1; i < stringToLocalize.length(); i++) {
            endingPosition = i;
            if (!Character.isDigit(stringToLocalize.charAt(i)) && stringToLocalize.charAt(i) != '.')
                break;
        }
        return endingPosition;
    }
    public boolean stringHasMultiplySymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '*')
                return true;
        }
        return false;
    }
    public boolean stringHasDivideSymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/')
                return true;
        }
        return false;
    }
    public boolean stringHasPlusSymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '+')
                return true;
        }
        return false;
    }
    public boolean stringHasMinusSymbol(String stringToCheck) {
        int x = stringToCheck.charAt(0) == '-' ? 1 : 0;
        for (int i = x; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '-')
                return true;
        }
        return false;
    }
    public String countSubstring(String stringToCount) {
        double firstNumber, secondNumber;
        if (stringHasMultiplyOrDivideSymbol(stringToCount)) {
            firstNumber = Double.parseDouble(stringToCount.substring(0, localizeMultipleOrDivideSymbolInString(stringToCount)));
            secondNumber = Double.parseDouble(stringToCount.substring(localizeMultipleOrDivideSymbolInString(stringToCount) + 1));
        } else {
            firstNumber = Double.parseDouble(stringToCount.substring(0, localizePlusOrMinusSymbolInString(stringToCount)));
            secondNumber = Double.parseDouble(stringToCount.substring(localizePlusOrMinusSymbolInString(stringToCount) + 1));
        }
        if (stringHasMultiplySymbol(stringToCount)) {
            firstNumber*=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (stringHasDivideSymbol(stringToCount)) {
            firstNumber/=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (stringHasPlusSymbol(stringToCount)) {
            firstNumber+=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (stringHasMinusSymbol(stringToCount)) {
            firstNumber-=secondNumber;
            stringToCount = Double.toString(firstNumber);
        }
        return stringToCount;
    }
    public boolean inputStringIsCorrect(String stringToCheck) {
        if (inputStringIsEmpty(stringToCheck))
            return false;
        if (stringToCheck.charAt(stringToCheck.length()-1) == '-' || stringToCheck.charAt(stringToCheck.length()-1) == '+' ||
                stringToCheck.charAt(stringToCheck.length()-1) == '/' || stringToCheck.charAt(stringToCheck.length()-1) == '*' ||
                stringToCheck.charAt(stringToCheck.length()-1) == '(' || stringToCheck.charAt(stringToCheck.length()-1) == '.')
            return false;
        return  (Character.isDigit(stringToCheck.charAt(0)) || stringToCheck.charAt(0) == '-' || stringToCheck.charAt(0) == '(') &&
                inputStringHasCorrectBrackets(stringToCheck) && inputStringHasOnlyCorrectSymbols(stringToCheck) &&
                inputStringDoesNotHaveMathSymbolsInARow(stringToCheck) && inputStringHasCorrectFractionalNumbers(stringToCheck) &&
                inputStringDoesNotHaveMathSymbolBeforeClosingBrackets(stringToCheck);
    }
    public boolean inputStringIsEmpty(String stringToCheck) {
        return stringToCheck.length() == 0;
    }
    public boolean stringIncludeDivisionByZero(String stringToCheck) {
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
    public boolean inputStringHasCorrectBrackets(String stringToCheck) {
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
    public boolean inputStringDoesNotHaveMathSymbolBeforeClosingBrackets(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == ')' && (stringToCheck.charAt(i-1) == '-' ||
                    stringToCheck.charAt(i-1) == '+' || stringToCheck.charAt(i-1) == '*' ||
                    stringToCheck.charAt(i-1) == '/' || stringToCheck.charAt(i-1) == '.' || stringToCheck.charAt(i-1) == '('))
                return false;
        }
        return true;
    }
    public boolean inputStringHasOnlyCorrectSymbols(String stringToCheck) {
        int symbolsCounter = 0;
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (Character.isDigit(stringToCheck.charAt(i)) || stringToCheck.charAt(i) == '-' || stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '/'
                    || stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '(' || stringToCheck.charAt(i) == ')' || stringToCheck.charAt(i) == '.')
                symbolsCounter++;
        }
        return symbolsCounter == stringToCheck.length();
    }
    public boolean inputStringDoesNotHaveMathSymbolsInARow(String stringToCheck) {
        for (int i = 1; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i-1) == '-' || stringToCheck.charAt(i-1) == '+' || stringToCheck.charAt(i-1) == '/' || stringToCheck.charAt(i-1) == '*') {
                if (stringToCheck.charAt(i) == '-' || stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*') {
                    return false;
                }
            }
        }
        return true;
    }
    public String addMultiplySymbolBeforeAndAfterBracketsIfInputStringDoesNotHaveAnyMathSymbolThere(String stringToAdd) {
        StringBuilder ns = new StringBuilder(stringToAdd);
        for (int i = 1; i < stringToAdd.length(); i++) {
            if (stringToAdd.charAt(i) == '(' && (Character.isDigit(stringToAdd.charAt(i-1)) || stringToAdd.charAt(i-1) == ')')) {
                ns.insert(i, '*');
                stringToAdd = ns.toString();
                i = 0;
            }
        }
        for (int i = 0; i < stringToAdd.length()-1; i++)
            if (stringToAdd.charAt(i) == ')' && (Character.isDigit(stringToAdd.charAt(i+1)) || stringToAdd.charAt(i+1) == '(')) {
                ns.insert(i + 1 , '*');
                stringToAdd = ns.toString();
                i = 0;
            }
        stringToAdd = ns.toString();
        return stringToAdd;
    }
    public boolean inputStringHasCorrectFractionalNumbers(String stringToCheck) {
        stringToCheck = stringToCheck.replaceAll("[-+*()/]", " ").replaceAll("[\\s]{2,}", " ");
        String[] d = stringToCheck.split(" ");
        int x = 0;
        for (String value : d) {
            for (int j = 0; j < value.length(); j++) {
                if (value.charAt(0) == '.')
                    return false;
                if (value.charAt(j) == '.') {
                    x++;
                }
                if (x > 1)
                    return false;
            }
            x = 0;
        }
        return true;
    }
    public boolean stringHasSubstringInfinity(String stringToCheck) {
        String con = "Infinity";
        return (stringToCheck.contains(con));
    }
}