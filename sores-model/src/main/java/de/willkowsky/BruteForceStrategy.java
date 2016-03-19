package de.willkowsky;

import java.util.List;

public class BruteForceStrategy implements ResolveStrategy {

    private Playground playground;
    private SolveTree solveTree = new SolveTree(null);

    @Override
    public boolean resolve(Playground playground) {
        this.playground = playground;
        boolean solved = false;

        if(playground.getUnresolvedFields().size() == 0) {
            return true;
        }

        // Die Brute force strategy löst ein Feld und ruft rekursiv sich mit dem
        // verbleibenden Möglichkeiten selbst wieder auf.

        for(ValueField field : playground.getUnresolvedFields()) {

            List<Integer> possibleValues = field.getPossibleValues();

            if(possibleValues.size() == 1) {
                field.setValue(1);
            }

            for(Integer possibleValue : possibleValues) {
                Playground handsomeTryRabbit = playground.copy();
                handsomeTryRabbit.setValue(field.getXIndex(), field.getYIndex(),
                        possibleValue);
                SolveTree node = solveTree.getCurrentNode().addNode(handsomeTryRabbit);
                solved = node.resolve();
                if(!solved) {
                    field.addImpossibleValue(possibleValue);
                    return false;
                }

            }
        }

        return solved;
    }
}
