package U3;

import java.util.Scanner;

public class Geldwechsel {
    //                   Indices 0 1 2 3 4
    static int betrag[] = {1, 2, 5, 9, 11};
    static int n = betrag.length; // Anzahl Muenzen
    // # Wechselarten fuer Betrag g und Muenzen mit Indices <= i
    static long w[][]; // Tabelle

    static long w(int g, int i) { // Methode
        return g < 0 ? 0 :
                i == 0 ? (g % betrag[0] == 0 ? 1 : 0) :
                        w[g][i] != 0 ? w[g][i] :
                                (w[g][i] = w(g, i - 1) + w(g - betrag[i], i));
    }

    public static void main(String[] args) {
        //int g = Integer.parseInt (args[0]); // g lesen


        for (int g, i = 0; i < 20; i++) {
            g = (int) Math.pow(3, i);


            w = new long[g + 1][n]; // w dimensionieren
            System.out.println("Den Betrag " + g + " kann man auf " +
                    w(g, n-1 ) + " verschiedene Arten wecheln.");
        }
    }


}
