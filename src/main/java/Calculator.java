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
        Checking checking = new Checking();
        String inputString = null;
        boolean isCorrect = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your example");
        System.out.println("You can use numbers and next symbols:");
        System.out.println("'.' '/' '*' '-' '+' '(' ')'");
        while (!isCorrect) {
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
    public String countWhileHasBrackets(String stringToCount) {
        Localizing localizing = new Localizing();
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        Checking checking = new Checking();
        while (!checking.checkSubstringInfinity(stringToCount) && checking.checkHasBracket(stringToCount)) {
            String substringInBrackets = localizing.localizeClosedBrackets(stringToCount);
            if (!checking.checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) >= 0) {
                stringToCount = countAndOpenBrackets.openBracketsWithPositiveNumber(stringToCount);
                continue;
            }
            if (!checking.checkHasMoreThanSingleNumber(substringInBrackets) && Double.parseDouble(substringInBrackets) < 0) {
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
    public String countWithoutBrackets(String stringToCount) {
        Localizing localizing = new Localizing();
        CountAndOpenBrackets countAndOpenBrackets = new CountAndOpenBrackets();
        Checking checking = new Checking();
        String substringInBrackets;
        while (!checking.checkSubstringInfinity(stringToCount) && checking.checkHasMoreThanSingleNumber(stringToCount)) {
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
                ns.insert(i+1 , '*');
                stringToAdd = ns.toString();
                i = 0;
            }
        stringToAdd = ns.toString();
        return stringToAdd;
    }
}