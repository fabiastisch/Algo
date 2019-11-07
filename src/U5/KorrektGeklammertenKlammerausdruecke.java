package U5;

import java.util.Scanner;
import java.util.Stack;

public class KorrektGeklammertenKlammerausdruecke {
    static int n;
    static Stack<Klammerart> stack;
    static Klammerart[] used = new Klammerart[3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Geben Sie eine positive Ganzzahl ein ... (Sinnvoll größer 7)");
            try {
                n = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                n = -1;
                System.err.println("NumberFormatExeption");
            }
            System.out.println(b(n));
        }
    }

    enum Klammerart {
        RUND,
        ECKIG,
        GESCHWEIFT;
    }


    public static int b(int n) {
        push(Klammerart.RUND);


        return 0;
    }


    public static void push(Klammerart klammerart) {
        stack.push(klammerart);
        boolean istdrin = false;
        for (Klammerart e : used) {
            if (e.equals(klammerart)) {
                istdrin = true;
                break;
            }
        }
        if (!istdrin) {
            for (int i = 0; i <= used.length - 1; i++) {
                if (used[i] == null) {
                    used[i] = klammerart;
                    break;
                }
            }
        }
    }


}


