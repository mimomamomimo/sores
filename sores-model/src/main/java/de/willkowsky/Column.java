package de.willkowsky;

public class Column extends ValueGroup{

    public Column(ValueField[] fields, int index) {
        super(fields, index);
    }

    public String toString() {
        String format = "%d \n %d \n %d \n";
        return "\n" + String.format(format, getValue(1), getValue(2), getValue(3)) +
                String.format(format, getValue(4), getValue(5), getValue(6)) +
                String.format(format, getValue(7), getValue(8), getValue(9));
    }
}
