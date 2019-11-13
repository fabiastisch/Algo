package U5;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class KorrektGeklammertenKlammerausdruecke {
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

            System.out.println(b(n, new Stack<>(), new Klammerart[2]));
        }
    }

    public static int b(int n, Stack<Klammerart> stack, Klammerart[] used) {
        if (inSpeicher(n, stack)) {
            return map.get("" + n + "|" + stack);
        }
        if (n == 0 && stack.isEmpty()) {
            return 1;
        }
        if (n >= 0) {
            int erg = 0;

            if (n > 0) {
                erg += push(Klammerart.RUND_AUF, (Stack<Klammerart>) stack.clone(), used.clone(), n);


                if (isUseable(Klammerart.ECKIG_AUF, used)) {
                    // System.out.println("Eckig");
                    erg += push(Klammerart.ECKIG_AUF, (Stack<Klammerart>) stack.clone(), used.clone(), n);
                }
                if (isUseable(Klammerart.GESCHWEIFT_AUF, used)) {
                    //System.out.println("Rund");
                    erg += push(Klammerart.GESCHWEIFT_AUF, (Stack<Klammerart>) stack.clone(), used.clone(), n);
                }
            }
            if (gehtZu(Klammerart.RUND_AUF, stack)) {
                Stack<Klammerart> a = ((Stack<Klammerart>) stack.clone());
                a.pop();
                erg += b(n, a, used);
            }
            if (gehtZu(Klammerart.ECKIG_AUF, stack)) {
                Stack<Klammerart> a = ((Stack<Klammerart>) stack.clone());
                a.pop();
                erg += b(n, a, used);
            }
            if (gehtZu(Klammerart.GESCHWEIFT_AUF, stack)) {
                Stack<Klammerart> a = ((Stack<Klammerart>) stack.clone());
                a.pop();
                erg += b(n, a, used);
            }

            map.put("" + n + "|" + stack, erg);
            return erg;
        }
        return 0;
    }

    private static boolean inSpeicher(int n, Stack<Klammerart> stack) {
        return map.containsKey("" + n + "|" + stack);
    }

    public static boolean gehtZu(Klammerart klammerart, Stack<Klammerart> stack) {
        if (stack.isEmpty()) return false;
        Klammerart k = stack.pop();
        stack.push(k);
        return k == klammerart;
    }

    public static boolean isUseable(Klammerart klammerart, Klammerart[] used) {
        if (klammerart == Klammerart.RUND_AUF) return true;
        else if (klammerart == Klammerart.ECKIG_AUF) return used[0] == Klammerart.RUND_AUF;
        else if (klammerart == Klammerart.GESCHWEIFT_AUF) return used[1] == Klammerart.ECKIG_AUF;


        return false;
    }

    public static int push(Klammerart klammerart, Stack<Klammerart> stack, Klammerart[] used, int n) {

        stack.push(klammerart);

        if (klammerart == Klammerart.RUND_AUF) used[0] = Klammerart.RUND_AUF;
        else if (klammerart == Klammerart.ECKIG_AUF) used[1] = Klammerart.ECKIG_AUF;

        return b(n - 1, stack, used);
    }

    enum Klammerart {
        RUND_AUF,
        ECKIG_AUF,
        GESCHWEIFT_AUF,
        RUND_ZU,
        ECKIG_ZU,
        GESCHWEIFT_ZU;
    }

}
