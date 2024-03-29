package U5;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class AufrufeVonPerm {
    static int n;
    static int[] a;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Geben Sie eine Zahl ein");
            try {
                n = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                n = -1;
                System.err.println("NumberFormatExeption");
            }

            BigInteger t = a(n);
            BigInteger fak = fak(n);
            System.out.println(t);
            System.out.println(fak);

            BigDecimal a = new BigDecimal(t);
            BigDecimal f = new BigDecimal(fak);
            System.out.println("\n" + a.divide(f, 101, RoundingMode.HALF_EVEN));


        }
    }

    static BigInteger fak(int n) {
        if (n == 0) return BigInteger.ONE;
        BigInteger ergebnis = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            ergebnis = ergebnis.multiply(BigInteger.valueOf(i));
        }
        return ergebnis;
    }

    static BigInteger a(int n) {

        if (n == 0) {
            return BigInteger.ZERO;
        }
        //int temp = n*a(n-1)+1;

        BigInteger temp = BigInteger.valueOf(n).multiply(a(n - 1)).add(BigInteger.ONE);


        return temp;
    }

}
