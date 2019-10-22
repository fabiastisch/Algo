package U3;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class Muenzwechsel {
                        // 0   1  2  3  4
    static int coins [] = {1 , 2, 5, 9, 11};
    static int n = coins.length; // Anzahl der Münzen
    static int g;
    static HashMap<Double, BigInteger> speicher = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        do {


            try {
                g = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                g = 0;
                System.err.println("NumberFormatExeption");
            }

            System.out.println("Den Betrag " + g + " kann man auf " + w(g,n-1) + " verschiedene Arten wechseln.");

        }while (g !=0);

    }

    static BigInteger w(int g, int i){
        /*
        return g < 0 ? BigInteger.ZERO :
            i == 0 ? (g % coins[0] == 0 ? BigInteger.ONE : BigInteger.ZERO) :
                    speicher.containsKey(Double.valueOf(g +"."+ i))? speicher.get(Double.valueOf(g +"."+ i)) :

                            speicher.put(Double.valueOf(g +"."+ i), w(g,i-1).add(w(g-coins[i],i)));*/

        if (g < 0){
            return BigInteger.ZERO;
        }
        if (i == 0){
            if (g%coins[0] == 0){
                return BigInteger.ONE;
            }else return BigInteger.ZERO;
        }
        if (speicher.containsKey(Double.valueOf(g+"."+i))){
            return speicher.get(Double.valueOf(g+"."+i));
        }else {
           // BigInteger temp1 = w(g,i-1);
            speicher.put(Double.valueOf(g+"."+(i-1)),w(g,i-1));
            speicher.put(Double.valueOf((g-coins[i])+"."+i),w(g-coins[i],i));

            //BigInteger temp2 = w(g-coins[i],i);
          //  speicher.put(Double.valueOf(g+"."+i),temp1.add(temp2));
            speicher.put(Double.valueOf(g+"."+i),speicher.get(Double.valueOf(g+"."+(i-1))).add(speicher.get(Double.valueOf((g-coins[i])+"."+i))));
            return speicher.get(Double.valueOf(g+"."+i));
        }



    }

}
