package U5;

import java.util.Arrays;

public class PermuatationTest {
    public static void main(String[] args) {


        for (int n = 2; n < 10; n++) {

            Perm perm = new Perm(n);
            int fak = 0;
            int[] per;
            while ((per = perm.getNext()) != null) {
                //System.out.println(Arrays.toString(perm.getNext()));
                fak++;
            }
            System.out.println("\n\n Für n = " + n + " gibt es " + fak + " verschiedene Permutationen.");
            System.out.println(Perm.permAnzahl);
            Perm.permAnzahl = 0;
        }
    }
}
