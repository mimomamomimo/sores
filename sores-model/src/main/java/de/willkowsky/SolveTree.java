package de.willkowsky;

public class SolveTree {
    private final String nodename;
    private SolveTree currentSolveTree;
    private Playground playground;
    private SolveTree parent;

    public SolveTree(String nodename, SolveTree parent) {
        this.parent = parent;
        this.nodename = nodename;
        currentSolveTree = this;
    }

    public SolveTree getCurrentNode() {
        return currentSolveTree;
    }

    public SolveTree addNode(String nodename, Playground playground) {
        currentSolveTree = new SolveTree(nodename, this);
        currentSolveTree.playground = playground;
        return currentSolveTree;
    }

    public boolean resolve(ResolveStrategy resolveStrategy) {
        return playground.resolve(resolveStrategy);
    }

    public void discard() {
        currentSolveTree = parent;
    }
}
