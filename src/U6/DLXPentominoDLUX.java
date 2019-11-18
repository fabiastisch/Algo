package U6;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Fabian Weber , Robin Schlund, Julian Beitinger
 * 202224       , 202961       , 202387
 */

/*
für N= ...  = ...

0 = 1
1 = 0
2 = 33
3 = 164
4 = 7078
5 = 95945
6 = 2182916
7 = 33716384


 */


public class DLXPentominoDLUX {
    int n = 0;
    int col;
    int[][] mat;


    public DLXPentominoDLUX(int n) {
        this.n = n;
        this.col = n * 7;
        this.mat = createMatix();

        printArray(mat);

        DLX.createBoard(mat);
        System.out.println("Board fertig");
        DLX.search(0);

        System.out.println(DLX.cnt);
    }

    private int[][] createMatix() {

        int[][] d = getDomino();
        int[][] l = getL();
        int[][] u = getU();
        int[][] x = getX();


        int[][] aa = matrixAdd(matrixAdd(d, l), matrixAdd(u, x));
        //printArray(aa);
        return aa;
    }

    private int[][] getX() { // liefert das Exact Cover problem für das Feld n*7, nur mit X-Pentominos zu füllen
        int[][] a = new int[0][7];
        if (n >= 3) {
            int[][] x = {
                    //21
                    {0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0},
            };
            a = new int[n * 5 - 10][col];
            int c;

            for (int i = 0; i < n * 5 - 10; i++) {
                for (int col = 0; col < this.col; col++) {
                    c = col;

                    if (col >= (i / 5) * 7 && col < (i / 5 + 3) * 7) {

                        if (i >= 5) {
                            int g = i / 5;
                            c = col - 7 * g;
                            if (c < 0) {
                                c = 0;
                            }
                        }
                        a[i][col] = x[i % 5][c % 21];
                    }
                }
            }
            //  System.out.println("X:\n");
            //  printArray(a);
            //System.out.println(a.length); == n*5
        }
        return a;


    }

    private int[][] getU() {// liefert das Exact Cover problem für das Feld n*7, nur mit U-Pentominos zu füllen
        int[][] u = {
                {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0},
        };
        int[][] uXGespiegelt = {
                {1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1},
        };
        int[][] uImUhrzeigersinn = {
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
        };
        int[][] uGegenUhrzeigersinn = {
                {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
        };

        int[][] a = new int[n * 5 - 5][col];
        int c;
        for (int i = 0; i < n * 5 - 5; i++) { // U
            for (int col = 0; col < this.col; col++) {
                c = col;
                if (col >= (i / 5) * 7 && col < (i / 5 + 2) * 7) {
                    if (i >= 5) {
                        int g = i / 5;
                        c = col - 7 * g;
                        if (c < 0) {
                            c = 0;
                        }
                    }
                    a[i][col] = u[i % 5][c % 14];
                }
            }
        }
        // System.out.println("U:\n");
        // printArray(a);
        int[][] ab = new int[n * 5 - 5][col];
        int cd;
        for (int i = 0; i < n * 5 - 5; i++) { // U
            for (int col = 0; col < this.col; col++) {
                cd = col;
                if (col >= (i / 5) * 7 && col < (i / 5 + 2) * 7) {
                    if (i >= 5) {
                        int g = i / 5;
                        cd = col - 7 * g;
                        if (cd < 0) {
                            cd = 0;
                        }
                    }
                    ab[i][col] = uXGespiegelt[i % 5][cd % 14];
                }
            }
        }
        //System.out.println("UxGesp:\n");
        //  printArray(ab);
        int[][] abc = new int[0][7];
        int[][] abcd = new int[0][7];
        if (n >= 3) {
            abc = new int[n * 6 - 12][col];
            int cde;
            for (int i = 0; i < n * 6 - 12; i++) { // U
                for (int col = 0; col < this.col; col++) {
                    cde = col;
                    if (col >= (i / 6) * 7 && col < (i / 6 + 3) * 7) {
                        if (i >= 6) {
                            int g = i / 6;
                            cde = col - 7 * g;
                            if (cde < 0) {
                                cde = 0;
                            }
                        }
                        abc[i][col] = uImUhrzeigersinn[i % 6][cde % 21];
                    }
                }
            }
            //   System.out.println("UImUhrezeigersinn:\n");
            //   printArray(abc);

            abcd = new int[n * 6 - 12][col];
            int cdef;
            for (int i = 0; i < n * 6 - 12; i++) { // U
                for (int col = 0; col < this.col; col++) {
                    cdef = col;
                    if (col >= (i / 6) * 7 && col < (i / 6 + 3) * 7) {
                        if (i >= 6) {
                            int g = i / 6;
                            cdef = col - 7 * g;
                            if (cdef < 0) {
                                cdef = 0;
                            }
                        }
                        abcd[i][col] = uGegenUhrzeigersinn[i % 6][cdef % 21];
                    }
                }
            }
        }
        //  System.out.println("UgegenUhrzeigersinn:\n");
        //  printArray(abcd);
        int[][] l = matrixAdd(matrixAdd(a, ab), matrixAdd(abc, abcd));
        //printArray(l);

        return l;
    }

    private int[][] getL() { // liefert das Exact Cover problem für das Feld n*7, nur mit L-Pentominos zu füllen
        //  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        //   1,2,3,4,5,6,7,8,9,1,1,2,3,4,5,6,7,8,9,2,1,2,3,4,5,6,7,8
        //  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        int[][] l = {

                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
        };
        int[][] lYGespiegelt = {
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
        };
        int[][] lXGespiegelt = {
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
        };
        int[][] lXYGespiegelt = {

                {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
        };
        int[][] lImUhrzeigersinn = {
                {1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0},
        };
        int[][] lImUhrzeigersinnXGespiegelt = {
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
        };
        int[][] lGegenUhrzeigersinn = {
                {0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1},
        };
        int[][] lGegenUhrzeigersinnXGespiegelt = {
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
        };
        int[][] erg = new int[0][7];
        if (n >= 4) {
            int[][] a = new int[n * 6 - 18][col];
            int c;
            for (int i = 0; i < n * 6 - 18; i++) {
                for (int col = 0; col < this.col; col++) {
                    c = col;
                    if (col >= (i / 6) * 7 && col < (i / 6 + 4) * 7) {
                        if (i >= 6) {
                            int g = i / 6;
                            c = col - 7 * g;
                            if (c < 0) {
                                c = 0;
                            }
                        }
                        a[i][col] = l[i % 6][c % 28];
                    }
                }
            }
            //   System.out.println("L:\n");
            //   printArray(a);
            int[][] ab = new int[n * 6 - 18][col];
            int cd;
            for (int i = 0; i < n * 6 - 18; i++) {
                for (int col = 0; col < this.col; col++) {
                    cd = col;
                    if (col >= (i / 6) * 7 && col < (i / 6 + 4) * 7) {
                        if (i >= 6) {
                            int g = i / 6;
                            cd = col - 7 * g;
                            if (cd < 0) {
                                cd = 0;
                            }
                        }
                        ab[i][col] = lYGespiegelt[i % 6][cd % 28];
                    }
                }
            }
            //   System.out.println("LyGesp:\n");
            //  printArray(ab);
            int[][] abc = new int[n * 6 - 18][col];
            int cde;
            for (int i = 0; i < n * 6 - 18; i++) {
                for (int col = 0; col < this.col; col++) {
                    cde = col;
                    if (col >= (i / 6) * 7 && col < (i / 6 + 4) * 7) {
                        if (i >= 6) {
                            int g = i / 6;
                            cde = col - 7 * g;
                            if (cde < 0) {
                                cde = 0;
                            }
                        }
                        abc[i][col] = lXGespiegelt[i % 6][cde % 28];
                    }
                }
            }
            //  System.out.println("LXgesp:\n");
            // printArray(abc);
            int[][] abcd = new int[n * 6 - 18][col];
            int cdef;
            for (int i = 0; i < n * 6 - 18; i++) {
                for (int col = 0; col < this.col; col++) {
                    cdef = col;
                    if (col >= (i / 6) * 7 && col < (i / 6 + 4) * 7) {
                        if (i >= 6) {
                            int g = i / 6;
                            cdef = col - 7 * g;
                            if (cdef < 0) {
                                cdef = 0;
                            }
                        }
                        abcd[i][col] = lXYGespiegelt[i % 6][cdef % 28];
                    }
                }
            }
            //   System.out.println("LXYGesp:\n");
            //    printArray(abcd);

            erg = matrixAdd(matrixAdd(a, ab), matrixAdd(abc, abcd));
        } // N > 4 !!!! darunter frei
        int[][] bd = new int[n * 4 - 4][col];
        int ca;
        for (int i = 0; i < n * 4 - 4; i++) {
            for (int col = 0; col < this.col; col++) {
                ca = col;
                if (col >= (i / 4) * 7 && col < (i / 4 + 2) * 7) {
                    if (i >= 4) {
                        int g = i / 4;
                        ca = col - 7 * g;
                        if (ca < 0) {
                            ca = 0;
                        }
                    }
                    bd[i][col] = lImUhrzeigersinn[i % 4][ca % 14];
                }
            }
        }
        //  System.out.println("LimUhrzeigersinn:\n");
        //  printArray(bd);
        int[][] bde = new int[n * 4 - 4][col];
        int cab;
        for (int i = 0; i < n * 4 - 4; i++) {
            for (int col = 0; col < this.col; col++) {
                cab = col;
                if (col >= (i / 4) * 7 && col < (i / 4 + 2) * 7) {
                    if (i >= 4) {
                        int g = i / 4;
                        cab = col - 7 * g;
                        if (cab < 0) {
                            cab = 0;
                        }
                    }
                    bde[i][col] = lImUhrzeigersinnXGespiegelt[i % 4][cab % 14];
                }
            }
        }
        // System.out.println("LimUhrzeigersinn Xgesp:\n");
        // printArray(bde);
        int[][] bdef = new int[n * 4 - 4][col];
        int ea;
        for (int i = 0; i < n * 4 - 4; i++) {
            for (int col = 0; col < this.col; col++) {
                ea = col;
                if (col >= (i / 4) * 7 && col < (i / 4 + 2) * 7) {
                    if (i >= 4) {
                        int g = i / 4;
                        ea = col - 7 * g;
                        if (ea < 0) {
                            ea = 0;
                        }
                    }
                    bdef[i][col] = lGegenUhrzeigersinn[i % 4][ea % 14];
                }
            }
        }
        //System.out.println("LgegenUhr:\n");
        // printArray(bdef);
        int[][] bdefg = new int[n * 4 - 4][col];
        int aas;
        for (int i = 0; i < n * 4 - 4; i++) {
            for (int col = 0; col < this.col; col++) {
                aas = col;
                if (col >= (i / 4) * 7 && col < (i / 4 + 2) * 7) {
                    if (i >= 4) {
                        int g = i / 4;
                        aas = col - 7 * g;
                        if (aas < 0) {
                            aas = 0;
                        }
                    }
                    bdefg[i][col] = lGegenUhrzeigersinnXGespiegelt[i % 4][aas % 14];
                }
            }
        }
        //System.out.println("LgegenUhr x gesp:\n");
        //printArray(bdefg);
        int[][] s = matrixAdd(matrixAdd(bd, bde), matrixAdd(bdef, bdefg));

        if (n >= 4) {
            erg = matrixAdd(erg, s);
        } else {
            erg = s;
        }

        //printArray(erg);

        return erg;
    }

    private int[][] getDomino() { // liefert das Exact Cover problem für das Feld n*7, nur mit Dominos zu füllen
        int[][] dominoW = {
                {1, 1, 0, 0, 0, 0, 0}, // TODO:
                {0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1}
        };
        int[][] dominoS = {
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1}
        };

        int m = 13 * n - 7;
        int[][] a = new int[m][col];

        for (int i = 0; i < n * 13 - 7; i++) {
            for (int col = 0; col < this.col; col++) {
                if (i < n * 6) {
                    if (col >= i / 6 * 7 && col < (i / 6 + 1) * 7) {
                        a[i][col] = dominoW[i % 6][col % 7];
                    }
                } else {
                    if (col >= ((i - n * 6) / 7) * 7 && col < ((i - n * 6) / 7 + 2) * 7) {
                        a[i][col] = dominoS[(i - n * 6) % 7][col % 14];
                    }
                }
            }
        }
        //  System.out.println("D:\n");
        //  printArray(a);

        return a;
    }

    private void printArray(int[][] array) { // Nur zum Testen...
        StringBuilder s = new StringBuilder();
        int cnt = 1;

        for (int i = 0; i < array.length; i++) {
            s.append(" " + cnt + "[");
            for (int j = 0; j < array[i].length; j++) {
                s.append(array[i][j]);
                if (j != array[i].length - 1) {
                    s.append(", ");
                }
            }
            s.append("]\n");
            cnt++;
        }
        System.out.println(s);
    }

    private int[][] matrixAdd(int[][] a, int[][] b) { // Setzt die int Arrays untereinander
        /*
        |20,21,22,23,24,25,26 |
        +
        |10,11,12,13,14,15,16 |
        =
        |20,21,22,23,24,25,26 |
        |10,11,12,13,14,15,16 |
         */
        if (a.length == 0 && b.length == 0) {
            return new int[0][0];
        }
        if (a.length == 0) {
            return b;
        }
        if (b.length == 0) {
            return a;
        }
        if (a.length != 0 && b.length != 0) {
            if (a[0].length == b[0].length) {
                int len = a.length + b.length;
                int[][] m = new int[len][a[0].length];
                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < a[0].length; j++) {
                        if (i < a.length) {
                            m[i][j] = a[i][j];
                        } else {
                            m[i][j] = b[i - a.length][j];
                        }
                    }
                }
                return m;
            }
        }
        return new int[0][0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("Geben Sie eine Zahl ein");
        try {
            n = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            n = -1;
            System.err.println("NumberFormatExeption");
        }
        if (n > 0) {
            new DLXPentominoDLUX(n);
        } else System.out.println(1);
    }
}

class DLXNode {
    DLXNode L, R, U, D;
    Column C;

    public DLXNode() {
        L = R = U = D = this;
    }

    public DLXNode(Column c) {
        this();
        C = c;
    }

    // Node unterhalb von dieser(this) verlinken
    DLXNode nodenachunten(DLXNode n) {
        n.D = this.D;
        n.D.U = n;
        n.U = this;
        this.D = n;
        return n;
    }

    // Node rechts von dieser(this) verlinkn
    DLXNode nodenachrechts(DLXNode n) {
        n.R = this.R;
        n.R.L = n;
        n.L = this;
        this.R = n;
        return n;
    }

    void unlinkLR() {
        this.R.L = this.L;
        this.L.R = this.R;
    }

    void relinkUD() {
        this.U.D = this.D.U = this;
    }

    void unlinkUD() {
        this.U.D = this.D;
        this.D.U = this.U;
    }
}

class Column extends DLXNode {
    String name;

    public Column(String n) {
        super();
        name = n;
        C = this;
    }

    void cover() {
        unlinkLR();
        for (DLXNode i = this.D; i != this; i = i.D) {
            for (DLXNode j = i.R; i != j; j = j.R) {
                j.unlinkUD();
            }
        }
    }

    void uncover() {
        for (DLXNode i = this.U; i != this; i = i.U) {
            for (DLXNode j = i.L; i != j; j = j.L) {
                j.relinkUD();
            }
        }
        this.L.R = this.R.L = this;
    }
}

class DLX {
    static Column header;
    static int cnt = 0;


    public static void createBoard(int[][] board) {
        int spalten = board[0].length;
        int reihen = board.length;


        header = new Column("header");
        ArrayList<Column> columnNodes = new ArrayList<Column>();

        for (int i = 0; i < spalten; i++) {
            Column column = new Column(Integer.toString(i));
            columnNodes.add(column);
            header = (Column) header.nodenachrechts(column);

        }
        header = header.R.C;

        for (int i = 0; i < reihen; i++) {
            DLXNode prev = null;
            for (int j = 0; j < spalten; j++) {
                if (board[i][j] == 1) { // Wenn in der Matrix eine 1 an dem Index ist, wird eine neue node erstellt.
                    Column col = columnNodes.get(j);
                    DLXNode newNode = new DLXNode(col);
                    if (prev == null)
                        prev = newNode;
                    col.U.nodenachunten(newNode);
                    prev = prev.nodenachrechts(newNode);

                }
            }
        }
    }

    public static void search(int k) { // finds & counts solutions

        if (header.R == header) {
            cnt++;
            return;
        }     // if empty: count & done
        Column c = (Column) header.R;
        // choose next column c

        c.cover();                     // remove c from columns

        for (DLXNode r = c.D; r != c; r = r.D) {  // forall rows with 1 in c

            for (DLXNode j = r.R; j != r; j = j.R) // forall 1-elements in row
                j.C.cover();                    // remove column
            search(k + 1);                    // recursion
            for (DLXNode j = r.L; j != r; j = j.L) // forall 1-elements in row
                j.C.uncover();                  // backtrack: un-remove
        }
        c.uncover();                        // un-remove c to columns
    }
}
