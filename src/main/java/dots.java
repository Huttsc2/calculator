public class dots {
    public static void main(String[] args) {
        String s = "1.23+12.3*(-12.3+1.24)+23.4-12.3*(-1.23)*(-2.34)+1+(-2.1)";
        s = s.replaceAll("[-+*()/]", " ").replaceAll("[\\s]{2,}", " ");
        String[] d = s.split(" ");
        int x = 0;
        boolean isHasOneDot = true;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length(); j++) {
                if (d[i].charAt(j) == '.') {
                    x++;
                }
                if (x > 1)
                    isHasOneDot = false;
            }
            x = 0;
        }
        System.out.println(isHasOneDot);
    }
}
