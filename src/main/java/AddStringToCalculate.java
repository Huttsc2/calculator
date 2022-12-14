import java.util.Scanner;

public class AddStringToCalculate {
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
    public void instructions() {
        System.out.println("Enter your example");
        System.out.println("You can use numbers and next symbols:");
        System.out.println("'.' '/' '*' '-' '+' '(' ')'");  //TODO: you should unite these rows and replace it by single method
    }
    public String addMultiplySymbol(String stringToAdd) { //TODO: the name is too big. We can live without additional information
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
