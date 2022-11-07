import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MyProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<String, String> kid = new HashMap<String, String>();
        HashMap<String, String> sk = new HashMap<String, String>();
        for (int i = 0; i < n; i++) {
            kid.put(sc.next().replaceAll("[^A-Za-zА-Яа-я]", ""), sc.nextLine().toLowerCase());
        }
        int k = sc.nextInt();
        sc.close();
        HashSet<String> words = new HashSet<String>();
        int x = 0;
        for (Map.Entry<String, String> i : kid.entrySet()) {
            String[] w = i.getValue().split("\\s+");
            for (int j = 0; j < w.length; j++) {
                if (w[j].length() >= k) {
                    words.add(w[j]);
                }
            }
            if (words.size() > x) {
                x = words.size();
                sk.clear();
                sk.put(i.getKey(), words.toString());
            }

        }
        for (Map.Entry<String, String> i : sk.entrySet()) {
            System.out.println(i.getKey() + i.getValue());
        }
    }
}