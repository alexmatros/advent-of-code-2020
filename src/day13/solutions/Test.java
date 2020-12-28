package day13.solutions;

public class Test {

    public static void main(String[] args) {
        long n = Long.parseLong("100000000000000");

        for (int i = 1; i < 13; i++) {
            long m = n + i;
            System.out.println("n: " + m + " r: " + m % 13);
        }
    }
}
