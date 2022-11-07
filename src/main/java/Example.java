public class Example {
    public static void main(String[] args) {
        String s = "11+(22+(123+123)+(3+44))+(4+5+6)+7+8";
        System.out.println(s);
        while (checkBrackets(s)) {
            s = swapFunction(s);
            System.out.println(s);
        }
        while (checkSigns(s)) {
            s = swapFunction(s);
            System.out.println(s);
        }
    }
    static int startChange(String s) {
        int x = 0, y = 0, z = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                y++;
                x = i;
            }
            if (s.charAt(i) == ')') {
                z++;
            }
            if (z == 1 && y > 0) {
                break;
            }
        }
        return x;
    }
    static int endChange(String s) {
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                y = i;
                break;
            }
        }
        return y;
    }
    static String swapFunction(String s) {
        String temp = s;
        if (checkBrackets(s)) {
            temp = deleteBrackets(s);
        }
        if (needToLocale(temp)) {
            temp = localExample(temp);
        } else {
            temp = countPlus(temp);
        }
        if (checkTwoSigns(s)) {
            temp = countPlus(temp);
            return temp;
        }
        if (!needToLocale(temp) && !needToLocale(s)) {
            s = s.substring(0, startChange(s)+1) + temp + s.substring(endChange(s));
            return s;
        } else if (!needToLocale(temp)){
            s = s.substring(0, startChange(s)) + temp + s.substring(endChange(s)+1);
            return s;
        }
        return temp;
    }
    static boolean checkBrackets(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                return true;
            }
        }
        return false;
    }
    static boolean checkSigns(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                return true;
            }
        }
        return false;
    }
    static boolean checkTwoSigns(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                x++;
            }
        }
        if (x == 2) {
            return true;
        } else {
            return false;
        }
    }


    static String deleteBrackets(String s) {
        int x = startChange(s);
        int y = endChange(s);
        s = s.substring(x+1, y);
        return s;
    }
    static String localExample(String s) {
        String temp;
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                for (int j = i+1; j < s.length(); j++) {
                    if (!Character.isDigit(s.charAt(j)) || j == s.length()-1) {
                        x = j;
                        break;
                    }
                }
                break;
            }
        }
        temp = s.substring(0, x);
        temp = countPlus(temp);
        s = temp + s.substring(x);
        return s;
    }
    static String countPlus(String s) {
        int x = 0, y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                x = Integer.parseInt(s.substring(0, i));
                y = Integer.parseInt(s.substring(i));
                break;
            }
        }
        x+=y;
        s = Integer.toString(x);
        return s;
    }

    static boolean needToLocale(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                x++;
            }
        }
        if (x > 1) {
            return true;
        }
        return false;
    }
}
