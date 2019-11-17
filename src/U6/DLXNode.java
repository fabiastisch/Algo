package U6;
/*

public class DLXNode {
    DLXNode L, R, U, D;
    Column C;

    public DLXNode() {
        L = R = U = D = this;
    }

    public DLXNode(Column c) {
        this();
        C = c;
    }

    // hooks node n1 `below` current node
    DLXNode hookDown(DLXNode n1) {
        assert (this.C == n1.C);
        n1.D = this.D;
        n1.D.U = n1;
        n1.U = this;
        this.D = n1;
        return n1;
    }

    // hooke a node n1 to the right of `this` node
    DLXNode hookRight(DLXNode n1) {
        n1.R = this.R;
        n1.R.L = n1;
        n1.L = this;
        this.R = n1;
        return n1;
    }

    void unlinkLR() {
        this.L.R = this.R;
        this.R.L = this.L;
    }

    void relinkLR() {
        this.L.R = this.R.L = this;
    }

    void unlinkUD() {
        this.U.D = this.D;
        this.D.U = this.U;
    }

    void relinkUD() {
        this.U.D = this.D.U = this;
    }
}
*/
