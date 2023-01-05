import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Qwe {
    public static void main(String[] args) {
        String input = "-11-13";
        String pattern = "(^-?\\d+\\.\\d+|^-?\\d+|\\d+\\.\\d+|\\d+)";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(input);

        ArrayList<String> group = new ArrayList<>();
        while (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                group.add(m.group(i));
            }
        }
        for (String gr : group)
            System.out.println(gr);
    }
}
