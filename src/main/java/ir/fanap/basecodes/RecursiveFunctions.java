package ir.fanap.basecodes;

public class RecursiveFunctions {
    public static void main(String[] args) {
        int n = 4;
        hanoi(n, "A", "C", "B");
    }

    private static void hanoi(int n, String base, String dest, String middle) {
        if (n == 1) {
            print(n, base, dest);
        } else {
            hanoi(n - 1, base, middle, dest);
            print(n, base, dest);
            hanoi(n - 1, middle, dest, base);
        }
    }

    private static void print(int n, String base, String dest) {
        System.out.println("Disk " + n + ": Bar " + base + " ------------> Bar " + dest);
    }
}
