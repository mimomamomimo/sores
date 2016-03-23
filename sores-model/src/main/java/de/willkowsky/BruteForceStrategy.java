package de.willkowsky;

import java.util.List;

// Die Brute Force Strategy löst ein Feld und ruft rekursiv sich selbst mit den
// verbleibenden Möglichkeiten wieder auf.
public class BruteForceStrategy implements ResolveStrategy {

    private Playground playground;
    private SolveNode solveNode =
            new SolveNode(SolveNode.rootNodeName, null, null);

    @Override
    public boolean resolve(Playground playground) {
        this.playground = playground;
        System.out.println("resolving playground " + playground);

        boolean solved = false;
        if(solveNode.getPlayground() == null && solveNode.getNodename().equals
                (SolveNode.rootNodeName)) {
            solveNode.setPlayground(playground);
        }

        if(playground.getUnresolvedFieldsByColumnAndRow().size() == 0) {
            return true;
        }

        for(ValueField field : playground.getUnresolvedFieldsByColumnAndRow()) {
            List<Integer> possibleValues = field.getPossibleValues();
            if(possibleValues.size() == 0) {
                // dieser Zweig ist nicht mehr lösbar, gehe zum elternzweig
                return false;
            }
            //possibleValues.stream().forEach(p -> System.out.println(p));

            for(Integer possibleValue : possibleValues) {
                Playground handsomeTryRabbitPlayground = playground.copy();
                handsomeTryRabbitPlayground
                        .setValue(field.getXIndex(), field.getYIndex(),
                                possibleValue);
                String nodename = field.getXIndex() + "_" + field.getYIndex()
                        + "_" + possibleValue;
                SolveNode node = solveNode
                        .addNode(nodename, handsomeTryRabbitPlayground);
                System.out.println("added another treenode " + nodename +
                        ", now trying to solve the new node");
                solved = node.resolve(this);
                if(!solved) {
                    System.out.println("with " + nodename +
                            " the playground is no longer solveable");
                    field.addImpossibleValue(possibleValue);
                } else {
                    break;
                }
            }

            break;
        }
        return solved;
    }
}
