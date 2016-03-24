package de.willkowsky;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

// Die Brute Force Strategy löst ein Feld und ruft rekursiv sich selbst mit den
// verbleibenden Möglichkeiten wieder auf.
public class BruteForceStrategy implements ResolveStrategy {

    private static final Log LOG = LogFactory.getLog(BruteForceStrategy.class);

    private SolveNode solveNode = new SolveNode(SolveNode.rootNodeName, null, null);
    private Playground solvedPlayground;

    @Override
    public boolean resolve(Playground playground) {
        LOG.info("resolving playground " + playground);

        boolean solved = false;
        if (solveNode.getPlayground() == null && solveNode.getNodename().equals(SolveNode.rootNodeName)) {
            solveNode.setPlayground(playground);
        }

        if (playground.getUnresolvedFieldsByColumnAndRow().size() == 0) {
            return true;
        }

        for (ValueField field : playground.getUnresolvedFieldsByColumnAndRow()) {
            List<Integer> possibleValues = field.getPossibleValues();
            if (possibleValues.size() == 0) {
                // dieser Zweig ist nicht mehr lösbar, gehe zum elternzweig
                return false;
            }

            for (Integer possibleValue : possibleValues) {
                Playground petriDish = playground.copy();
                petriDish.setValue(field.getXIndex(), field.getYIndex(), possibleValue);
                String nodename = field.getXIndex() + "_" + field.getYIndex() + "_" + possibleValue;
                SolveNode node = solveNode.addNode(nodename, petriDish);
                LOG.info("added another treenode " + nodename + ", now trying to solve the new node");

                solved = node.resolve(this);

                if (!solved) {
                    LOG.info("with " + nodename + " the playground is no longer solveable");
                    field.addImpossibleValue(possibleValue);
                } else {
                    this.solvedPlayground = petriDish.copy();
                    playground.setValueFieldsAsDeepCopy(petriDish.getValueFields());
                    break;
                }
            }

            return solved;
        }
        return false;
    }

    public Playground getSolvedPlayground() {
        return solvedPlayground;
    }
}
