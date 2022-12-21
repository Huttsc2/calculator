public class CalculateAndOpenBrackets {
    public String calculateSubstringHasMoreThanTwoNumbers(String stringToCalculate) {
        PositionSearch positionSearch = new PositionSearch();
        String substringInBrackets = positionSearch.searchClosedBrackets(stringToCalculate);
        String remainderAfterClosedBracket = stringToCalculate.substring(positionSearch.searchEndPositionBrackets(stringToCalculate)+1);
        int substringLength = substringInBrackets.length()+1;
        substringInBrackets = substringInBrackets.substring(0, positionSearch.searchStartingPositionForCalculations(substringInBrackets)) +
                calculatedSubstring(positionSearch.searchSubstringForCalculations(substringInBrackets)) +
                substringInBrackets.substring(positionSearch.searchEndPositionForCalculations(substringInBrackets));
        stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length()-remainderAfterClosedBracket.length()-substringLength) +
                substringInBrackets + stringToCalculate.substring(stringToCalculate.length()-remainderAfterClosedBracket.length()-1);
        return stringToCalculate;
    }
    public String calculateSubstringHasMoreThanSingleNumber(String stringToCalculate) {
        PositionSearch positionSearch = new PositionSearch();
        String remainderAfterClosedBracket = stringToCalculate.substring(positionSearch.searchEndPositionBrackets(stringToCalculate)+1);
        String substringInBrackets = positionSearch.searchClosedBrackets(stringToCalculate);
        String calculatedSubstringInBrackets = calculatedSubstring(substringInBrackets);
        stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length()-remainderAfterClosedBracket.length()-substringInBrackets.length()-1)
                + calculatedSubstringInBrackets + stringToCalculate.substring(stringToCalculate.length()-remainderAfterClosedBracket.length()-1);
        return stringToCalculate;
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
        PositionSearch positionSearch = new PositionSearch();
        int startPosition = positionSearch.searchStartPositionClosedBrackets(stringToOpenBrackets);
        int endPosition = positionSearch.searchEndPositionBrackets(stringToOpenBrackets);
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
            stringToOpenBrackets = calculateSubstringWithMinusSingleNumberAfterDivideOrMultiply(stringToOpenBrackets);
        }
        return stringToOpenBrackets;
    }
    public String calculateSubstringWithMinusSingleNumberAfterDivideOrMultiply(String stringToOpenBrackets) {
        PositionSearch positionSearch = new PositionSearch();
        int startPositionIncludingBrackets = 0;
        int startPosition = positionSearch.searchStartPositionClosedBrackets(stringToOpenBrackets);
        int endPosition = positionSearch.searchEndPositionBrackets(stringToOpenBrackets);
        String substringToCalculate;
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
        substringToCalculate = stringToOpenBrackets.substring(startPositionIncludingBrackets, endPosition).replaceAll("[)(]", "");
        substringToCalculate = calculatedSubstring(substringToCalculate);
        stringToOpenBrackets = stringToOpenBrackets.substring(0, startPositionIncludingBrackets) +
                '(' + substringToCalculate + stringToOpenBrackets.substring(endPosition);
        return stringToOpenBrackets;
    }
    public String calculatedSubstring(String stringToCalculate) {
        PositionSearch positionSearch = new PositionSearch();
        Checking checking = new Checking();
        double firstNumber, secondNumber;
        if (checking.checkHasDivideSymbol(stringToCalculate) || checking.checkHasMultiplySymbol(stringToCalculate)) {
            firstNumber = Double.parseDouble(stringToCalculate.substring(0, positionSearch.searchMultipleOrDivideSymbol(stringToCalculate)));
            secondNumber = Double.parseDouble(stringToCalculate.substring(positionSearch.searchMultipleOrDivideSymbol(stringToCalculate) + 1));
        } else {
            firstNumber = Double.parseDouble(stringToCalculate.substring(0, positionSearch.searchPlusOrMinusSymbol(stringToCalculate)));
            secondNumber = Double.parseDouble(stringToCalculate.substring(positionSearch.searchPlusOrMinusSymbol(stringToCalculate) + 1));
        }
        if (checking.checkHasMultiplySymbol(stringToCalculate)) {
            firstNumber*=secondNumber;
            stringToCalculate = Double.toString(firstNumber);
            return stringToCalculate;
        } else if (checking.checkHasDivideSymbol(stringToCalculate)) {
            firstNumber/=secondNumber;
            stringToCalculate = Double.toString(firstNumber);
            return stringToCalculate;
        } else if (checking.checkHasPlusSymbol(stringToCalculate)) {
            firstNumber+=secondNumber;
            stringToCalculate = Double.toString(firstNumber);
            return stringToCalculate;
        } else if (checking.checkHasMinusSymbol(stringToCalculate)) {
            firstNumber-=secondNumber;
            stringToCalculate = Double.toString(firstNumber);
        }
        return stringToCalculate;
    }
}
