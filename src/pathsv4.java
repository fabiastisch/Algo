
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class pathsv4 {
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

        System.out.println(" for n = " + n + " ");
        n--;
        BigInteger funktion = funktion(0, 0, 0);   //START

        System.out.println("Anzah der Pfade: " + funktion);


    }





    public static BigInteger funktion(int x, int y, int dir){

        if (x == n && y == 0){
            return BigInteger.ONE;
        }






        return nachoben(x,y,dir).add(
                nachrechts(x,y,dir)).add(
                nachunten(x,y,dir));

    }

    private static BigInteger nachunten(int x, int y, int dir) {
        if (y > 0 && dir != 1) {
            return funktion(x + 1, y - 1, -1);
        }else return BigInteger.ZERO;
    }

    private static BigInteger nachrechts(int x, int y, int dir) {
        if (x + y < n && dir != -1) {
            if (speicher.containsKey(x+"|"+y)){
                return speicher.get(x+"|"+y);
            }else {
                speicher.put(x+"|"+y, funktion(x+1,y,0));
            }

            return speicher.get(x+"|"+y);
        }else return BigInteger.ZERO;
    }

    private static BigInteger nachoben(int x, int y, int dir) {
        if (x-y > 0) {
            if (speicher.containsKey(x+"|"+y)){
                return speicher.get(x+"|"+y);
            }else {
                return funktion(x - 1, y + 1, 1);
            }
        }else return BigInteger.ZERO;
    }

}

