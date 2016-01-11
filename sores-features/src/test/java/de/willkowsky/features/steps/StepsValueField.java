package de.willkowsky.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.willkowsky.*;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unused")
public class StepsValueField {

    private Playground playground;
    private ValueField valueField;

    @Given("^aus einem Spielfeld das Feld Reihe (\\d+), Spalte (\\d+)$")
    public void aus_einem_Spielfeld_das_Feld_Reihe_Spalte(int row, int column) throws Throwable {
        playground = new Playground();
        playground.setDims(9, 9);
        valueField = playground.getField(row,column);
    }

    @When("^es nach seinem zugehörigen Gruppen gefragt wird,$")
    public void es_nach_seinem_zugehörigen_Gruppen_gefragt_wird() throws Throwable {
    }

    @Then("^dann ist es der (\\d+)\\. Block, die Zeile (\\d+) und die Spalte (\\d+)$")
    public void dann_ist_es_der_Block_die_Zeile_und_die_Spalte(int blockIndex, int rowIndex, int columnIndex) throws Throwable {
        List<ValueGroup> groups = valueField.getValueGroups();

        for (ValueGroup group : groups) {
            if (group instanceof Row) {
                assertThat(group.getIndex(), is(rowIndex));
            }
            if (group instanceof Column) {
                assertThat(group.getIndex(), is(columnIndex));
            }
            if (group instanceof Block) {
                assertThat(group.getIndex(), is(blockIndex));
            }
        }
    }

    @When("^das Feld (\\d+), (\\d+) den Wert (\\d+)$")
    public void das_Feld_den_Wert(int row, int column, int value) throws Throwable {
        playground.setValue(row, column, value);
    }

    @When("^das Feld (\\d+), (\\d+) den Wert (\\d+) hat$")
    public void das_Feld_den_Wert_hat(int row, int column, int value) throws Throwable {
        das_Feld_den_Wert(row, column, value);
    }

    @Then("^sind noch (\\d+) andere Werte als (\\d+),(\\d+),(\\d+) möglich$")
    public void sind_noch_andere_Werte_als_möglich(int arg1, int arg2, int arg3, int arg4) throws Throwable {
        List<Integer> possibleValues = valueField.getPossibleValues();

    }


}
