package U4;

import java.util.Arrays;
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
    static int n;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                n = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                n = -1;
                System.err.println("NumberFormatExeption");
            }
            Perm p = new Perm(n);
            int[]c;

            while ((c = p.getNext()) != null){
                System.out.println(arrStr(c));
            }



        }while (n!=-1);
    }

    static String arrStr(int[] c){
        /*String re = "[";

        for (int i =0; i <c.length; i++){
            re += c[i] +",";
        }
        re += "]";
        return re;*/
        return Arrays.toString(c);
    }



}
