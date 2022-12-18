public class Localizing { //TODO: What do you mean? the "localization" word has other meaning in programming. Also, the "localizing" word means process but not an object
    public int localizeStartPositionBrackets(String stringToLocalize) {
        int startBracketsPosition = 0;
        for (int i = 0; i < stringToLocalize.length(); i++) {
            //TODO: simplify the expression, you can use: stringToLocalize.indexOf('(');
            if (stringToLocalize.charAt(i) == '('){
                startBracketsPosition = i;
            }
            if (stringToLocalize.charAt(i) == ')') //TODO: i don't understand the reason for this expression
                break;
        }
        return startBracketsPosition;
    }
    public int localizeEndPositionBrackets(String stringToLocalize) {//TODO: the same with previous
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
        String substringInBrackets; //TODO: you can unite the declaration and the initialization
        substringInBrackets = stringToLocalize.substring(startBracketsPosition+1, endBracketsPosition);
        return substringInBrackets;
    }
    public int localizePlusOrMinusSymbol(String stringToLocalize) {//TODO: it's ambitious
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
    public int localizeMultipleOrDivideSymbol(String stringToLocalize) {//TODO: the same with previous
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
        String substringToCount; //TODO: unite
        substringToCount = stringToLocalize.substring(localizeStartingPositionToCount(stringToLocalize), localizeEndingPositionToCount(stringToLocalize));
        return substringToCount;

        //TODO: return stringToLocalize.substring(localizeStartingPositionToCount(stringToLocalize), localizeEndingPositionToCount(stringToLocalize));
    }
    public int localizeStartingPositionToCount(String stringToLocalize) {
        Checking checking = new Checking();
        int startPosition; //TODO: simplify the expression
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
        int endPosition; //TODO: simplify
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
