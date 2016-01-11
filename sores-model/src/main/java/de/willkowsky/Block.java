package de.willkowsky;

public class Block extends ValueGroup{

    public Block(ValueField[] values, int index) {
        super(values, index);
    }

    public String toString() {
        String format = "%d | %d | %d ";
        String builder = String.format("\n" + String.format(format, getValue(1), getValue(2), getValue(3))) +
                "\n" + String.format(format, getValue(4), getValue(5), getValue(6)) +
                "\n" + String.format(format, getValue(7), getValue(8), getValue(9));
        return builder;
    }
}
