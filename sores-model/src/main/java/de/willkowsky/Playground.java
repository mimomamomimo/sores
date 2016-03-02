package de.willkowsky;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Playground {

    private static final Log LOG = LogFactory.getLog(Playground.class);

    private int numValuesOfRow = 3;
    private int numValuesOfCols = 3;

    private ValueField[][] valueFields;
    private Block[] blocks;
    private Row[] rows;

    public Playground() {

    }

    public Playground(File file) throws IOException {
        initFields(file);
    }

    public void setDims(int rows, int columns) {
        initValueFields(rows, columns);
        initValueGroups();
    }

    protected void initValueGroups() {
        initRows();
        initColumns();
        initBlocks();
    }

    private void initBlocks() {
        int numberOfBlocks = 9;
        blocks = new Block[numberOfBlocks];
        for(int blockIndex = 0; blockIndex < numberOfBlocks; blockIndex++) {
            ValueField[] fields =
                new ValueField[numValuesOfRow * numValuesOfCols];
            for(int fieldNumber = 0; fieldNumber < fields.length;
                fieldNumber++) {
                int x = getXIndex(blockIndex, fieldNumber);
                int y = getYIndex(blockIndex, fieldNumber);
                fields[fieldNumber] = valueFields[y][x];
            }
            blocks[blockIndex] = new Block(fields, blockIndex);
        }
    }

    protected int getYIndex(int blockNumber, int fieldNumber) {
        int remainderBlockNumber = blockNumber % numValuesOfRow;
        int remainderFieldNumber = fieldNumber % numValuesOfRow;
        return ((blockNumber - remainderBlockNumber)) +
            (fieldNumber - remainderFieldNumber) / numValuesOfRow;
    }

    protected int getXIndex(int blockNumber, int fieldNumber) {
        return (fieldNumber % numValuesOfRow) +
            ((blockNumber % numValuesOfRow) * numValuesOfCols);
    }

    private void initColumns() {
        Column[] columns = new Column[valueFields[0].length];
        for(int colIndex = 0; colIndex < columns.length; colIndex++) {
            ValueField[] fields = new ValueField[valueFields[0].length];
            for(int rowIndex = 0; rowIndex < fields.length; rowIndex++) {
                fields[rowIndex] = valueFields[rowIndex][colIndex];
            }
            columns[colIndex] = new Column(fields, colIndex);
        }
    }

    private void initRows() {
        rows = new Row[valueFields.length];
        for(int index = 0; index < valueFields.length; index++) {
            rows[index] = new Row(valueFields[index], index);
        }
    }

    private void initValueFields(int rows, int columns) {
        valueFields = new ValueField[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int y = 0; y < columns; y++) {
                valueFields[i][y] = new ValueField(i, y);
            }
        }
    }

    public void setValue(int row, int column, int value) {
        valueFields[row][column].setValue(value);
    }

    public Integer get(int row, int column) {
        return valueFields[row][column].getValue();
    }

    public Block getBlock(int blockNumber) {
        return blocks[blockNumber - 1];
    }

    public String toString() {
        String line = "------------\n";
        StringBuilder builder = new StringBuilder("\n");

        for(ValueGroup row : rows) {
            if(row.getIndex() % numValuesOfCols == 0) {
                builder.append(line);
            }
            builder.append(row.toString());
        }

        builder.append(line);

        return builder.toString();
    }

    public ValueField getField(int row, int column) {
        return valueFields[row][column];
    }

    public void resolve() {
        TreeNode rootNode = new TreeNode("root");
        for(Block block : blocks) {
            block.resolve(rootNode);
        }
    }

    public void resolveWithPlanB() {
        TreeNode rootNode = new TreeNode("root");
        for(Block block : blocks) {
            block.resolveForPlanB(tNode);
        }

        if(hasResolvableValueFields()) {
            resolve();
        }

        // planb Lösung nicht mehr nötig, all Felder sind gelöst
        else {
            resolveViaPLanB();
        }
    }

    private boolean hasResolvableValueFields() {
        for(Block block : blocks) {
            if(block.hasResolvableValueFields()) {
                return true;
            }
        }
        return false;
    }

    private void resolveViaPLanB() {
        List<ValueField> unresolvedFields = new ArrayList<>();

        for(Block block : blocks) {
            unresolvedFields.addAll(block.getUnresolvedFields());
        }

        Comparator<? super ValueField> comp = new Comparator<ValueField>() {
            public int compare(ValueField o1, ValueField o2) {
                return Integer.valueOf(o1.getPossibleValues().size())
                    .compareTo(o2.getPossibleValues().size());
            }
        };

        Collections.sort(unresolvedFields, comp);

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
                        setValueFieldsAsDeepCopy(
                            planBPlayground.getValueFields());
                        initValueGroups();

                        resolve();
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

            unresolvedField.resolve(null);
        }
    }

    private boolean isInvalid() {
        for(Block block : blocks) {
            if(block.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private Playground getPlanBPlayground() {
        Playground planB = new Playground();

        planB.setValueFieldsAsDeepCopy(valueFields);
        planB.initValueGroups();

        return planB;
    }

    public boolean isValid() {
        boolean result = true;

        for(Block block : blocks) {
            result = result && block.isValid();
        }

        return result;
    }

    public ValueField[][] getValueFields() {
        return valueFields.clone();
    }

    public void setValueFieldsAsDeepCopy(ValueField[][] valueFields) {

        this.valueFields =
            new ValueField[valueFields.length][valueFields[0].length];

        for(int x = 0; x < valueFields.length; x++) {
            for(int y = 0; y < valueFields[0].length; y++) {
                this.valueFields[x][y] =
                    new ValueField(x, y, valueFields[x][y].getValue());
            }
        }

    }

    private void initFields(File file) throws IOException {
        List<String> strings = FileUtils.readLines(file);
        setDims(9, 9);
        for(String string : strings) {
            for(int i = 0; i < string.length(); i++) {
                int row = strings.indexOf(string);
                int value = Integer.parseInt(String.valueOf(string.charAt(i)));
                setValue(row, i, value);
            }
        }
    }

}
