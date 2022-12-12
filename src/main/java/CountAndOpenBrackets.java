public class CountAndOpenBrackets {
    public String countSubstringHasMoreThanTwoNumbers(String stringToCount) {
        Localizing localizing = new Localizing();
        String substringInBrackets = localizing.localizeClosedBrackets(stringToCount);
        String remainderAfterClosedBracket = stringToCount.substring(localizing.localizeEndPositionBrackets(stringToCount)+1);
        int substringLength = substringInBrackets.length()+1;
        substringInBrackets = substringInBrackets.substring(0, localizing.localizeStartingPositionToCount(substringInBrackets)) +
                countSubstring(localizing.localizeSubstringToCount(substringInBrackets)) +
                substringInBrackets.substring(localizing.localizeEndingPositionToCount(substringInBrackets));
        stringToCount = stringToCount.substring(0, stringToCount.length()-remainderAfterClosedBracket.length()-substringLength) +
                substringInBrackets + stringToCount.substring(stringToCount.length()-remainderAfterClosedBracket.length()-1);
        return stringToCount;
    }
    public String countSubstringHasMoreThanSingleNumber(String stringToCount) {
        Localizing localizing = new Localizing();
        String remainderAfterClosedBracket = stringToCount.substring(localizing.localizeEndPositionBrackets(stringToCount)+1);
        String substringInBrackets = localizing.localizeClosedBrackets(stringToCount);
        String countedSubstringInBrackets = countSubstring(substringInBrackets);
        stringToCount = stringToCount.substring(0, stringToCount.length()-remainderAfterClosedBracket.length()-substringInBrackets.length()-1)
                + countedSubstringInBrackets + stringToCount.substring(stringToCount.length()-remainderAfterClosedBracket.length()-1);
        return stringToCount;
    }
    public String openBracketsWithPositiveNumber(String stringToOpenBrackets) {
        Checking checking = new Checking();
        while (!checking.checkSubstringInfinity(stringToOpenBrackets) && checking.checkSinglePositiveNumbersInBrackets(stringToOpenBrackets)) {
            stringToOpenBrackets = openBracketsWithSinglePositiveNumber(stringToOpenBrackets);
        }
        return stringToOpenBrackets;
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
        Localizing localizing = new Localizing();
        int startPosition = localizing.localizeStartPositionBrackets(stringToOpenBrackets);
        int endPosition = localizing.localizeEndPositionBrackets(stringToOpenBrackets);
        if (startPosition == 0) {
            stringToOpenBrackets = stringToOpenBrackets.substring(startPosition + 1, endPosition) + stringToOpenBrackets.substring(endPosition + 1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '+') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0, startPosition-1) +
                    stringToOpenBrackets.substring(startPosition+1, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '-' && stringToOpenBrackets.charAt(startPosition-2) == '(') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition-1) +
                    stringToOpenBrackets.substring(startPosition+2, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '-') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition-1) + '+' +
                    stringToOpenBrackets.substring(startPosition+2, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '(') {
            stringToOpenBrackets = stringToOpenBrackets.substring(0,startPosition) +
                    stringToOpenBrackets.substring(startPosition+1, endPosition) + stringToOpenBrackets.substring(endPosition+1);
        } else if (stringToOpenBrackets.charAt(startPosition-1) == '*' || stringToOpenBrackets.charAt(startPosition-1) == '/') {
            stringToOpenBrackets = countSubstringWithMinusSingleNumberAfterDivideOrMultiply(stringToOpenBrackets);
        }
        return stringToOpenBrackets;
    }
    public String countSubstringWithMinusSingleNumberAfterDivideOrMultiply(String stringToOpenBrackets) {
        Localizing localizing = new Localizing();
        int startPositionIncludingBrackets = 0;
        int startPosition = localizing.localizeStartPositionBrackets(stringToOpenBrackets);
        int endPosition = localizing.localizeEndPositionBrackets(stringToOpenBrackets);
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
    public String countSubstring(String stringToCount) {
        Localizing localizing = new Localizing();
        Checking checking = new Checking();
        double firstNumber, secondNumber;
        if (checking.checkMultiplyOrDivideSymbol(stringToCount)) {
            firstNumber = Double.parseDouble(stringToCount.substring(0, localizing.localizeMultipleOrDivideSymbol(stringToCount)));
            secondNumber = Double.parseDouble(stringToCount.substring(localizing.localizeMultipleOrDivideSymbol(stringToCount) + 1));
        } else {
            firstNumber = Double.parseDouble(stringToCount.substring(0, localizing.localizePlusOrMinusSymbol(stringToCount)));
            secondNumber = Double.parseDouble(stringToCount.substring(localizing.localizePlusOrMinusSymbol(stringToCount) + 1));
        }
        if (checking.checkHasMultiplySymbol(stringToCount)) {
            firstNumber*=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (checking.checkHasDivideSymbol(stringToCount)) {
            firstNumber/=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (checking.checkHasPlusSymbol(stringToCount)) {
            firstNumber+=secondNumber;
            stringToCount = Double.toString(firstNumber);
            return stringToCount;
        } else if (checking.checkHasMinusSymbol(stringToCount)) {
            firstNumber-=secondNumber;
            stringToCount = Double.toString(firstNumber);
        }
        return stringToCount;
    }
}
