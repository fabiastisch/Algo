package U8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Fabian Weber , Robin Schlund, Julian Beitinger
 * 202224       , 202961       , 202387
 */

public class QuadSort {
    static int ausgabenZahl = 0;
    static int[] max;
    static boolean threeInOne = false;
    static ArrayList<String> list;
    static ArrayList<int[]> intList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        System.out.println("Geben Sie Die Maximalen Literangaben für die Kanister A,B,C,D ein. Trennen Sie mit \",\" " +
                "\n bsp: 4,1,3,1");
        s = scanner.nextLine().trim();

        int prev = 0;
        int[] a = new int[4];
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',') {
                a[x] = Integer.parseInt(s.substring(prev, i));
                x++;
                prev = i + 1;
            }
            if (i == s.length() - 1) {
                a[x] = Integer.parseInt(s.substring(prev, i + 1));
                x++;
            }
        }
        max = a.clone();
        list = new ArrayList<>();
        intList = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            a[i] = 0;
        }

        System.out.println(Arrays.toString(max));

        System.out.println("\n\n QuadSort ergibt: \n");

        fill(a);



        intList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] o) {


             /*   int a = 0, b = 0;
                for (int i = 0; i < ints.length; i++){
                    a += Math.pow(i,10) * ints[i];
                    b += Math.pow(i,10) * o[i];

                }
                return Integer.compare(a, b);*/

                int x4 = Integer.compare(ints[3], o[3]);

                if (x4 != 0) return x4;

                int x3 = Integer.compare(ints[2], o[2]);
                if (x3 != 0) return x3;

                int x2 = Integer.compare(ints[1], o[1]);
                if (x2 != 0) return x2;

                int x1 = Integer.compare(ints[1],o[1]);
                if (x1 != 0) return x1;

                return 0;

            }
        });


        for (int[] i : intList) {
            System.out.println(Arrays.toString(i));
        }

        System.out.println("Es gab genau " + ausgabenZahl + " Quadrupel");


    }

    static void fill(int[] a) {
        list.add(Arrays.toString(a));
        intList.add(a);
        ausgabenZahl++;

        // 1 --> 2
        if (a[0] != 0 && a[1] < max[1]) {
            int[] b = a.clone();
            while (b[0] != 0 && b[1] < max[1]) {
                b[0]--;
                b[1]++;
            }
            if (!list.contains(Arrays.toString(b))) {
                fill(b);
            }

        }

        // 1 --> 3
        if (a[0] != 0 && a[2] < max[2]) {
            int[] b = a.clone();
            while (b[0] != 0 && b[2] < max[2]) {
                b[0]--;
                b[2]++;
            }
            if (!list.contains(Arrays.toString(b))) {
                fill(b);
            }
        }

        // 2 --> 4
        if (a[1] != 0 && a[3] < max[3]) {
            int[] b = a.clone();
            while (b[1] != 0 && b[3] < max[3]) {
                b[1]--;
                b[3]++;
            }
            if (!list.contains(Arrays.toString(b))) {
                fill(b);
            }
        }

        // 3 --> 4
        if (a[2] != 0 && a[3] < max[3]) {
            int[] b = a.clone();
            while (b[2] != 0 && b[3] < max[3]) {
                b[2]--;
                b[3]++;
            }
            if (!list.contains(Arrays.toString(b))) {
                fill(b);
            }

        }

        // 3 --> 1
        if (a[2] != 0 && a[1] < max[1] | !threeInOne) {
            int[] b = a.clone();
            while (b[2] != 0 && b[0] < max[0]) {
                b[2]--;
                b[0]++;
            }
            if (!list.contains(Arrays.toString(b))) {
                fill(b);
            }
        }


    }


}

