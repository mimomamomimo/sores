package de.willkowsky;

import java.util.*;

public class ValueGroup {

    private List<Integer> allValues = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private ValueField[] valueFields;
    private int index;

    public ValueGroup(ValueField[] valueFields, int index) {
        this.valueFields = valueFields;
        this.index = index;

        for(ValueField valueField : valueFields) {
            if(valueField != null) {
                valueField.registerGroup(this);
            }
        }
    }

    public void set(int value, int rowNum) {
        valueFields[rowNum - 1].setValue(value);
    }

    public boolean isValid() {
        Set<Integer> compareSet = new HashSet<>();

        for(ValueField value : valueFields) {
            if(value.getValue() != 0) {
                compareSet.add(value.getValue());
            }
        }

        return compareSet.size() == valueFields.length;
    }

    public int getValue(int rowNum) {
        return valueFields[rowNum - 1].getValue();
    }

    public int getIndex() {
        return index;
    }

    public List<Integer> getMissingValues() {

        List<Integer> missingValues = new ArrayList<>(allValues);

        for(ValueField valueField : valueFields) {
            int index = missingValues.indexOf(valueField.getValue());
            if(index != -1) {
                missingValues.remove(index);
            }
        }

        return missingValues;
    }

    public String toString() {
        String rowFormat = "|%d%d%d|%d%d%d|%d%d%d|\n";
        return String.format(rowFormat, getValue(1), getValue(2), getValue(3),
            getValue(4), getValue(5), getValue(6), getValue(7), getValue(8),
            getValue(9));
    }

    public boolean resolve(TreeNode startNode) {
        List<ValueField> fieldsWithZeroValue = getValueFieldsWithValue(0);

        if(fieldsWithZeroValue.size() == 0) {
            // fertig, alle felder sind mit validen Werten gefüllt und somit
            // das Spiel gelöst.
            System.out.println("keine weiteren nichtgefüllten Felder " +
                "gefunden, spiel ist damit gelöst");
            return true;
        }

        // try solving game by iterating all possible values in the
        // first found field with zero in it. create a new subtree and resolve
        // that subtree.
        boolean gameSolved = false;
        for(ValueField valueField : fieldsWithZeroValue) {
            List<Integer> collect = valueField.getPossibleValues();
            for(Integer integer : collect) {
                String nodeName =
                    valueField.getXIndex() + "_" + valueField.getYIndex() +
                        "_" + integer;
                TreeNode subTree = new TreeNode(
                    nodeName);
                valueField.setValue(integer);
                startNode.addChild(subTree);
                if(resolve(subTree)) {
                    gameSolved = true;
                    break;
                }
            }

            if(gameSolved) {
                break;
            }
        }

        return gameSolved;
    }

    public void resolveForPlanB() {
        List<ValueField> fieldsWithZeroValue = getValueFieldsWithValue(0);

        for(ValueField valueField : fieldsWithZeroValue) {
            valueField.resolveForPlanB();
        }
    }

    private List<ValueField> getValueFieldsWithValue(int i) {
        List<ValueField> valueFieldList = new ArrayList<>();
        for(ValueField valueField : valueFields) {
            if(valueField.getValue() == i) {
                valueFieldList.add(valueField);
            }
        }
        return valueFieldList;
    }

    public boolean hasResolvableValueFields() {
        List<ValueField> emptyFields = getValueFieldsWithValue(0);

        if(emptyFields.isEmpty()) {
            return false;
        }

        for(ValueField emptyField : emptyFields) {
            if(emptyField.getPossibleValues().size() == 1) {
                return true;
            }
        }

        return false;
    }

    public List<ValueField> getUnresolvedFields() {
        List<ValueField> unresolvedFields = new ArrayList<>();

        for(ValueField valueField : valueFields) {
            if(!valueField.isSolved()) {
                unresolvedFields.add(valueField);
            }
        }
        return unresolvedFields;
    }

    public boolean isInvalid() {
        for(ValueField valueField : valueFields) {
            if(valueField.isInvalid()) {
                return true;
            }
        }
        return false;
    }
}
