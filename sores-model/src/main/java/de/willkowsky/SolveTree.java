package de.willkowsky;

public class SolveTree {

    private SolveTree current;
    private Playground playground;
    private SolveTree parent;

    public SolveTree(SolveTree parent) {
        this.parent = parent;
        current = this;
    }

    public SolveTree getCurrentNode() {
        return current;
    }

    public SolveTree addNode(Playground playground) {
        current = new SolveTree(this);
        current.playground = playground;
        return current;
    }

    public boolean resolve() {
        return playground.resolve();
    }

    public void discard() {
        current = parent;
    }
}
