package ir.fanap.basecodes;

import java.io.IOException;

@FunctionalInterface
interface HappyNumberFinder {
    boolean find(Long n);
}

public class HappyNunberFinderMain {

    public static void main(String[] args) throws IOException {
        HappyNumberFinder finder = n -> {
            long happyNumber = findHappyNumber(n);
            System.out.println(happyNumber);
            return happyNumber == 1;
        };
        System.out.println(finder.find(19L)); // 1 true
        System.out.println(finder.find(2L)); // 2 false
    }

    private static int countDigits(Long n) {
        return (int) Math.floor(Math.log10(n) + 1);
    }

    public static long findHappyNumber(long n) {
        if (countDigits(n) == 1) return n;

        long sum = 0;
        long temp = n;
        while (temp > 0) {
            long d = temp % 10;
            temp /= 10;
            sum += d * d;
        }
        return findHappyNumber(sum);
    }
}

