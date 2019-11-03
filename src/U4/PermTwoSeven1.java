package U4;

import java.util.Arrays;
import java.util.Scanner;

public class PermTwoSeven1 {

    private static int n;
    private static int permAnzahl = 0;
    // private static int[] used;
    // private static int[] curr;
    //  private static int index = 1;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            n = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            n = -1;
            System.err.println("NumberFormatExeption");
        }

        for (int i = 1; i <= n; i++) {
            int[] current = new int[n];
            // int[] used = new int[n];
            current[0] = i;
            //  used[0] = i;
            p(current, 1);
            /*curr = new int[n];
            curr[0] = i;*/
            //perm(i);
        }
        System.out.println(permAnzahl);

    }

    private static void p(int[] array, int index) {
        if (index == n) {
            permAnzahl++;
            System.out.println(Arrays.toString(array));
        } else {
            plus7(array, index);
            plus2(array, index);
            minus2(array, index);
            minus7(array, index);
        }

    }


    private static void plus2(int[] i, int index) {
        int value = i[index - 1] + 2;
        if (value <= n && !intArrayContains(value, i)) {
            //used[index] = value;
            i[index] = value;
            p(i, ++index);
        }
    }

    private static void plus7(int[] i, int index) {
        System.out.println("plus7");
        int value = i[index - 1] + 7;
        if (value <= n && !intArrayContains(value, i)) {
            //used[index] = value;
            i[index] = value;
            p(i, ++index);
        }

    }

    private static void minus2(int[] i, int index) {
        int value = i[index - 1] - 2;
        if (value > 0 && !intArrayContains(value, i)) {
            //used[index] = value;
            i[index] = value;
            p(i, ++index);
        }
    }

    private static void minus7(int[] i, int index) {
        int value = i[index - 1] - 7;
        if (value > 0 && !intArrayContains(value, i)) {
            //used[index] = value;
            i[index] = value;
            p(i, ++index);
        }
    }

    private static boolean intArrayContains(int element, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) return true;
        }
        return false;
    }


}

/*
if (2 + i <= n) {
                curr[index] = i + 2;
                index++;
                perm(curr[index]);

            }
 */

  /* private static void perm(int i) {
        if (index == n) {
            permAnzahl++;
            System.out.println(Arrays.toString(curr));
        }else {

        }
    }*/