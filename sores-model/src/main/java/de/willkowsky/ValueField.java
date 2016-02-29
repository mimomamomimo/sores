package de.willkowsky;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValueField {

    private List<Integer> impossibleValues = new ArrayList<>();
    private int value;
    private List<ValueGroup> groups = new ArrayList<>();
    private int indexX;
    private int indexY;

    public ValueField(int indexX, int indexY) {
        this.indexX = indexX;
        this.indexY = indexY;
    }

    public ValueField(int indexX, int indexY, int value) {
        this.indexX = indexX;
        this.indexY = indexY;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void registerGroup(ValueGroup valueGroup) {
        groups.add(valueGroup);
    }

    public List<ValueGroup> getValueGroups() {
        return groups;
    }

    public List<Integer> getPossibleValues() {
        Set<Integer> possibleValues = new HashSet<>();

        for (ValueGroup valueGroup : groups) {
            possibleValues.addAll(valueGroup.getMissingValues());
        }

        for (ValueGroup valueGroup : groups) {
            possibleValues.retainAll(valueGroup.getMissingValues());
        }

        for (Integer impossibleValue : impossibleValues) {
            possibleValues.remove(impossibleValue);
        }

        return new ArrayList<>(possibleValues);
    }

    public void resolve(TreeNode treeNode) {
        if (getPossibleValues().size() == 1) {
            setValue(getPossibleValues().get(0));
        }
    }

    public boolean isSolved() {
        return getValue() != 0;
    }

    public int getXIndex() {
        return indexX;
    }

    public int getYIndex() {
        return indexY;
    }

    public void addImpossibleValue(Integer possibleValue) {
        impossibleValues.add(possibleValue);
    }

    public boolean isInvalid() {
        return getPossibleValues().isEmpty() && getValue() == 0;
    }
}
