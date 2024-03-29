package U6;

/**
 * Class DLXNode
 * represents a matrix element of the cover matrix with value 1
 * links go to up down left right neigbors, and column header
 * can also be used as colm header or root of column headers
 * matrix is sparsely coded
 * try to do all operations very efficiently
 * see:
 * http://en.wikipedia.org/wiki/Dancing_Links
 * http://arxiv.org/abs/cs/0011047
 */
class DLXNode_Heinz {       // represents 1 element or header
    DLXNode_Heinz C;           // reference to column-header
    DLXNode_Heinz L, R, U, D;  // left, right, up, down references


    DLXNode_Heinz() {
        C = L = R = U = D = this;
    } // supports circular lists


    /**
     * search tries to find and count all complete coverings of the DLX matrix.
     * Is a recursive, depth-first, backtracking algorithm that finds
     * all solutions to the exact cover problem encoded in the DLX matrix.
     * each time all columns are covered, static long cnt is increased
     *
     * @param int k: number of level
     */
   /* public static void search(int k) { // finds & counts solutions
        if (h.R == h) {
            cnt++;
            return;
        }     // if empty: count & done
        DLXNode_Heinz c = h.R;                   // choose next column c
        cover(c);                          // remove c from columns
        for (DLXNode_Heinz r = c.D; r != c; r = r.D) {  // forall rows with 1 in c
            for (DLXNode_Heinz j = r.R; j != r; j = j.R) // forall 1-elements in row
                cover(j.C);                    // remove column
            search(k + 1);                    // recursion
            for (DLXNode_Heinz j = r.L; j != r; j = j.L) // forall 1-elements in row
                uncover(j.C);                  // backtrack: un-remove
        }
        uncover(c);                        // un-remove c to columns
    }*/

    /**
     * cover "covers" a column c of the DLX matrix
     * column c will no longer be found in the column list
     * rows i with 1 element in column c will no longer be found
     * in other column lists than c
     * so column c and rows i are invisible after execution of cover
     *
     * @param //DLXNode c: header element of column that has to be covered
     */
    public static void cover(DLXNode_Heinz c) { // remove column c
        c.R.L = c.L;                         // remove header
        c.L.R = c.R;                         // .. from row list
        for (DLXNode_Heinz i = c.D; i != c; i = i.D)      // forall rows with 1
            for (DLXNode_Heinz j = i.R; i != j; j = j.R) {   // forall elem in row
                j.D.U = j.U;                     // remove row element
                j.U.D = j.D;                     // .. from column list
            }
    }

    /**
     * uncover "uncovers" a column c of the DLX matrix
     * all operations of cover are undone
     * so column c and rows i are visible again after execution of uncover
     *
     * @param //DLXNode c: header element of column that has to be uncovered
     */
    public static void uncover(DLXNode_Heinz c) {//undo remove col c
        for (DLXNode_Heinz i = c.U; i != c; i = i.U)      // forall rows with 1
            for (DLXNode_Heinz j = i.L; i != j; j = j.L) {   // forall elem in row
                j.D.U = j;                       // un-remove row elem
                j.U.D = j;                       // .. to column list
            }
        c.R.L = c;                           // un-remove header
        c.L.R = c;                           // .. to row list
    }
}