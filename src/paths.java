import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class paths {

  static HashMap<String, BigInteger> speicher = new HashMap<>();

  public static void main(String[] args) {



      Scanner scanner = new Scanner(System.in);

      int n;

      try {
        n = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        n = 0;
        System.err.println("NumberFormatExeption");
      }



    funktion(n, 0, 0); // andersrum
    BigInteger anzahlDerPfade = speicher.get("" + n + "|" + 0);
    System.out.println(anzahlDerPfade);

    System.out.println(speicher);
   // System.out.println(speicher.size());

  }


  public static BigInteger funktion(int x, int y, int dir) {
    if (x == 0 && y == 0) {
      System.out.println("0");
      return BigInteger.ONE;
    }
    if (speicher.containsKey("" + x + "|" + y)) {
      System.out.println(x + "|" + y + " aus dem Speicher");
      return speicher.get("" + x + "|" + y);

    }


    if (!(x - 1 < 0) && (y <= x - 1) && dir != -1) { //nach Links?
      System.out.println("Kann nach links");
      BigInteger temp = BigInteger.ZERO;
      if (speicher.containsKey("" + x + "|" + y)) {
        temp = speicher.get("" + x + "|" + y);
      }
      speicher.put("" + x + "|" + y, (funktion(x - 1, y, 0).add(temp)));
    }

    if ((y + 1 < x - 1)) { // Links Oben?
      System.out.println("Kann nach Links Oben");
      BigInteger temp = BigInteger.ZERO;
      if (speicher.containsKey("" + x + "|" + y)) {
        temp = speicher.get("" + x + "|" + y);
      }
      speicher.put("" + x + "|" + y, (funktion(x - 1, y + 1, 1).add(temp)));
    }

    if (y - 1 >= 0 && dir != 1) { //Rechts Unten?
      System.out.println("Kann nach Rechts Unten");
      BigInteger temp = BigInteger.ZERO;
      if (speicher.containsKey("" + x + "|" + y)) {
        temp = speicher.get("" + x + "|" + y);
      }
      speicher.put("" + x + "|" + y, (funktion(x + 1, y - 1, -1).add(temp)));
    }


    return speicher.get("" + x + "|" + y);
  }


}

