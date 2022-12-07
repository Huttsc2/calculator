import java.util.Scanner;

public  class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String stringToCount;
        stringToCount = calculator.addStringAndCheckIt();
        stringToCount = calculator.countWhileHasBrackets(stringToCount);
        stringToCount = calculator.countWithoutBrackets(stringToCount);
        System.out.println(stringToCount);
    }
    public String addStringAndCheckIt() {
        String inputString = null;
        boolean isCorrect = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your example");
        System.out.println("You can use numbers and next symbols:");
        System.out.println("'.' '/' '*' '-' '+' '(' ')'");
        while (!isCorrect) {
            inputString = sc.nextLine().replaceAll(" ", "");
            isCorrect = checkInputIsCorrect(inputString);
            if (!isCorrect) {
                System.out.println("incorrect example, try again");
                isCorrect = checkInputIsCorrect(inputString);
            }
        }
        sc.close();
        inputString = addMultiplySymbolBeforeAndAfterBrackets(inputString);
        if (checkHasDivisionByZero(inputString))
            inputString = "Infinity";
        return inputString;
    }
    public String countWhileHasBrackets(String stringToCount) {
        String remainderAfterClosedBracket;
        String substringInBrackets;
        String countedSubstringInBrackets;
        while (!checkSubstringInfinity(stringToCount) && checkHasBracket(stringToCount)) {
            substringInBrackets = localizeClosedBrackets(stringToCount);
            remainderAfterClosedBracket = stringToCount.substring(localizeEndPositionBrackets(stringToCount)+1);
            if (!checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) >= 0) {
                stringToCount = openBracketsWithPositiveNumber(stringToCount);
                continue;
            }
            if (!checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) < 0) {
                stringToCount = openBracketsWithSingleNegativeNumber(stringToCount);
                continue;
            }
            if (!checkSubstringInfinity(stringToCount) && checkHasMoreThanTwoNumbers(substringInBrackets)) {
                int substringLength = substringInBrackets.length()+1;
                substringInBrackets = substringInBrackets.substring(0, localizeStartingPositionToCount(substringInBrackets)) +
                        countSubstring(localizeSubstringToCount(substringInBrackets)) +
                        substringInBrackets.substring(localizeEndingPositionToCount(substringInBrackets));
                stringToCount = stringToCount.substring(0, stringToCount.length()-remainderAfterClosedBracket.length()-substringLength) +
                        substringInBrackets + stringToCount.substring(stringToCount.length()-remainderAfterClosedBracket.length()-1);
                continue;
            }
            if (checkHasMoreThanSingleNumber(substringInBrackets)) {
                countedSubstringInBrackets = countSubstring(substringInBrackets);
                stringToCount = stringToCount.substring(0, stringToCount.length()-remainderAfterClosedBracket.length()-substringInBrackets.length()-1)
                        + countedSubstringInBrackets + stringToCount.substring(stringToCount.length()-remainderAfterClosedBracket.length()-1);
            }
        }
        return stringToCount;
    }
    public String countWithoutBrackets(String stringToCount) {
        String substringInBrackets, solvedSubstringInBrackets;
        while (!checkSubstringInfinity(stringToCount) && checkHasMoreThanSingleNumber(stringToCount)) {
            substringInBrackets = localizeSubstringToCount(stringToCount);
            solvedSubstringInBrackets = countSubstring(substringInBrackets);
            if (checkHasMoreThanTwoNumbers(stringToCount))
                stringToCount = stringToCount.substring(0, localizeStartingPositionToCount(stringToCount)) +
                        solvedSubstringInBrackets + stringToCount.substring(localizeEndingPositionToCount(stringToCount));
            else
                 stringToCount = countSubstring(stringToCount);
        }
        if (checkSubstringInfinity(stringToCount)) {
            stringToCount = "division by zero error";
        }
        return stringToCount;
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
    public String openBracketsWithPositiveNumber(String stringToOpenBrackets) {
        while (!checkSubstringInfinity(stringToOpenBrackets) && checkPositiveNumbersInBrackets(stringToOpenBrackets)) {
            stringToOpenBrackets = openBracketsWithSinglePositiveNumber(stringToOpenBrackets);
        }
        return stringToOpenBrackets;
    }
    public boolean checkPositiveNumbersInBrackets(String stringToCheck) {
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
    public String openBracketsWithSinglePositiveNumber(String stringToOpenBrackets) {
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
    public String openBracketsWithSingleNegativeNumber(String stringToOpenBrackets) {
        int startPosition = localizeStartPositionBrackets(stringToOpenBrackets);
        int endPosition = localizeEndPositionBrackets(stringToOpenBrackets);
        if (startPosition == 0) {
            stringToOpenBrackets = stringToOpenBrackets.substring(startPosition + 1, endPosition) + stringToOpenBrackets.substring(endPosition + 1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '+') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0, startPosition-1) +
                    stringToOpenBrackets.substring(startPosition+1, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '-' && stringToOpenBrackets.charAt(startPosition-2) == '(') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition-1) +
                    stringToOpenBrackets.substring(startPosition+2, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        }else if (stringToOpenBrackets.charAt(startPosition-1) == '-') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition-1) + '+' +
                    stringToOpenBrackets.substring(startPosition+2, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '(') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition) +
                    stringToOpenBrackets.substring(startPosition+1, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        }  else if (stringToOpenBrackets.charAt(startPosition-1) == '*' ||
                stringToOpenBrackets.charAt(startPosition-1) == '/') {
            stringToOpenBrackets = countSubstringWithMinusSingleNumberAfterDivideOrMultiply(stringToOpenBrackets);
        }
        return stringToOpenBrackets;
    }
    public String countSubstringWithMinusSingleNumberAfterDivideOrMultiply(String stringToOpenBrackets) {
        int startPositionIncludingBrackets = 0;
        int startPosition = localizeStartPositionBrackets(stringToOpenBrackets);
        int endPosition = localizeEndPositionBrackets(stringToOpenBrackets);
        String substringToCount;
        for (int i = startPosition-2; i > 0; i--) {
            if (stringToOpenBrackets.charAt(i) == '+' || stringToOpenBrackets.charAt(i) == '/' ||
                    stringToOpenBrackets.charAt(i) == '*' || stringToOpenBrackets.charAt(i) == '(') {
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
        return stringToOpenBrackets;
    }
    public boolean checkHasMoreThanTwoNumbers(String stringToCheck) {
        int firstSymbolIsMinusCorrection = checkFirstSymbolMinus(stringToCheck) ? 1 : 0;
        int mathSymbolsCounter = 0;
        for (int i = firstSymbolIsMinusCorrection; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-')
                mathSymbolsCounter++;
        }
        return mathSymbolsCounter > 1;
    }
    public boolean checkHasMoreThanSingleNumber(String stringToCheck) {
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
    public boolean checkHasBracket(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '(')
                return true;
        }
        return false;
    }
    public int localizeStartPositionBrackets(String stringToLocalize) {
        int startBracketsPosition = 0;
        for (int i = 0; i < stringToLocalize.length(); i++) {
            if (stringToLocalize.charAt(i) == '('){
                startBracketsPosition = i;
            }
            if (stringToLocalize.charAt(i) == ')')
                break;
        }
        return startBracketsPosition;
    }
    public int localizeEndPositionBrackets(String stringToLocalize) {
        int endBracketsPosition = 0;
        for (int i = 0; i < stringToLocalize.length(); i++) {
            if (stringToLocalize.charAt(i) == ')') {
                endBracketsPosition = i;
                break;
            }
        }
        return endBracketsPosition;
    }
    public String localizeClosedBrackets(String stringToLocalize) {
        int startBracketsPosition = localizeStartPositionBrackets(stringToLocalize);
        int endBracketsPosition = localizeEndPositionBrackets(stringToLocalize);
        stringToLocalize = stringToLocalize.substring(startBracketsPosition+1, endBracketsPosition);
        return stringToLocalize;
    }
    public boolean checkFirstSymbolMinus(String stringToCheck) {
        return stringToCheck.charAt(0) == '-';
    }
    public boolean checkMultiplyOrDivideSymbol(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '/')
                return true;
        }
        return false;
    }
    public int localizePlusOrMinusSymbol(String stringToLocalize) {
        int firstMinusSymbolCorrection = stringToLocalize.charAt(0) == '-' ? 1 : 0;
        for (int i = firstMinusSymbolCorrection; i < stringToLocalize.length(); i++) {
            if (stringToLocalize.charAt(i) == '+' || stringToLocalize.charAt(i) == '-') {
                firstMinusSymbolCorrection = i;
                break;
            }
        }
        return firstMinusSymbolCorrection;
    }
    public int localizeMultipleOrDivideSymbol(String stringToLocalize) {
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
        stringToLocalize = stringToLocalize.substring(localizeStartingPositionToCount(stringToLocalize), localizeEndingPositionToCount(stringToLocalize));
        return stringToLocalize;
    }
    public boolean checkMathSymbolInSubstringIsFirstMathSymbol(String stringToCheck) {
        int firstIsBracketsCorrection = stringToCheck.charAt(0) == '(' ? 1 : 0;
        int firstIsMinusSymbolCorrection = stringToCheck.charAt(firstIsBracketsCorrection) == '-' ? 1 : 0;
        int mathSymbolCounter = 0;
        if (checkMultiplyOrDivideSymbol(stringToCheck)) {
            firstIsBracketsCorrection = localizeMultipleOrDivideSymbol(stringToCheck);
        } else {
            firstIsBracketsCorrection = localizePlusOrMinusSymbol(stringToCheck);
        }
        for (int i = firstIsMinusSymbolCorrection; i < firstIsBracketsCorrection; i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-') {
                mathSymbolCounter++;
            }
        }
        return mathSymbolCounter == 0;
    }
    public boolean checkMathSymbolInSubstringIsLastMathSymbol(String stringToCheck) {
        int positionOfMathSymbol;
        int mathSymbolCounter = 0;
        if (checkMultiplyOrDivideSymbol(stringToCheck)) {
            positionOfMathSymbol = localizeMultipleOrDivideSymbol(stringToCheck);
        } else {
            positionOfMathSymbol = localizePlusOrMinusSymbol(stringToCheck);
        }
        for (int i = positionOfMathSymbol+1; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '/' || stringToCheck.charAt(i) == '*' ||
                    stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '-') {
                mathSymbolCounter++;
            }
        }
        return mathSymbolCounter == 0;
    }
    public int localizeStartingPositionToCount(String stringToLocalize) {
        int startingPosition;
        if (checkMultiplyOrDivideSymbol(stringToLocalize)) {
            startingPosition = localizeMultipleOrDivideSymbol(stringToLocalize);
        } else {
            startingPosition = localizePlusOrMinusSymbol(stringToLocalize);
        }
        if (checkMathSymbolInSubstringIsFirstMathSymbol(stringToLocalize)) {
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
    public int localizeEndingPositionToCount(String stringToLocalize) {
        int endingPosition;
        if (checkMultiplyOrDivideSymbol(stringToLocalize)) {
            endingPosition = localizeMultipleOrDivideSymbol(stringToLocalize);
        } else {
            endingPosition = localizePlusOrMinusSymbol(stringToLocalize);
        }
        if (checkMathSymbolInSubstringIsLastMathSymbol(stringToLocalize)) {
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
    public String countSubstring(String stringToCount) {
        double firstNumber, secondNumber;
        if (checkMultiplyOrDivideSymbol(stringToCount)) {
            firstNumber = Double.parseDouble(stringToCount.substring(0, localizeMultipleOrDivideSymbol(stringToCount)));
            secondNumber = Double.parseDouble(stringToCount.substring(localizeMultipleOrDivideSymbol(stringToCount) + 1));
        } else {
            firstNumber = Double.parseDouble(stringToCount.substring(0, localizePlusOrMinusSymbol(stringToCount)));
            secondNumber = Double.parseDouble(stringToCount.substring(localizePlusOrMinusSymbol(stringToCount) + 1));
        }
        if (checkHasMultiplySymbol(stringToCount)) {
            firstNumber*=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (checkHasDivideSymbol(stringToCount)) {
            firstNumber/=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (checkHasPlusSymbol(stringToCount)) {
            firstNumber+=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (checkHasMinusSymbol(stringToCount)) {
            firstNumber-=secondNumber;
            stringToCount = Double.toString(firstNumber);
        }
        return stringToCount;
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
        int symbolsCounter = 0;
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (Character.isDigit(stringToCheck.charAt(i)) || stringToCheck.charAt(i) == '-' || stringToCheck.charAt(i) == '+' || stringToCheck.charAt(i) == '/'
                    || stringToCheck.charAt(i) == '*' || stringToCheck.charAt(i) == '(' || stringToCheck.charAt(i) == ')' || stringToCheck.charAt(i) == '.')
                symbolsCounter++;
        }
        return symbolsCounter == stringToCheck.length();
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
            if (stringToCheck.charAt(i-1) == '(' && stringToCheck.charAt(i) != '-' && !Character.isDigit(stringToCheck.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public String addMultiplySymbolBeforeAndAfterBrackets(String stringToAdd) {
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
    public boolean checkFractionalNumbers(String stringToCheck) {
        stringToCheck = stringToCheck.replaceAll("[-+*()/]", " ").replaceAll("\\s{2,}", " ");
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
    public boolean checkSubstringInfinity(String stringToCheck) {
        String con = "Infinity";
        return (stringToCheck.contains(con));
    }
}