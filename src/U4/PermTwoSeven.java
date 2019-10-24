package U4;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * Schreiben Sie ein Programm PermTwoSeven, das eine naturliche Zahl ¨ n einliest und dann
 * (m¨oglichst schnell) alle Permutationen π der Zahlen {1, 2, . . . , n} in aufsteigender Reihenfolge
 * auflistet, bei denen die absolute Differenz zweier direkt aufeinanderfolgender Zahlen genau 2 oder 7
 * betr¨agt. Zum Schluss soll das Programm auch die Zahl der gefundenen Permutationen ausgeben.
 *
 * Fur ¨ n = 8 soll die Ausgabe also etwa so aussehen:
 * 2 4 6 8 1 3 5 7
 * 7 5 3 1 8 6 4 2
 * Es gab genau 2 Permutationen in {1,...,8} mit up-down in {2,7}
 */

public class PermTwoSeven {
    private int n;
    private int[] a, even, odd;
    private ArrayList<int[]> evenL, oddL;

    ; // a Arbeitsarray
    private int max; // maximaler Index
    private boolean iseven;

    private Collection<int[]> solutions;


    public PermTwoSeven(int n) {
        a = new int[n]; // Indices: 0 .. n-1
        max = n - 1; // Maximaler Index
        for (int i = 0; i <= max; ) a[i] = ++i; // a fuellen, von 1 - max

        if (n % 2 == 0) {
            iseven = true;
        }
        if (iseven) {
            System.out.println("Even");
            even = new int[n / 2];
            odd = new int[n / 2];
        } else {
            System.out.println("odd");
            even = new int[n / 2 - 1];
            odd = new int[n / 2 + 1];
        }

    }

    public static void main(String[] args) {
        int nn;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                nn = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                nn = -1;
                System.err.println("NumberFormatExeption");
            }
            /*Perm p = new Perm(nn);
            int[]c;

            while ((c = p.getNext()) != null){
                System.out.println(arrStr(c));
            }*/
            PermTwoSeven p = new PermTwoSeven(nn);
            p.perm();

        } while (nn != -1);
    }

    private void perm() {

        for (int i = 0, e = 0, o = 0; i <= max; i++) {
            if (a[i] % 2 == 0) {
                even[e++] = a[i];
            } else {
                odd[o++] = a[i];
            }
        } // Array in gerade und ungerade gesplittet
        System.out.println("Gerade \t" + Arrays.toString(even) + "\nUngerade \t" + Arrays.toString(odd) + "\nA \t" + Arrays.toString(a));
        System.out.println("\n\n");

        a = new int[]{1, 3, 5, 7};

        permutationen(0, odd, true);
        permutationen(0, even, false);


    }

    private void permutationen(int i, int[] aa, boolean iseven) {
        int max = aa.length - 1;
        if (i >= max) {
            if (iseven) {
                evenL.add(aa);
            } else {
                oddL.add(aa);
            }
        } else {
            for (int j = i; j <= max; j++) { // jedes nach Vorne
                aa = swap(i, j, aa); // vertauschen
                permutationen(i + 1, aa, iseven); // und Rekursion
            }
            int h = aa[i]; // restauriere Array
            System.arraycopy(aa, i + 1, aa, i, max - i); // shift links
            aa[max] = h;
        }
    }

    private int[] swap(int i, int j, int[] a) { // tausche a[i] <-> a[j]
        if (i != j) {
            int h = a[i];
            a[i] = a[j];
            a[j] = h;
        }
        return a;
    } // end swap


    private boolean gueltig() {

        for (int j = 0; j < max; j++) {
            int abs = Math.abs(a[j] - a[j + 1]);

            if (abs != 2 && abs != 7) {
                //System.out.println("Return false");
                return false;
            }
        }

        return true;
    }

    private void test() {

        int[] temp = new int[n];

        for (int i = 0; i <= max; i++) {
            //if ()
            temp[i] = odd[i];


        }

    /*private void backtracking(int pos, int[]ar){

        while (noch nicht fertig){

            if (ar[pos] % 2 == 0) { //falls gerade
            }
            if (gültig) {
            }
        }
    }*/

    /*
     int[] temp = new int[n];

        for (int i = 0; i <= max; i++) {
            //if ()
            temp[i] = odd[i];

        }
     */


    }
}
