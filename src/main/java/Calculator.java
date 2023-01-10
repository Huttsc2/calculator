public class Calculator { //TODO: the name of this class and his idea are not the same
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        AddStringToCalculate addStringToCalculate = new AddStringToCalculate();
        /* TODO
        * it has to be united. Look at chain invocation
        */
        //TODO: you said "count", but the result is string, what do you mean?
        String stringToCalculate = calculator.calculateWithoutBrackets(calculator.calculateWhileHasBrackets(addStringToCalculate.addString())); //TODO: the same
        System.out.println(stringToCalculate);
    }
    public String calculateWhileHasBrackets(String stringToCalculate) {// TODO: the name is strange, also, we can live without additional information in the name
        PositionSearch positionSearch = new PositionSearch();
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        Checking checking = new Checking();
        while (checking.checkHasBracket(stringToCalculate)) { //TODO: Simplify the expression.
            String substringInBrackets = positionSearch.searchClosedBrackets(stringToCalculate);
            if (!checking.checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) >= 0) {
                stringToCalculate = calculateAndOpenBrackets.openBracketsWithPositiveNumber(stringToCalculate);
                continue;
            }
            if (!checking.checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) < 0) { //TODO: you can use it without if
                stringToCalculate = calculateAndOpenBrackets.openBracketsWithSingleNegativeNumber(stringToCalculate);
                continue;
            }
            if (checking.checkHasMoreThanTwoNumbers(substringInBrackets)) {
                stringToCalculate = calculateAndOpenBrackets.calculateSubstringHasMoreThanTwoNumbers(stringToCalculate);
                continue;
            }
            if (checking.checkHasMoreThanSingleNumber(substringInBrackets)) {
                stringToCalculate = calculateAndOpenBrackets.calculateSubstringHasMoreThanSingleNumber(stringToCalculate);
            }
        }
        return stringToCalculate;
    }
    public String calculateWithoutBrackets(String stringToCalculate) { //TODO: the "count" word is not the same with the "calculate" word
        PositionSearch positionSearch = new PositionSearch();
        CalculateAndOpenBrackets calculateAndOpenBrackets = new CalculateAndOpenBrackets();
        Checking checking = new Checking();
        while (checking.checkHasMoreThanSingleNumber(stringToCalculate)) { //TODO: simplify the expression
            String substringInBrackets = positionSearch.searchSubstringForCalculations(stringToCalculate);
            String solvedSubstringInBrackets = calculateAndOpenBrackets.calculatedSubstring(substringInBrackets);
            if (checking.checkHasMoreThanTwoNumbers(stringToCalculate))
                stringToCalculate = stringToCalculate.substring(0, positionSearch.searchStartingPositionForCalculations(stringToCalculate)) +
                        solvedSubstringInBrackets + stringToCalculate.substring(positionSearch.searchEndPositionForCalculations(stringToCalculate));
            else
                stringToCalculate = calculateAndOpenBrackets.calculatedSubstring(stringToCalculate);
        }
        if (checking.checkSubstringInfinity(stringToCalculate)) {
            stringToCalculate = "division by zero error";
        }
        return stringToCalculate;
    }
}