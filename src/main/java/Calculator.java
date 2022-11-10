import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        String example;
        example = checkExample();
        example = countBrackets(example);
        example = openBrackets(example);
        example = countMilDiv(example);
        example = countMinBrackets(example);
        countFinalExample(example);
    }
    static String countFinalExample(String s) {
        String temp, count;
        while (!checkInfinity(s) && isHasMoreThanSingleNumber(s)) {
            temp = localExample(s);
            count = countTemp(temp);
            s = s.substring(0, localStart(s)) + count +
                    s.substring(localEnd(s));
            System.out.println(s);
        }
        return s;
    }
    static String countMinBrackets(String s) {
        String temp;
        while (!checkInfinity(s) && isHasBrackets(s)) {
            temp = localExampleInBrackets(s);
            if (startBrackets(s) == 0) {
                s = temp + s.substring(endBrackets(s) + 1);
            } else if (s.charAt(startBrackets(s)-1) == '-') {
                s = s.substring(0, startBrackets(s)-1) + '+' +
                        temp.substring(1) + s.substring(endBrackets(s)+1);
            } else if (s.charAt(startBrackets(s)-1) == '+') {
                s = s.substring(0, startBrackets(s)-1) + temp +
                        s.substring(endBrackets(s)+1);
            }
            System.out.println(s);
        }
        return s;
    }
    static String countMilDiv(String s) {
        String temp, count;
        int x, y;
        while (!checkInfinity(s) && (localMultiply(s) || localDivide(s))) {
            temp = localExampleIncludingBrackets(s);
            count = countTemp(temp);
            if (Double.parseDouble(count) < 0)
                count = '(' + count + ')';
            if (s.charAt(localMultiplyOrDivideSign(s)-1) == ')') {
                x = localStartIncludingBrackets(s)-1;
            } else {
                x = localStart(s);
            }
            if (s.charAt(localMultiplyOrDivideSign(s)+1) == '(') {
                y = localEndIncludingBrackets(s)+1;
            } else {
                y = localEnd(s);
            }
            s = s.substring(0, x) + count + s.substring(y);
        }
        return s;
    }
    static String openBrackets(String s) {
        while (!checkInfinity(s) && isHasPlusNumbersInBrackets(s)) {
            s = openPlusSingleNumbersBrackets(s);
            System.out.println(s);
        }
        return s;
    }
    static String checkExample() {
        String s = null;
        boolean isCorrect = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your example");
        while (!isCorrect) {
            s = sc.nextLine().replaceAll(" ", "");
            isCorrect = firstCorrect(s);
            if (!isCorrect) {
                System.out.println("incorrect example, try again");
                isCorrect = firstCorrect(s);
            }
        }
        sc.close();
        s = addMultiply(s);
        System.out.println(s);
        return s;
    }
    static String countBrackets(String s) {
        String example;
        String temp;
        String count;
        int x;
        while (!checkInfinity(s) && isHasBracketsWithNoSingleNumber(s)) {
            example = s;
            temp = localExampleInBrackets(example);
            example = example.substring(endBrackets(example) + 1);
            while (!isHasMoreThanSingleNumber(temp)) {
                temp = localExampleInBrackets(example);
                example = example.substring(endBrackets(example) + 1);
            }
            if (!checkInfinity(s) && isHasMoreThanTwoNumbers(temp)) {
                x = temp.length()+1;
                temp = temp.substring(0, localStart(temp)) + countTemp(localExample(temp)) +
                        temp.substring(localEnd(temp));
                s = s.substring(0, s.length()-example.length()-x) +
                        temp + s.substring(s.length()-example.length()-1);
                System.out.println(s);
                continue;
            }
            count = countTemp(temp);
            s = s.substring(0, s.length()-example.length()-temp.length()-1) +
                    count + s.substring(s.length()-example.length()-1);
            s = openBrackets(s);
            System.out.println(s);
        }
        return s;
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

    static int endBrackets(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                x = i;
                break;
            }
        }
        return x;
    }
    static int startBrackets(String s) {
        int x = 0, y = 0, z = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')')
                y++;
            if (s.charAt(i) == '('){
                x = i;
                z++;
            }
            if (z == y && y > 0)
                break;
        }
        return x;
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
            if (!Character.isDigit(s.charAt(i-1)) && s.charAt(i-1) != '.') {
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
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.')
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
        double x, y;
        if (isHasMultiplyOrDivide(s)) {
            x = Double.parseDouble(s.substring(0, localMultiplyOrDivideSign(s)));
            y = Double.parseDouble(s.substring(localMultiplyOrDivideSign(s) + 1));
        } else {
            x = Double.parseDouble(s.substring(0, localPlusOrMinusSign(s)));
            y = Double.parseDouble(s.substring(localPlusOrMinusSign(s) + 1));
        }
        if (localMultiply(s)) {
            x*=y;
            s = Double.toString(x);
            return s;
        } else if (localDivide(s)) {
            x/=y;
            s = Double.toString(x);
            return s;
        } else if (localPlus(s)) {
            x+=y;
            s = Double.toString(x);
            return s;
        } else if (localMinus(s)) {
            x-=y;
            s = Double.toString(x);
        }
        return s;
    }
    static boolean firstCorrect(String s) {
        if (s.charAt(s.length()-1) == '-' || s.charAt(s.length()-1) == '+' ||s.charAt(s.length()-1) == '/' ||
                s.charAt(s.length()-1) == '*' ||s.charAt(s.length()-1) == '(' || s.charAt(s.length()-1) == '.')
            return false;
        if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-' || s.charAt(0) == '(' || s.charAt(0) == '+') {
            if (checkBrackets(s) && checkSymbol(s) && checkSigns(s) && checkDots(s)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    static boolean checkBrackets(String s) {
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
    static boolean checkSymbol(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '/'
                    || s.charAt(i) == '*' || s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '.')
                x++;
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
    static String addMultiply(String s) {
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
    static boolean checkDots(String s) {
        s = s.replaceAll("[-+*()/]", " ").replaceAll("[\\s]{2,}", " ");
        String[] d = s.split(" ");
        int x = 0;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length(); j++) {
                if (d[i].charAt(0) == '.')
                    return false;
                if (d[i].charAt(j) == '.') {
                    x++;
                }
                if (x > 1)
                    return false;
            }
            x = 0;
        }
        return true;
    }
    static boolean checkInfinity(String s) {
        String con = "Infinity";
        return (s.contains(con));
    }
}