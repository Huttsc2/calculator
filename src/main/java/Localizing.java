public class Localizing {
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
        String substringInBrackets;
        substringInBrackets = stringToLocalize.substring(startBracketsPosition+1, endBracketsPosition);
        return substringInBrackets;
    }
    public int localizePlusOrMinusSymbol(String stringToLocalize) {
        int firstMinusSymbolCorrection = stringToLocalize.charAt(0) == '-' ? 1 : 0;
        int plusOrMinusPosition = 0;
        for (int i = firstMinusSymbolCorrection; i < stringToLocalize.length(); i++) {
            if (stringToLocalize.charAt(i) == '+' || stringToLocalize.charAt(i) == '-') {
                plusOrMinusPosition = i;
                break;
            }
        }
        return plusOrMinusPosition;
    }
    public int localizeMultipleOrDivideSymbol(String stringToLocalize) {
        int multiplyOrDividePosition = 0;
        for (int i = 0; i < stringToLocalize.length(); i++) {
            if (stringToLocalize.charAt(i) == '*' || stringToLocalize.charAt(i) == '/') {
                multiplyOrDividePosition = i;
                break;
            }
        }
        return multiplyOrDividePosition;
    }
    public String localizeSubstringToCount(String stringToLocalize) {
        String substringToCount;
        substringToCount = stringToLocalize.substring(localizeStartingPositionToCount(stringToLocalize), localizeEndingPositionToCount(stringToLocalize));
        return substringToCount;
    }
    public int localizeStartingPositionToCount(String stringToLocalize) {
        Checking checking = new Checking();
        int startPosition;
        if (checking.checkMultiplyOrDivideSymbol(stringToLocalize)) {
            startPosition = localizeMultipleOrDivideSymbol(stringToLocalize);
        } else {
            startPosition = localizePlusOrMinusSymbol(stringToLocalize);
        }
        if (checking.checkMathSymbolInSubstringIsFirstMathSymbol(stringToLocalize)) {
            startPosition = 0;
            return startPosition;
        }
        for (int i = startPosition; i > 1; i--) {
            if (!Character.isDigit(stringToLocalize.charAt(i-1)) && stringToLocalize.charAt(i-1) != '.') {
                startPosition = i;
                break;
            }
        }
        return startPosition;
    }
    public int localizeEndingPositionToCount(String stringToLocalize) {
        Checking checking = new Checking();
        int endPosition;
        if (checking.checkMultiplyOrDivideSymbol(stringToLocalize)) {
            endPosition = localizeMultipleOrDivideSymbol(stringToLocalize);
        } else {
            endPosition = localizePlusOrMinusSymbol(stringToLocalize);
        }
        if (checking.checkMathSymbolInSubstringIsLastMathSymbol(stringToLocalize)) {
            endPosition = stringToLocalize.length();
            return endPosition;
        }
        for (int i = endPosition+1; i < stringToLocalize.length(); i++) {
            endPosition = i;
            if (!Character.isDigit(stringToLocalize.charAt(i)) && stringToLocalize.charAt(i) != '.')
                break;
        }
        return endPosition;
    }
}
