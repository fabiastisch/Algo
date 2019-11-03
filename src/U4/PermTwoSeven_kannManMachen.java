package U4;

import java.util.Scanner;

public class PermTwoSeven_kannManMachen {

    private int arraySize;            //User Input = n
    private int amountOfPermutations; //Amount of permutations found for n
    private int[] usedNumbers;      //Contains all numbers from 1..n that have already been used
    private int[] possiblePermutation;//Current permutation

    private int currentIndex;     //Index for possiblePermutation[]


    public PermTwoSeven_kannManMachen() {
        amountOfPermutations = 0;
        currentIndex = 1;
    }

    public static void main(String[] args) {
        PermTwoSeven_kannManMachen pts = new PermTwoSeven_kannManMachen();
        pts.userInput();                            //Get n from User
        pts.checkNTimes();                     //Start permutating
    }

    private void checkNTimes() {
        for (int i = 1; i <= arraySize; i++) {      //Checks every beginning possible [1 ... n]
            possiblePermutation = new int[arraySize]; //Create new array, n big
            possiblePermutation[0] = i;               //Fill the first slot with i = 1 (starting value)
            usedNumbers = new int[arraySize];
            usedNumbers[0] = i;                       //Fill in the used starting number

            checkPermutations(i);
        }
        System.out.println("Es gab genau " + amountOfPermutations + " Permutationen");
    }

    private void checkPermutations(int i) {
        if (foundPermutation()) {
            amountOfPermutations++;
            printSolution(possiblePermutation);
        } else {
            check2Higher(i);
            check2Lower(i);
            check7Higher(i);
            check7Lower(i);
        }

    }

    // -------------------------    Permutation logic ------------------

    private void check2Higher(int i) {  //Checks i + 2
        if (i + 2 <= arraySize && !alreadyUsed(i + 2)) {
            possiblePermutation[currentIndex] = i + 2;
            usedNumbers[currentIndex] = i + 2;
            currentIndex++;
            checkPermutations(i + 2);
            currentIndex--;
            usedNumbers[currentIndex] = 0;
            possiblePermutation[currentIndex] = 0;
        }
    }

    private void check2Lower(int i) {
        if (i - 2 > 0 && !alreadyUsed(i - 2)) {
            possiblePermutation[currentIndex] = i - 2;
            usedNumbers[currentIndex] = i - 2;
            currentIndex++;
            checkPermutations(i - 2);
            currentIndex--;
            usedNumbers[currentIndex] = 0;
            possiblePermutation[currentIndex] = 0;
        }
    }

    private void check7Higher(int i) {
        if (i + 7 <= arraySize && !alreadyUsed(i + 7)) {
            possiblePermutation[currentIndex] = i + 7;
            usedNumbers[currentIndex] = i + 7;
            currentIndex++;
            checkPermutations(i + 7);
            currentIndex--;
            usedNumbers[currentIndex] = 0;
            possiblePermutation[currentIndex] = 0;
        }
    }

    private void check7Lower(int i) {
        if (i - 7 > 0 && !alreadyUsed(i - 7)) {
            possiblePermutation[currentIndex] = i - 7;
            usedNumbers[currentIndex] = i - 7;
            currentIndex++;
            checkPermutations(i - 7);
            currentIndex--;
            usedNumbers[currentIndex] = 0;
            possiblePermutation[currentIndex] = 0;
        }
    }

    private boolean alreadyUsed(int a) { //Takes the next number and check if it has been used before
        for (int i = 0; i < arraySize; i++) {
            if (a == usedNumbers[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean foundPermutation() {
        if (currentIndex == arraySize) {
            return true;
        }
        return false;
    }


    // ----------------------  User Input, print a permutation -----------------

    private void userInput() {
        Scanner sc = new Scanner(System.in);


        String s = "Bitte geben Sie eine ganze, positive Zahl ein, größer als 0";
        System.out.println(s);
        try {
            arraySize = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            arraySize = -1;
            System.err.println("NumberFormatExeption");
        }
    }

    private void printSolution(int[] a) {   //prints an array of a possible permutation
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.print("]");
        System.out.println();
    }
}
