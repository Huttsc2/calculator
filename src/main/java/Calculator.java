import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        String mainexample = null;
        String example;
        String temp;
        String count;
        boolean is_correct = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your example");
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
        int x , y;
        System.out.println(mainexample);
        while (isHasBracketsWithNoSingleNumber(mainexample)) {
            example = mainexample;
            temp = localExampleInBrackets(example);
            example = example.substring(endBrackets(example) + 1);
            while (!isHasMoreThanSingleNumber(temp)) {
                temp = localExampleInBrackets(example);
                example = example.substring(endBrackets(example) + 1);
            }
            if (isHasMoreThanTwoNumbers(temp)) {
                x = temp.length()+1;
                temp = temp.substring(0, localStart(temp)) + countTemp(localExample(temp)) +
                        temp.substring(localEnd(temp));
                mainexample = mainexample.substring(0, mainexample.length()-example.length()-x) +
                        temp + mainexample.substring(mainexample.length()-example.length()-1);
                System.out.println(mainexample);
                continue;
            }
            count = countTemp(temp);
            mainexample = mainexample.substring(0, mainexample.length()-example.length()-temp.length()-1) +
                    count + mainexample.substring(mainexample.length()-example.length()-1);
            System.out.println(mainexample);
        }
        while (isHasPlusNumbersInBrackets(mainexample)) {
            mainexample = openPlusSingleNumbersBrackets(mainexample);
            System.out.println(mainexample);
        }
        while (localMultiply(mainexample) || localDivide(mainexample)) {
            temp = localExampleIncludingBrackets(mainexample);
            count = countTemp(temp);
            if (Integer.parseInt(count) < 0)
                count = '(' + count + ')';
            if (mainexample.charAt(localMultiplyOrDivideSign(mainexample)-1) == ')') {
                x = localStartIncludingBrackets(mainexample)-1;
            } else {
                x = localStart(mainexample);
            }
            if (mainexample.charAt(localMultiplyOrDivideSign(mainexample)+1) == '(') {
                y = localEndIncludingBrackets(mainexample)+1;
            } else {
                y = localEnd(mainexample);
            }
            mainexample = mainexample.substring(0, x) + count + mainexample.substring(y);
        }
        while (isHasBrackets(mainexample)) {
            temp = localExampleInBrackets(mainexample);
            if (startBrackets(mainexample) == 0) {
                mainexample = temp + mainexample.substring(endBrackets(mainexample) + 1);
            } else if (mainexample.charAt(startBrackets(mainexample)-1) == '-') {
                mainexample = mainexample.substring(0, startBrackets(mainexample)-1) + '+' +
                        temp.substring(1) + mainexample.substring(endBrackets(mainexample)+1);
            } else if (mainexample.charAt(startBrackets(mainexample)-1) == '+') {
                mainexample = mainexample.substring(0, startBrackets(mainexample)-1) + temp +
                        mainexample.substring(endBrackets(mainexample)+1);
            }
            System.out.println(mainexample);
        }
        while (isHasMoreThanSingleNumber(mainexample)) {
            temp = localExample(mainexample);
            count = countTemp(temp);
            mainexample = mainexample.substring(0, localStart(mainexample)) + count +
                    mainexample.substring(localEnd(mainexample));
            System.out.println(mainexample);
        }
        System.out.println(mainexample);
    }
    static boolean isHasPlusNumbersInBrackets(String s) {
        for (int i = 0; i < s.length()-1; i++) {
            if(s.charAt(i) == '(' && Character.isDigit(s.charAt(i+1))) {
                return true;
            }
        }
        return false;
    }
    static String localExampleIncludingBrackets(String s) {
        int x = localMultiplyOrDivideSign(s);
        int y, z;
        if (s.charAt(x-1) == ')') {
            y = localStartIncludingBrackets(s);
        } else {
            y = localStart(s);
        }
        if (s.charAt(x+1) == '(') {
            z = localEndIncludingBrackets(s);
        } else {
            z = localEnd(s);
        }
        s = s.substring(y, z);
        s = s.replaceAll("[)(]", "");
        return s;
    }
    static int localStartIncludingBrackets(String s) {
        int x = 1;
        if (isHasMultiplyOrDivide(s)) {
            x = localMultiplyOrDivideSign(s);
        }
        if (isItFirstSign(s)) {
            x = 1;
            return x;
        }
        for (int i = x; i > 1; i--) {
            if (s.charAt(i-1) == '(') {
                x = i;
                break;
            }
        }
        return x;
    }
    static int localEndIncludingBrackets(String s) {
        int y;
        if (isHasMultiplyOrDivide(s)) {
            y = localMultiplyOrDivideSign(s);
        } else {
            y = localPlusOrMinusSign(s);
        }
        if (isItLastSign(s)) {
            y = s.length();
            return y;
        }
        for (int i = y+1; i < s.length(); i++) {
            y = i;
            if (s.charAt(i) == ')')
                break;
        }
        return y;
    }
    static String openPlusSingleNumbersBrackets(String s) {
        int x = 0, y = s.length()-1;
        boolean is_minus = false;
        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i) == '(' && s.charAt(i + 1) != '-') {
                x = i;
                is_minus = true;
            }
            if (s.charAt(i) == ')' && is_minus) {
                y = i;
                break;
            }
        }
        s = s.substring(0, x) + s.substring(x+1, y) + s.substring(y+1);
        return s;
    }
    static boolean isHasMinusAfterBrackets(String s) {
        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i) == '(' && s.charAt(i+1) == '-')
                return true;
        }
        return false;
    }
    static boolean isHasMoreThanTwoNumbers(String s) {
        int x = isFirstSignsMinus(s) ? 1 : 0;
        int y = 0;
        for (int i = x; i < s.length(); i++) {
            if (s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-')
                y++;
        }
        if (y > 1)
            return true;
        return false;
    }
    static boolean isHasBracketsWithNoSingleNumber(String s) {
        int x;
        for (int i = 0; i < s.length()-1; i++) {
            x = s.charAt(i+1) == '-' ? 2 : 1;
            if(s.charAt(i) == '(') {
                for (int j = i+x; s.charAt(j) != ')'; j++) {
                    if (s.charAt(j) == '-' || s.charAt(j) == '+' || s.charAt(j) == '*' || s.charAt(j) == '/')
                        return true;
                }
            }
        }
        return false;
    }
    static boolean isHasZeroAfterDivide(String s) {
        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i) == '/' && s.charAt(i+1) == '0')
                return true;
        }
        return false;
    }
    static boolean isHasMoreThanSingleNumber(String s) {
        int x = s.charAt(0) == '-' ? 1 : 0;
        int y = 0;
        for (int i = x; i < s.length(); i++) {
            if (s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-')
                y++;
        }
        if (y == 0)
            return false;
        return true;
    }
    static boolean isHasBrackets(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                return true;
        }
        return false;
    }

    static int startBrackets(String s) {
        int x = 0, y = 0, z = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                y++;
                x = i;
            }
            if (s.charAt(i) == ')')
                z++;
            if (z == 1 && y > 0)
                break;
        }
        return x;
    }
    static int endBrackets(String s) {
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                y = i;
                break;
            }
        }
        return y;
    }
    static String localExampleInBrackets(String s) {
        int x = startBrackets(s);
        int y = endBrackets(s);
        s = s.substring(x+1, y);
        return s;
    }
    static boolean isFirstSignsMinus(String s) {
        if (s.charAt(0) == '-')
            return true;
        return false;
    }
    static boolean isHasMultiplyOrDivide(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/')
                return true;
        }
        return false;
    }
    static int localPlusOrMinusSign(String s) {
        int x = s.charAt(0) == '-' ? 1 : 0;
        for (int i = x; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                x = i;
                break;
            }
        }
        return x;
    }
    static int localMultiplyOrDivideSign(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                x = i;
                break;
            }
        }
        return x;
    }
    static String localExample(String s) {
        s = s.substring(localStart(s), localEnd(s));
        return s;
    }
    static boolean isItFirstSign(String s) {
        int y = s.charAt(0) == ')' ? 1 : 0;
        int x = s.charAt(y) == '-' ? 1 : 0;
        int z = 0;
        if (isHasMultiplyOrDivide(s)) {
            y = localMultiplyOrDivideSign(s);
        } else {
            y = localPlusOrMinusSign(s);
        }
        for (int i = x; i < y; i++) {
            if (s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-') {
                z++;
            }
        }
        if (z == 0) {
            return true;
        } else {
            return false;
        }
    }
    static boolean isItLastSign(String s) {
        int x;
        int z = 0;
        if (isHasMultiplyOrDivide(s)) {
            x = localMultiplyOrDivideSign(s);
        } else {
            x = localPlusOrMinusSign(s);
        }
        for (int i = x+1; i < s.length(); i++) {
            if (s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-') {
                z++;
            }
        }
        if (z == 0) {
            return true;
        } else {
            return false;
        }
    }
    static int localStart(String s) {
        int x;
        if (isHasMultiplyOrDivide(s)) {
            x = localMultiplyOrDivideSign(s);
        } else {
            x = localPlusOrMinusSign(s);
        }
        if (isItFirstSign(s)) {
            x = 0;
            return x;
        }
        for (int i = x; i > 1; i--) {
            if (!Character.isDigit(s.charAt(i-1))) {
                x = i;
                break;
            }
        }
        return x;
    }
    static int localEnd(String s) {
        int y;
        if (isHasMultiplyOrDivide(s)) {
            y = localMultiplyOrDivideSign(s);
        } else {
            y = localPlusOrMinusSign(s);
        }
        if (isItLastSign(s)) {
            y = s.length();
            return y;
        }
        for (int i = y+1; i < s.length(); i++) {
            y = i;
            if (!Character.isDigit(s.charAt(i)))
                break;
        }
        return y;
    }
    static boolean localMultiply(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*')
                return true;
        }
        return false;
    }
    static boolean localDivide(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/')
                return true;
        }
        return false;
    }
    static boolean localPlus(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+')
                return true;
        }
        return false;
    }
    static boolean localMinus(String s) {
        int x = s.charAt(0) == '-' ? 1 : 0;
        for (int i = x; i < s.length(); i++) {
            if (s.charAt(i) == '-')
                return true;
        }
        return false;
    }
    static String countTemp(String s) {
        int x, y;
        if (isHasMultiplyOrDivide(s)) {
            x = Integer.parseInt(s.substring(0, localMultiplyOrDivideSign(s)));
            y = Integer.parseInt(s.substring(localMultiplyOrDivideSign(s) + 1));
        } else {
            x = Integer.parseInt(s.substring(0, localPlusOrMinusSign(s)));
            y = Integer.parseInt(s.substring(localPlusOrMinusSign(s) + 1));
        }
        if (localMultiply(s)) {
            x*=y;
            s = Integer.toString(x);
            return s;
        } else if (localDivide(s)) {
            x/=y;
            s = Integer.toString(x);
            return s;
        } else if (localPlus(s)) {
            x+=y;
            s = Integer.toString(x);
            return s;
        } else if (localMinus(s)) {
            x-=y;
            s = Integer.toString(x);
        }
        return s;
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