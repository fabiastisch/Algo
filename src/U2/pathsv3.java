package U2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class pathsv3 {
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

        BigInteger funktion = funktion(0, 0, 0);   //START
        BigInteger anzahlDerPfade = speicher.get("" + n + "|" + 0 );
        System.out.println(anzahlDerPfade);
        System.out.println("Anzah der Pfade: " + funktion);

        System.out.println("\n\n " + speicher);
    }





    public static BigInteger funktion(int x, int y, int dir){

        if (x == n && y == 0){
            return BigInteger.ONE;
        }






        return nachoben(x,y,dir).add(
               nachlinks(x,y,dir)).add(
               nachunten(x,y,dir));

    }

    private static BigInteger nachunten(int x, int y, int dir) {
        return funktion(x+1,y-1,-1);
    }

    private static BigInteger nachlinks(int x, int y, int dir) {
        return funktion(x-1,y,0);
    }

    private static BigInteger nachoben(int x, int y, int dir) {
        return funktion(x-1,y+1,1);
    }

}

