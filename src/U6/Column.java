package U6;

public class Column extends DLXNode {
    int size; // number of ones in current column
    String name;

    public Column(String n) {
        super();
        size = 0;
        name = n;
        C = this;
    }

    void cover() {
        unlinkLR();
        for (DLXNode i = this.D; i != this; i = i.D) {
            for (DLXNode j = i.R; j != i; j = j.R) {
                j.unlinkUD();
                j.C.size--;
            }
        }
        //header.size--; // not part of original
    }

    void uncover() {
        for (DLXNode i = this.U; i != this; i = i.U) {
            for (DLXNode j = i.L; j != i; j = j.L) {
                j.C.size++;
                j.relinkUD();
            }
        }
        relinkLR();
        // header.size++; // not part of original
    }
}