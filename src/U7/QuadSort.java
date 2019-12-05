package U7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuadSort {
    static int ausgabenZahl = 0;
    static int[] max;
    static boolean threeInOne = false;
    static ArrayList<String> list;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        System.out.println("Geben Sie Die Literangaben für A,B,C,D ein. Trennen Sie mit \",\" ");
        s = scanner.nextLine();

        int prev = 0;
        int[]a = new int[4];
        int x = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ','){
                a[x] = Integer.parseInt(s.substring(prev,i));
                x++;
                prev = i+1;
            }
            if (i == s.length()-1){
                a[x] = Integer.parseInt(s.substring(prev,i+1));
                x++;
            }
        }
        max = a.clone();
        list = new ArrayList<>();

        for (int i = 1; i < 4; i++){
            a[i] = 0;
        }

        System.out.println(Arrays.toString(max));

        System.out.println("QuadSort ergibt: \n");

        fill(a);

        for (String s1: list){
            System.out.println(s1);
        }
        System.out.println("Es gab genau "+ausgabenZahl + " Quadrupel");


    }

    static void fill(int[] a){
        list.add(Arrays.toString(a));
        ausgabenZahl++;

        // 1 -> 2
        if (a[0] != 0 && a[1] < max[1]) {
            int[] b = a.clone();
            while (b[0] != 0 && b[1] < max[1]){
                b[0]--;
                b[1]++;
            }
            if (!list.contains(Arrays.toString(b))){
                fill(b);
            }

        }

        // 1 -> 3
        if (a[0] != 0 && a[2] < max[2]){
            int[] b = a.clone();
            while (b[0] != 0 && b[2] < max[2]){
                b[0]--;
                b[2]++;
            }
            if (!list.contains(Arrays.toString(b))){
                fill(b);
            }
        }

        // 2 -> 4
        if (a[1] != 0 && a[3] < max[3]){
            int[] b = a.clone();
            while (b[1] != 0 && b[3] < max[3]){
                b[1]--;
                b[3]++;
            }
            if (!list.contains(Arrays.toString(b))){
                fill(b);
            }
        }

        // 3 -> 4
        if (a[2] != 0 && a[3] < max[3]){
            int[] b = a.clone();
            while (b[2] != 0 && b[3] < max[3]){
                b[2]--;
                b[3]++;
            }
            if (!list.contains(Arrays.toString(b))){
                fill(b);
            }

        }

        // 3 -> 1
        if (a[2] != 0 && a[1] < max[1] | !threeInOne){
            int[] b = a.clone();
            while (b[2] != 0 && b[0] < max[0]){
                b[2]--;
                b[0]++;
            }
            if (!list.contains(Arrays.toString(b))){
                fill(b);
            }
        }



    }



}

