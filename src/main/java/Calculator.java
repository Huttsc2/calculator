import java.util.Scanner;

public  class Calculator { //TODO: the name of this class and his idea are not the same
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        /* TODO
        * it has to be united. Look at chain invocation
        */
        String stringToCount;
        stringToCount = calculator.addStringAndCheckIt();
        stringToCount = calculator.countWhileHasBrackets(stringToCount); //TODO: you said "count", but the result is string, what do you mean?
        stringToCount = calculator.countWithoutBrackets(stringToCount); //TODO: the same
        System.out.println(stringToCount);
    }
    public String addStringAndCheckIt() { //TODO: this method can be separated into two parts
        Checking checking = new Checking();
        String inputString = null;
        boolean isCorrect = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your example");
        System.out.println("You can use numbers and next symbols:");
        System.out.println("'.' '/' '*' '-' '+' '(' ')'");  //TODO: you should unite these rows and replace it by single method
        while (!isCorrect) { //TODO: simplify the method, you have got code-duplicate
            inputString = sc.nextLine().replaceAll(" ", "");
            isCorrect = checking.checkInputIsCorrect(inputString);
            if (!isCorrect) {
                System.out.println("incorrect example, try again");
                isCorrect = checking.checkInputIsCorrect(inputString);
            }
        }
        sc.close();
        inputString = addMultiplySymbolBeforeAndAfterBrackets(inputString);
        if (checking.checkHasDivisionByZero(inputString))
            inputString = "Infinity";
        return inputString;
    }
    public String countWhileHasBrackets(String stringToCount) {// TODO: the name is strange, also, we can live without additional information in the name
        Localizing localizing = new Localizing();
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        Checking checking = new Checking();
        while (!checking.checkSubstringInfinity(stringToCount) && checking.checkHasBracket(stringToCount)) { //TODO: Simplify the expression.
            String substringInBrackets = localizing.localizeClosedBrackets(stringToCount);
            if (!checking.checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) >= 0) {
                stringToCount = countAndOpenBrackets.openBracketsWithPositiveNumber(stringToCount);
                continue;
            }
            if (!checking.checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) < 0) { //TODO: you can use it without if
                stringToCount = countAndOpenBrackets.openBracketsWithSingleNegativeNumber(stringToCount);
                continue;
            }
            if (!checking.checkSubstringInfinity(stringToCount) && checking.checkHasMoreThanTwoNumbers(substringInBrackets)) {
                stringToCount = countAndOpenBrackets.countSubstringHasMoreThanTwoNumbers(stringToCount);
                continue;
            }
            if (checking.checkHasMoreThanSingleNumber(substringInBrackets)) {
                stringToCount = countAndOpenBrackets.countSubstringHasMoreThanSingleNumber(stringToCount);
            }
        }
        return stringToCount;
    }
    public String countWithoutBrackets(String stringToCount) { //TODO: the "count" word is not the same with the "calculate" word
        Localizing localizing = new Localizing();
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        Checking checking = new Checking();
        String substringInBrackets;
        while (!checking.checkSubstringInfinity(stringToCount) && checking.checkHasMoreThanSingleNumber(stringToCount)) { //TODO: simplify the expression
            substringInBrackets = localizing.localizeSubstringToCount(stringToCount);
            String solvedSubstringInBrackets = countAndOpenBrackets.countSubstring(substringInBrackets);
            if (checking.checkHasMoreThanTwoNumbers(stringToCount))
                stringToCount = stringToCount.substring(0, localizing.localizeStartingPositionToCount(stringToCount)) +
                        solvedSubstringInBrackets + stringToCount.substring(localizing.localizeEndingPositionToCount(stringToCount));
            else
                stringToCount = countAndOpenBrackets.countSubstring(stringToCount);
        }
        if (checking.checkSubstringInfinity(stringToCount)) {
            stringToCount = "division by zero error";
        }
        return stringToCount;
    }
    public String addMultiplySymbolBeforeAndAfterBrackets(String stringToAdd) { //TODO: the name is too big. We can live without additional information
        StringBuilder ns = new StringBuilder(stringToAdd);
        for (int i = 1; i < stringToAdd.length(); i++) { //TODO: you can use the symbols code to check it. Also, simplify the expression
            if (stringToAdd.charAt(i) == '(' && (Character.isDigit(stringToAdd.charAt(i-1)) || stringToAdd.charAt(i-1) == ')')) {
                ns.insert(i, '*');
                stringToAdd = ns.toString();
                i = 0;
            }
        }
        for (int i = 0; i < stringToAdd.length()-1; i++)
            if (stringToAdd.charAt(i) == ')' && (Character.isDigit(stringToAdd.charAt(i+1)) || stringToAdd.charAt(i+1) == '(')) {
                ns.insert(i+1 , '*');
                stringToAdd = ns.toString();
                i = 0;
            }
        stringToAdd = ns.toString();
        return stringToAdd;
    }
}