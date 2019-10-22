package U2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class pathsv2 {
    static HashMap<String, BigInteger> speicher = new HashMap<>();
    private static int n;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        try {
            n = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            n = 0;
            System.err.println("NumberFormatExeption");
        }

        //+ "|" + 0

        BigInteger funktion = funktion(n, 0, 0);// andersrum
        BigInteger anzahlDerPfade = speicher.get("" + n + "|" + 0 );
        System.out.println(anzahlDerPfade);
        System.out.println("Anzah der Pfade: " + funktion);

        System.out.println("\n\n " + speicher);
    }

    public static BigInteger funktion(int x, int y, int dir) {

        /*if (x == n && y == 0) {
            System.out.println(x+ "|" + y);
            return BigInteger.ONE;
        }*/


        return oben(x, y,0).add(unten(x, y,0)).add(links(x, y,0));
    }

    public static BigInteger links(int x, int y, int dir) {
        if (!(x - 1 < 0) && (y <= x - 1 ) && dir != 0) { // Links

            BigInteger temp;
            if (speicher.containsKey("" + x + "|" + y) && dir != 1) {
                temp = speicher.get("" + x + "|" + y);
                speicher.put("" + x + "|" + y, (funktion(x - 1, y, 0).add(temp)));
                return speicher.get("" + x + "|" + y);
            } else if (dir != 1){
                speicher.put("" + x + "|" + y, links(x-1,y,0).add(unten(x-1,y,0)));
                return speicher.get("" + x + "|" + y);
            } else if (dir == 1){
                return links(x-1,y,0).add(unten(x-1,y,0));
            }

        } else {
            System.out.println("Links: " +x+ "|" + y);
            return BigInteger.ZERO;
        }
        System.out.println("links ganz unten");
        return BigInteger.ZERO;


    }

    public static BigInteger oben(int x, int y, int dir) {
        if ((y + 1 < x - 1)) { // Links Oben?
            BigInteger temp;
            if (speicher.containsKey("" + x + "|" + y) && dir != 1) {
                temp = speicher.get("" + x + "|" + y);
                speicher.put("" + x + "|" + y, (funktion(x - 1, y + 1, 0).add(temp)));
                return speicher.get("" + x + "|" + y);
            } else if (dir != 1) {
                    speicher.put("" + x + "|" + y, links(x - 1, y + 1,1).add(oben(x - 1, y + 1,1)));
                    return speicher.get("" + x + "|" + y);
            } else {
                return links(x - 1, y + 1,1).add(oben(x - 1, y + 1,1));
            }
        } else {
            System.out.println("Oben: " +x+ "|" + y);
            return BigInteger.ZERO;
        }


    }

    public static BigInteger unten(int x, int y, int dir) {
        if (y - 1 >= 0 && dir != 1) { //Rechts Unten?
            //System.out.println("Kann nach Rechts Unten");
            BigInteger temp;
            if (speicher.containsKey("" + x + "|" + y)) {
                temp = speicher.get("" + x + "|" + y);

                speicher.put("" + x + "|" + y, (funktion(x + 1, y - 1, -1).add(temp)));
                return speicher.get("" + x + "|" + y);
            }else {
                speicher.put("" + x + "|" + y, oben(x + 1, y - 1,-1).add(unten(x + 1, y - 1,-1)));
                return speicher.get("" + x + "|" + y);
            }
        } else {
            System.out.println("Unten" +x+ "|" + y);
            return BigInteger.ZERO;


        }
    }


}
