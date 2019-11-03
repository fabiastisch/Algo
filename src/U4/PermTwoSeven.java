package U4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Fabian Weber , Robin Schlund, Julian Beitinger
 * 202224       , 202961       , 202387
 */
public class PermTwoSeven {
    /**
     * Schreiben Sie ein Programm PermTwoSeven, das eine naturliche Zahl ¨ n einliest und dann
     * (m¨oglichst schnell) alle Permutationen π der Zahlen {1, 2, . . . , n} in aufsteigender Reihenfolge
     * auflistet, bei denen die absolute Differenz zweier direkt aufeinanderfolgender Zahlen genau 2 oder 7
     * betr¨agt. Zum Schluss soll das Programm auch die Zahl der gefundenen Permutationen ausgeben.
     * <p>
     * Fur ¨ n = 8 soll die Ausgabe also etwa so aussehen:
     * 2 4 6 8 1 3 5 7
     * 7 5 3 1 8 6 4 2
     * Es gab genau 2 Permutationen in {1,...,8} mit up-down in {2,7}
     */

    private static int n;
    private static int permAnzahl = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Geben Sie eine positive Ganzzahl ein ... (Sinnvoll größer 7)");
        try {
            n = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            n = -1;
            System.err.println("NumberFormatExeption");
        }

        for (int i = 1; i <= n; i++) {
            int[] a = new int[n];
            a[0] = i;
            permutationen(a, 1);
        }
        System.out.println("Die Anzahl der gültigen Permutationen für " + n + " beträgt: " + permAnzahl);
    }

    private static void permutationen(int[] array, int index) {
        if (index == n) {
            permAnzahl++;
            System.out.println(Arrays.toString(array));
        } else {
            plus2(array.clone(), index);
            minus2(array.clone(), index);
            plus7(array.clone(), index);
            minus7(array.clone(), index);
        }

    }


    private static void plus2(int[] i, int index) {
        int value = i[index - 1] + 2;
        if (value <= n && !intArrayContains(value, i)) {
            i[index] = value;
            permutationen(i, ++index);
        }
    }

    private static void plus7(int[] i, int index) {
        int value = i[index - 1] + 7;
        if (value <= n && !intArrayContains(value, i)) {
            i[index] = value;
            permutationen(i, ++index);
        }
    }

    private static void minus2(int[] i, int index) {
        int value = i[index - 1] - 2;
        if (value > 0 && !intArrayContains(value, i)) {
            i[index] = value;
            permutationen(i, ++index);
        }
    }

    private static void minus7(int[] i, int index) {
        int value = i[index - 1] - 7;
        if (value > 0 && !intArrayContains(value, i)) {
            i[index] = value;
            permutationen(i, ++index);
        }
    }

    private static boolean intArrayContains(int element, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) return true;
        }
        return false;
    }
}

