import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStringToCalculate { //Class's name can't be like "add" or any verb
    public String addString() { //TODO: this method can be separated into two parts
        Checking checking = new Checking();
        String inputString = null;
        boolean isCorrect = false;
        Scanner sc = new Scanner(System.in);
        instructions();
        while (!isCorrect) { //TODO: simplify the method, you have got code-duplicate
            inputString = sc.nextLine().replaceAll(" ", "");
            isCorrect = checking.checkInputIsCorrect(inputString);
            if (!isCorrect)
                System.out.println("incorrect example, try again");
        }
        sc.close();
        inputString = addMultiplySymbol(inputString);
        if (checking.checkHasDivisionByZero(inputString))
            inputString = "Infinity";
        return inputString;
    }

    /**
     * That's I expected
     * We just get an expression
     */
    private String getExpression() {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine()
                .replaceAll(" ", "");
        while (!checkSyntax(expression)) {
            System.out.println("incorrect example, try again");
            expression = sc.nextLine()
                    .replaceAll(" ", "");
        }
        sc.close();
        return expression;
    }

    /**
     * Then, we will check the expression by syntax
     */
    private Boolean checkSyntax(String expression) {
        return new Checking().checkInputIsCorrect(expression);
    }

    /**
     * We can call these methods here
     */
    public String get() {
        instructions();
        String expression = getExpression();
        expression = addMultiplySymbol(expression);
        if (new Checking().checkHasDivisionByZero(expression))
            expression = "Infinity";
        return expression;
    }

    public void instructions() {
        System.out.println("Enter your example");
        System.out.println("You can use numbers and next symbols:");
        System.out.println("'.' '/' '*' '-' '+' '(' ')'");  //TODO: you should unite these rows and replace it by single method
    }

    public String addMultiplySymbol(String stringToAdd) { //TODO: the name is too big. We can live without additional information
        StringBuilder ns = new StringBuilder(stringToAdd);
        for (int i = 1; i < stringToAdd.length(); i++) { //TODO: you can use the symbols code to check it. Also, simplify the expression
            if (stringToAdd.charAt(i) == '(' && (Character.isDigit(stringToAdd.charAt(i - 1)) || stringToAdd.charAt(i - 1) == ')')) {
                ns.insert(i, '*');
                stringToAdd = ns.toString();
                i = 0;
            }
        }
        for (int i = 0; i < stringToAdd.length() - 1; i++)
            if (stringToAdd.charAt(i) == ')' && (Character.isDigit(stringToAdd.charAt(i + 1)) || stringToAdd.charAt(i + 1) == '(')) {
                ns.insert(i + 1, '*');
                stringToAdd = ns.toString();
                i = 0;
            }
        stringToAdd = ns.toString();
        return stringToAdd;
    }

    /**
     * It may be replaced by regex
     */
    public String addMultiplySymbol2(String expression) {
        String patternBeforeBracket = "\\d[(]";
        String patternAfterBracket = "[)]\\d";
        String patternBetween = "[)][(]";

        String formattedExpression = format(expression, patternBeforeBracket);
        formattedExpression = format(formattedExpression, patternAfterBracket);
        formattedExpression = format(formattedExpression, patternBetween);
        return formattedExpression;
    }
    private String format(String expression, String pattern) {
        StringBuilder builder = new StringBuilder();
        String tempExpression = expression;
        Matcher matcher = Pattern.compile(pattern).matcher(tempExpression);
        while (matcher.find()) {
            builder.append(tempExpression, 0, matcher.start()+1)
                    .append('*')
                    .append(tempExpression, matcher.end()-1, matcher.end());
            tempExpression = tempExpression.substring(matcher.end());
            matcher = Pattern.compile(pattern).matcher(tempExpression);
        }
        builder.append(tempExpression);
        return builder.toString();
    }
}
