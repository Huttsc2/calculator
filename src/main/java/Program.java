public class Program {
    public static void main(String[] args) {
        String s = "11+(22+33+(99.011)+(11.1*11)+66+(11+22)+77)+88+10";
        boolean isItSingle = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                isItSingle = true;
            if (isItSingle && s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/')
                isItSingle = false;
            if (s.charAt(i) == ')' && isItSingle)
                break;
        }
        System.out.println(isItSingle);
    }
}
