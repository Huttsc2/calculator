public class PositionSearch { //TODO: What do you mean? the "localization" word has other meaning in programming. Also, the "localizing" word means process but not an object
    public int searchStartPositionClosedBrackets(String stringToSearch) {
        int startBracketsPosition = 0;
        for (int i = 0; i < stringToSearch.length(); i++) {
            //TODO: simplify the expression, you can use: stringToLocalize.indexOf('(');
            //i can`t, because i need to find the start position of the closed brackets, but not the first bracket
            if (stringToSearch.charAt(i) == '('){
                startBracketsPosition = i;
            }
            if (stringToSearch.charAt(i) == ')')
                break;
            //TODO: i don't understand the reason for this expression
            //this is necessary to determine that the brackets are closed
        }
        return startBracketsPosition;
    }
    public int searchEndPositionBrackets(String stringToSearch) {//TODO: the same with previous
        int endBracketsPosition = 0;
        for (int i = 0; i < stringToSearch.length(); i++) {
            if (stringToSearch.charAt(i) == ')') {
                endBracketsPosition = i;
                break;
            }
        }
        return endBracketsPosition;
    }
    public String searchClosedBrackets(String stringToSearch) {
        int startBracketsPosition = searchStartPositionClosedBrackets(stringToSearch);
        int endBracketsPosition = searchEndPositionBrackets(stringToSearch);
        //TODO: you can unite the declaration and the initialization
        return stringToSearch.substring(startBracketsPosition+1, endBracketsPosition);
    }
    public int searchPlusOrMinusSymbol(String stringToSearch) {//TODO: it's ambitious
        //I don`t understand what you mean
        int firstMinusSymbolCorrection = stringToSearch.charAt(0) == '-' ? 1 : 0;
        int plusOrMinusPosition = 0;
        for (int i = firstMinusSymbolCorrection; i < stringToSearch.length(); i++) {
            if (stringToSearch.charAt(i) == '+' || stringToSearch.charAt(i) == '-') {
                plusOrMinusPosition = i;
                break;
            }
        }
        return plusOrMinusPosition;
    }
    public int searchMultipleOrDivideSymbol(String stringToSearch) {//TODO: the same with previous
        int multiplyOrDividePosition = 0;
        for (int i = 0; i < stringToSearch.length(); i++) {
            if (stringToSearch.charAt(i) == '*' || stringToSearch.charAt(i) == '/') {
                multiplyOrDividePosition = i;
                break;
            }
        }
        return multiplyOrDividePosition;
    }
    public String searchSubstringForCalculations(String stringToSearch) {
        //TODO: unite
        return stringToSearch.substring(searchStartingPositionForCalculations(stringToSearch), searchEndPositionForCalculations(stringToSearch));
        //TODO: return stringToLocalize.substring(localizeStartingPositionToCount(stringToLocalize), localizeEndingPositionToCount(stringToLocalize));
    }
    public int searchStartingPositionForCalculations(String stringToSearch) {
        Checking checking = new Checking();
        int start; //TODO: simplify the expression
        if (checking.checkMultiplyOrDivideSymbol(stringToSearch)) {
            start = searchMultipleOrDivideSymbol(stringToSearch);
        } else {
            start = searchPlusOrMinusSymbol(stringToSearch);
        }
        if (checking.checkMathSymbolInSubstringIsFirstMathSymbol(stringToSearch)) {
            start = 0;
            return start;
        }
        for (int i = start; i > 1; i--) {
            if (!Character.isDigit(stringToSearch.charAt(i-1)) && stringToSearch.charAt(i-1) != '.') {
                start = i;
                break;
            }
        }

        return start;
    }
    public int searchEndPositionForCalculations(String stringToSearch) {
        Checking checking = new Checking();
        int end; //TODO: simplify
        if (checking.checkMultiplyOrDivideSymbol(stringToSearch)) {
            end = searchMultipleOrDivideSymbol(stringToSearch);
        } else {
            end = searchPlusOrMinusSymbol(stringToSearch);
        }
        if (checking.checkMathSymbolInSubstringIsLastMathSymbol(stringToSearch)) {
            end = stringToSearch.length();
            return end;
        }
        for (int i = end+1; i < stringToSearch.length(); i++) {
            end = i;
            if (!Character.isDigit(stringToSearch.charAt(i)) && stringToSearch.charAt(i) != '.')
                break;
        }
        return end;
    }
}
