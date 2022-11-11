public class Program {
    public static void main(String[] args) {
        String s = "11+(10+(-12)*6+77)+88";
        System.out.println(s);
        int x = 0,y = 0,z = 0;
        boolean bracketsOpen = false;
        boolean isHasMinus = false;
        for (int i = 0; i < s.length(); i++) {
            if (bracketsOpen && s.charAt(i) == '(' && s.charAt(i+1) == '-') {
                y = i;
                i+=2;
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) == '(' || s.charAt(j) == '/' || s.charAt(j) == '*'
                            || s.charAt(j) == '-' || s.charAt(j) == '+')
                        break;
                    if (s.charAt(j) == ')' && x > 0)
                        isHasMinus = true;
                    z = j;
                }
            }
            if (s.charAt(i) == '(') {
                x++;
                bracketsOpen = true;
            }
            if (s.charAt(i) == ')') {
                x--;
            }
            if (bracketsOpen && x == 0)
                bracketsOpen = false;

            if (isHasMinus)
                break;
        }
        System.out.println(s.substring(0,y-1) + s.substring(y+2, z) + s.substring(z+1));
        System.out.println(s.charAt(y));
        System.out.println(s.charAt(z));
        System.out.println(isHasMinus);
        System.out.println(y + " " + z);
    }
}
