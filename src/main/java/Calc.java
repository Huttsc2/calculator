import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        System.out.println("Enter your example");
        Scanner sc = new Scanner(System.in);
        boolean is_correct = false;
        String mainexample = null;
        while (!is_correct) {
            mainexample = sc.nextLine().replaceAll(" ", "");
            is_correct = first_correct(mainexample);
            if (!is_correct) {
                System.out.println("incorrect example, try again");
                is_correct = first_correct(mainexample);
            }
        }
        sc.close();
        mainexample = add_multiply(mainexample);
    }
    static boolean first_correct(String s) {
        if (s.charAt(s.length()-1) == '-' || s.charAt(s.length()-1) == '+' ||s.charAt(s.length()-1) == '/' ||
                s.charAt(s.length()-1) == '*' ||s.charAt(s.length()-1) == '(') {
            return false;
        }
        if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-' || s.charAt(0) == '(' || s.charAt(0) == '+') {
            if (check_brackets(s) && check_symbol(s) && checkSigns(s)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    static boolean check_brackets(String s) {
        int x = 0;
        boolean is_first_right_bracket = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                is_first_right_bracket = true;
                x++;
                if (Character.isDigit(s.charAt(i+1)) || s.charAt(i+1) == '('
                        || s.charAt(i+1) == '-' || s.charAt(i+1) == '+') {
                } else {
                    x++;
                }
            } else if (s.charAt(i) == ')' && is_first_right_bracket) {
                x--;
            }
        }
        if (x == 0) {
            return true;
        } else {
            return false;
        }
    }
    static boolean check_symbol(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '/'
            || s.charAt(i) == '*' || s.charAt(i) == '(' || s.charAt(i) == ')') {
                x++;
            }
        }
        if (x == s.length()) {
            return true;
        } else {
            return false;
        }
    }
    static boolean checkSigns(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) == '-' || s.charAt(i-1) == '+' || s.charAt(i-1) == '/' || s.charAt(i-1) == '*') {
                if (s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                    return false;
                }
            }
        }
        return true;
    }
    static String add_multiply(String s) {
        StringBuffer ns = new StringBuffer(s);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(' && (Character.isDigit(s.charAt(i-1)) || s.charAt(i-1) == ')')) {
                ns.insert(i, '*');
                s = ns.toString();
                i = 0;
            }
        }
        for (int i = 0; i < s.length()-1; i++)
            if (s.charAt(i) == ')' && (Character.isDigit(s.charAt(i+1)) || s.charAt(i+1) == '(')) {
                ns.insert(i + 1 , '*');
                s = ns.toString();
                i = 0;
            }
        s = ns.toString();
        return s;
    }
}
//add 0