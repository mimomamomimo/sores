package de.willkowsky;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SolveNode {
    private final String nodename;
    List<SolveNode> childNodes = new ArrayList<>();
    private Playground playground;
    private SolveNode parent;
    private boolean valid = true;
    private Objects nodeName;
    public static final String rootNodeName = "root";

    public SolveNode(String nodename, SolveNode parent, Playground playground) {
        this.parent = parent;
        this.nodename = nodename;
        this.playground = playground;
    }

    public SolveNode addNode(String nodename, Playground playground) {
        SolveNode solveNode = new SolveNode(nodename, this, playground);
        childNodes.add(solveNode);
        return solveNode;
    }

    public boolean resolve(ResolveStrategy resolveStrategy) {
        return playground.resolve(resolveStrategy);
    }

    public SolveNode discard() {
        this.valid = false;
        return parent;
    }

    public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }

    public String getNodename() {
        return nodename;
    }
}
