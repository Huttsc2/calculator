import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        String example;
        example = checkExample();
        example = countBrackets(example);
        example = countFinalExample(example);
        System.out.println(example);
    }
    static String countFinalExample(String s) {
        String temp, count;
        while (!checkInfinity(s) && isHasMoreThanSingleNumber(s)) {
            temp = localExample(s);
            count = countTemp(temp);
            if (isHasMoreThanTwoNumbers(s))
                s = s.substring(0, localStart(s)) + count + s.substring(localEnd(s));
            else
                 s = countTemp(s);
        }
        if (checkInfinity(s)) {
            s = "division by zero error";
        }
        return s;
    }
    static String openBrackets(String s) {
        while (!checkInfinity(s) && isHasPlusNumbersInBrackets(s)) {
            s = openPlusSingleNumbersBrackets(s);
        }
        return s;
    }
    static String checkExample() {
        String s = null;
        boolean isCorrect = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your example");
        System.out.println("You can use numbers and next symbols:");
        System.out.println("'.' '/' '*' '-' '+' '(' ')'");
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
        if (checkDivisionByZero(s))
            s = "Infinity";
        return s;
    }
    static String countBrackets(String s) {
        String example;
        String temp;
        String count;
        int x;
        while (!checkInfinity(s) && (isHasBracketsWithNoSingleNumber(s) || isHasBrackets(s))) {
            example = s;
            temp = localExampleInBrackets(example);
            example = example.substring(endBrackets(example) + 1);
            if (!isHasMoreThanSingleNumber(temp) && Double.parseDouble(temp) >= 0) {
                s = openBrackets(s);
                continue;
            }
            if (!isHasMoreThanSingleNumber(temp) && Double.parseDouble(temp) < 0) {
                s = openMinusSingleNumberInBrackets(s);
                continue;
            }
            if (!checkInfinity(s) && isHasMoreThanTwoNumbers(temp)) {
                x = temp.length()+1;
                temp = temp.substring(0, localStart(temp)) + countTemp(localExample(temp)) +
                        temp.substring(localEnd(temp));
                s = s.substring(0, s.length()-example.length()-x) +
                        temp + s.substring(s.length()-example.length()-1);
                continue;
            }
            if (isHasMoreThanSingleNumber(temp)) {
                count = countTemp(temp);
                s = s.substring(0, s.length()-example.length()-temp.length()-1) +
                        count + s.substring(s.length()-example.length()-1);
            }
        }
        return s;
    }
    static boolean isHasPlusNumbersInBrackets(String s) {
        boolean isItSingle = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                isItSingle = true;
            if (isItSingle && s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/')
                isItSingle = false;
            if (s.charAt(i) == ')' && isItSingle)
                break;
        }
        return isItSingle;
    }
    static String openPlusSingleNumbersBrackets(String s) {
        int x = 0, y = 0;
        boolean isPlusNumber = false;
        while (!isPlusNumber) {
            for (int i = y; i < s.length(); i++) {
                if (s.charAt(i) == '(')
                    x = i;
                if (s.charAt(i) == ')') {
                    y = i;
                    break;
                }
            }
            isPlusNumber = true;
            for (int i = x; i < y; i++) {
                if (s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '/' || s.charAt(i) == '*' ) {
                    isPlusNumber = false;
                    y+=1;
                    break;
                }
            }
        }
        s = s.substring(0, x) + s.substring(x+1, y) + s.substring(y+1);
        return s;
    }
    static String openMinusSingleNumberInBrackets(String s) {
        int x = startBrackets(s);
        int y = endBrackets(s);
        int q = y+1 == s.length() ? 0 : 1;
        int w = x == 0 ? 0 : 1;
        String temp;
        int z = 0;
        if (s.charAt(x-w) == '+' && s.charAt(y+q) != '*' && s.charAt(y+q) != '/') {
            s = s.substring(0, x-1) + s.substring(x+1, y) + s.substring(y+1);
        } else if (s.charAt(x-w) == '-' && s.charAt(x-w-w) != '(' && s.charAt(y+q) != '*' && s.charAt(y+q) != '/') {
            s = s.substring(0,x-w) + '+' + s.substring(x+2, y) + s.substring(y+q);
        } else if (s.charAt(x-w) == '(') {
            s = s.substring(0,x) + s.substring(x+1, y) + s.substring(y+1);
        } else if (s.charAt(x-w) == '-' && s.charAt(x-2) == '(') {
            s = s.substring(0,x-w) + s.substring(x+2, y) + s.substring(y+1);
        } else if ((s.charAt(y+q) == '*' || s.charAt(y+q) == '/') && s.charAt(y+q+q) != '(') {
            z = y + 3;
            for (int i = y + 2; i < s.length(); i++) {
                if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' ||
                        s.charAt(i) == '*' || s.charAt(i) == ')' ) {
                    z = i;
                    break;
                }
            }
            for (int i = y; i > 0; i--) {
                if (s.charAt(i) == '(') {
                    y = i + 1;
                    break;
                }
            }
            temp = s.substring(y, z).replaceAll("[)(]", "");
            temp = countTemp(temp);
            s = s.substring(0, x + 1) + temp + ')' + s.substring(z);
        } else if (s.charAt(x-w) == '*' || s.charAt(x-w) == '/') {
            for (int i = x-2; i > 0; i--) {
                if (s.charAt(i) == '+' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                    z = i+1;
                    break;
                } else if (s.charAt(i) == '-' &&  Character.isDigit(s.charAt(i-1))){
                    z = i+1;
                    break;
                } else if (s.charAt(i) == '-') {
                    z = i;
                    break;
                }
            }
            temp = s.substring(z, y).replaceAll("[)(]", "");
            temp = countTemp(temp);
            s = s.substring(0, z) + '(' + temp + s.substring(y);
        } else if (s.charAt(y+q) == '*' && s.charAt(y+q+q) == '(') {
            for (int i = y+4; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.') {
                    y = i;
                    break;
                }
            }
            temp = s.substring(x, y+1).replaceAll("[)(]", "");
            temp = countTemp(temp);
            s = s.substring(0, x) + '(' + temp + s.substring(y);
        }
        return s;
    }
    static boolean isHasMoreThanTwoNumbers(String s) {
        int x = isFirstSignsMinus(s) ? 1 : 0;
        int y = 0;
        for (int i = x; i < s.length(); i++) {
            if (s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-')
                y++;
        }
        return y > 1;
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
        int z = s.charAt(0) == '(' ? 1 : 0;
        int x = s.charAt(z) == '-' ? 1 + z : z;
        int y = 0;
        for (int i = x; i < s.length(); i++) {
            if (s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-')
                y++;
        }
        return y != 0;
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
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                x = i;
            }
            if (s.charAt(i) == ')')
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
        return s.charAt(0) == '-';
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
        int y = s.charAt(0) == '(' ? 1 : 0;
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
        return z == 0;
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
        return z == 0;
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
        if (checkEmptyExample(s))
            return false;
        if (s.charAt(s.length()-1) == '-' || s.charAt(s.length()-1) == '+' ||s.charAt(s.length()-1) == '/' ||
                s.charAt(s.length()-1) == '*' ||s.charAt(s.length()-1) == '(' || s.charAt(s.length()-1) == '.')
            return false;
        if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-' || s.charAt(0) == '(') {
            return checkBrackets(s) && checkSymbol(s) && checkSigns(s) && checkDots(s);
        } else {
            return false;
        }
    }
    static boolean checkEmptyExample(String s) {
        return s.length() == 0;
    }
    static boolean checkDivisionByZero(String s) {
        for (int i = 1; i < s.length()-1; i++) {
            if (s.charAt(i) == '0' && s.charAt(i-1) == '/') {
                if (i == s.charAt(i)-1)
                    return true;
                if (s.charAt(i+1) != '.')
                    return true;
            }
        }
        return false;
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
        return x == 0;
    }
    static boolean checkSymbol(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '/'
                    || s.charAt(i) == '*' || s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '.')
                x++;
        }
        return x == s.length();
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
        StringBuilder ns = new StringBuilder(s);
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
        for (String value : d) {
            for (int j = 0; j < value.length(); j++) {
                if (value.charAt(0) == '.')
                    return false;
                if (value.charAt(j) == '.') {
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