package de.willkowsky;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class PlanBStrategy implements ResolveStrategy {

    private static final Log LOG = LogFactory.getLog(PlanBStrategy.class);
    private Playground playground;

    @Override
    public boolean resolve(Playground playground) {

        this.playground = playground;

        for(Block block : playground.getBlocks()) {
            block.resolve();
        }

        if(playground.hasResolvableValueFields()) {
            return resolve(playground);
        } else {
            resolveViaPLanB();
        }

        return false;
    }

    private Playground getPlanBPlayground() {
        Playground planB = new Playground();

        planB.setValueFieldsAsDeepCopy(playground.getValueFields());
        planB.initValueGroups();

        return planB;
    }

    private void resolveViaPLanB() {
        List<ValueField> unresolvedFields = playground.getUnresolvedFields();

        if(unresolvedFields.isEmpty()) {
            return;
        }

        for(ValueField unresolvedField : unresolvedFields) {
            List<Integer> possibleValues = unresolvedField.getPossibleValues();

            if(possibleValues.size() == 1) {
                unresolvedField.setValue(possibleValues.get(0));
                continue;
            }

            for(Integer possibleValue : possibleValues) {
                Playground planBPlayground = getPlanBPlayground();
                planBPlayground.setValue(unresolvedField.getXIndex(),
                        unresolvedField.getYIndex(), possibleValue);
                LOG.info(String.format("Versuche Feld %s mit Wert %d (%s",
                        unresolvedField.getXIndex() + "," +
                                unresolvedField.getYIndex(), possibleValue,
                        possibleValues.toString()));
                if(!planBPlayground.isInvalid()) {
                    LOG.info("PlanB scheint noch lösbar: \n" +
                            planBPlayground.toString());
                    planBPlayground.resolve();

                    if(planBPlayground.isValid()) {
                        playground.setValueFieldsAsDeepCopy(
                                planBPlayground.getValueFields());
                        playground.initValueGroups();

                        playground.resolve();
                        LOG.info("Fertig: \n" + this.toString());
                        return;
                    }
                } else {
                    LOG.info(String.format(
                            "Für Feld %s ist der Wert %d falsch (%s",
                            unresolvedField.getXIndex() + "," +
                                    unresolvedField.getYIndex(), possibleValue,
                            possibleValues.toString()));
                    unresolvedField.addImpossibleValue(possibleValue);
                }
            }

            unresolvedField.resolve();
        }
    }


}
