public class Program {
    public static void main(String[] args) {
        String s = "(0.1+(-1))";
        boolean isHas = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' && s.charAt(i+1) == '-') {
                i+=2;
                isHas = true;
                while (s.charAt(i) != ')') {
                    i++;
                    if (s.charAt(i) == '+' || s.charAt(i) == '-' ||s.charAt(i) == '*' ||s.charAt(i) == '/') {
                        isHas = false;
                        break;
                    }
                }
                if (isHas)
                    break;
            }
        }
        System.out.println(isHas);
    }
}
