package U4;

import java.math.BigInteger;
import java.util.*;

/**
 * Betrachten Sie wieder die Aufgabe 1 mit den Gitter-Pfaden vom zweiten Ubungsblatt. Schreiben Sie ¨
 * ein Programm PathNodes, das eine naturliche Zahl ¨ n einliest und dann die Gesamtanzahl aller
 * Punkte summiert uber s ¨ ¨amtliche Pfade von (0, 0) nach (n, 0) effizient bestimmt und ausgibt.
 * Hinweis: Die Anzahl dieser Punkte fur ¨ n = 4 ist 28, fur ¨ n = 5 ist sie 152.
 */
public class PathNodes {
    static int n, i, summ;
    static HashMap<String,BigInteger[]> memory = new HashMap<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                n = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                n = -1;
                System.err.println("NumberFormatExeption");
            }

            for (int i = 0; i < n; i++) {

                BigInteger[] solution = nodes(n, 0, 0);
                System.out.println("Für n = " + i + "\n Die Anzahl der verschiedenen Pfade betragen: \n\t " + solution[0]);
                System.out.println("Die Anzahl der summierten Punkte über sämtliche Pfade beträgt: \n\t " + solution[1]);


            }
            System.out.println("\n\n\n END OF FILE");



            /* Set<String> e;
            Iterator it = memory.keySet().iterator();
           for (BigInteger[] el : memory.values()){
               System.out.println(it.next());
               System.out.println(Arrays.toString(el));

           }*/



        }while (n!=-1);
    }

    static BigInteger[] nodes(int x, int y, int t){

        if (x < 0 || y < 0 || y > x) { // Sollte nie true sein,  TODO: kann evtl. gelöscht werden
            System.err.println("Fehler \t"+ + ++i  + "\t" + ""+x+"|"+y+"|"+t);

            return new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO};
        }
        if (x == 0 && y == 0) {
            return new BigInteger[]{BigInteger.ONE, BigInteger.ONE};
        }
        String s = ""+x+"|"+y+"|"+t;
        if (memory.containsKey(s))return memory.get(s);

        BigInteger paths = BigInteger.ZERO;
        BigInteger nodes = BigInteger.ZERO;



        if (y - x < 0) {
            BigInteger[] left = nodes(x - 1, y, 1);
           paths = paths.add(left[0]);  // nach links
           nodes = nodes.add(left[1]);
        }
        if (t != 1 && x-y > 1){

            BigInteger[] leftTop =nodes(x-1,y+1,2);
            paths = paths.add(leftTop[0]); // Nach links oben
            nodes = nodes.add(leftTop[1]);
        }
        if ((t == 1 || t == 3) && y != 0) {

            BigInteger[] rightBot = nodes(x+1,y-1,3);
            paths = paths.add(rightBot[0]); // Nach rechts unten
            nodes = nodes.add(rightBot[1]);
        }

        nodes = nodes.add(paths);


        memory.put(s, new BigInteger[]{paths, nodes});


        return new BigInteger[]{paths, nodes};
    }

}
