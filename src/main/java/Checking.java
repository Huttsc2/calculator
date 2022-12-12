public class Checking {
    public boolean checkHasBracket(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == '(')
                return true;
        }
        return false;
    }
}
