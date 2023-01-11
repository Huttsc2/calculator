import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionSearch { //TODO: What do you mean? the "localization" word has other meaning in programming. Also, the "localizing" word means process but not an object
    public int searchStartPositionClosedBrackets(String stringToSearch) {
        int startBracketsPosition = 0;
        for (int i = 0; i < stringToSearch.length(); i++) {
            //TODO: simplify the expression, you can use: stringToLocalize.indexOf('(');
            if (stringToSearch.charAt(i) == '('){
                startBracketsPosition = i;
            }
            if (stringToSearch.charAt(i) == ')')
                break;
            //TODO: i don't understand the reason for this expression
        }
        return startBracketsPosition;
    }
    public int searchEndPositionBrackets(String stringToSearch) {//TODO: the same with previous
        return stringToSearch.indexOf(')');
    }
    public String searchClosedBrackets(String stringToSearch) {
        int startBracketsPosition = searchStartPositionClosedBrackets(stringToSearch);
        int endBracketsPosition = searchEndPositionBrackets(stringToSearch);
        //TODO: you can unite the declaration and the initialization
        return stringToSearch.substring(startBracketsPosition+1, endBracketsPosition);
    }
    public int searchMultiplySymbolPosition(String stringToSearch) {
        int symbolPosition = 0;
        String regex = "[*]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToSearch);
        if (matcher.find()) {
            symbolPosition = matcher.start();
        }
        return symbolPosition;
    }
    public int searchDivideSymbolPosition(String stringToSearch) {
        int symbolPosition = 0;
        String regex = "/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToSearch);
        if (matcher.find()) {
            symbolPosition = matcher.start();
        }
        return symbolPosition;
    }
    public int searchPlusSymbolPosition(String stringToSearch) {//TODO: it's ambitious
        int firstMinusSymbolCorrection = stringToSearch.charAt(0) == '-' ? 1 : 0;
        int symbolPosition = 0;
        stringToSearch = stringToSearch.substring(firstMinusSymbolCorrection);
        String regex = "[+-]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToSearch);
        if (matcher.find()) {
            symbolPosition = matcher.start();
        }
        return symbolPosition+firstMinusSymbolCorrection;
    }
    public String searchSubstringForCalculations(String stringToSearch) {
        //TODO: unite
        return stringToSearch.substring(searchStartingPositionForCalculations(stringToSearch), searchEndPositionForCalculations(stringToSearch));
        //TODO: return stringToLocalize.substring(localizeStartingPositionToCount(stringToLocalize), localizeEndingPositionToCount(stringToLocalize));
    }
    public int searchStartingPositionForCalculations(String stringToSearch) {
        Checking checking = new Checking();
        int start; //TODO: simplify the expression
        if (checking.checkHasMultiplySymbol(stringToSearch)) {
            start = searchMultiplySymbolPosition(stringToSearch);
        } else if (checking.checkHasDivideSymbol(stringToSearch)) {
            start = searchDivideSymbolPosition(stringToSearch);
        } else {
            start = searchPlusSymbolPosition(stringToSearch);
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
        if (checking.checkHasMultiplySymbol(stringToSearch)) {
            end = searchMultiplySymbolPosition(stringToSearch);
        } else if (checking.checkHasDivideSymbol(stringToSearch)) {
            end = searchDivideSymbolPosition(stringToSearch);
        } else {
            end = searchPlusSymbolPosition(stringToSearch);
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
    public double searchFirstNumber(String stringToSearch) {
        double firstNumber = 0;
        String pattern = "(^-?\\d+\\.\\d+|^-?\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(stringToSearch);
        if (m.find()) {
            firstNumber = Double.parseDouble(m.group());
        }
        return firstNumber;
    }
    public double searchSecondNumber(String stringToSearch) {
        double secondNumber = 0;
        String pattern = "((?<=[/+*])-\\d+\\.\\d+|(?<=[/+*])-\\d+|\\d+\\.\\d+$|\\d+$)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(stringToSearch);
        if (m.find()) {
            secondNumber = Double.parseDouble(m.group());
        }
        return secondNumber;
    }
}
