package U5;

import java.util.HashMap;
import java.util.Scanner;

public class Klammer {
    static int n;
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Geben Sie eine Zahl ein");
            try {
                n = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                n = -1;
                System.err.println("NumberFormatExeption");
            }

            //System.out.println(b(n, new Stack<>(), new Klammerart[2]));
        }
    }

    static int b(int n) {

        return 0;
    }


}
