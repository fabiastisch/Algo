package U6;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DLXPentominoDLUX {
    static Column header;
    static int cnt = 0;
    static int n;
    static LinkedList<DLXNode> heads = new LinkedList<>();

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


            search(0);
            System.out.println(cnt);

        }
    }

    public static void createBoard(int[][] board) {
        final int COLS = board[0].length;
        final int ROWS = board.length;

        header = new Column("header");
        ArrayList<Column> columnNodes = new ArrayList<Column>();

        for (int i = 0; i < COLS; i++) {
            Column column = new Column(Integer.toString(i));
            columnNodes.add(column);
            header = (Column) header.hookRight(column);
        }
        header = header.R.C;

        for (int i = 0; i < ROWS; i++) {
            DLXNode prev = null;
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == 1) { // atm immer flase, da ein leeres Brett erstellt wird
                    Column col = columnNodes.get(j);
                    DLXNode newNode = new DLXNode(col);
                    if (prev == null)
                        prev = newNode;
                    col.U.hookDown(newNode);
                    prev = prev.hookRight(newNode);
                    col.size++;
                }
            }
        }

        header.size = COLS;

        //return headerNode;
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
