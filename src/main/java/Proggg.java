import java.util.*;

public class Proggg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<String, String> kid = new HashMap<String, String>();
        for (int i = 0; i < n; i++) {
            kid.put(sc.next(), sc.nextLine().toLowerCase());
        }
        int k = sc.nextInt();
        sc.close();
        HashSet<String> words = new HashSet<String>();
        HashSet<String> morewords = new HashSet<String>();
        String smartkid = null;
        int x = 0, y = 0;
        for (Map.Entry<String, String> i : kid.entrySet()) {
            String[] kidswords = i.getValue().split("\\s+");
            for (int j = 0; j < kidswords.length; j++) {
                if (kidswords[j].length() >= k) {
                    words.add(kidswords[j]);
                    x++;
                }
                if (x > y) {
                    morewords = words;
                    y = x;
                    smartkid = i.getKey();
                    x = 0;
                    words.clear();
                }
            }
        }

        System.out.println(smartkid);
        for (String i : morewords)
            System.out.print(i + " ");
    }
}

