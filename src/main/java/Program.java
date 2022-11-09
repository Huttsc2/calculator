public class Program {
    public static void main(String[] args) {
        double d = 123.12314215;
        String str = String.format("%.3f", d);
        Double z = Double.parseDouble(str);
        System.out.println(z);
    }
}
