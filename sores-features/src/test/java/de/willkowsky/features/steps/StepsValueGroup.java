package de.willkowsky.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.willkowsky.ValueField;
import de.willkowsky.ValueGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unused")
public class StepsValueGroup {

    private static final Log LOG = LogFactory.getLog(StepsValueGroup.class);

    private ValueGroup valueGroup;
    private ValueField[] valueFields = new ValueField[9];

    @Given("^ist eine Wertegruppe$")
    public void ist_eine_Wertegruppe() throws Throwable {

        for (int i = 0; i < valueFields.length; i++) {
            valueFields[i] = new ValueField(i,i);
        }
        valueGroup = new ValueGroup(valueFields, 1);
    }

    @When("^sie mit den Ziffern (\\d+) bis (\\d+) befüllt ist,$")
    public void sie_mit_den_Ziffern_bis_befüllt_ist(int arg1, int arg2) throws Throwable {
        for (int i = arg1; i <= arg2 ; i++){
            valueGroup.set(i, i);
        }
    }

    @Then("^dann ist sie gültig$")
    public void dann_ist_sie_gültig() throws Throwable {
        Assert.assertTrue(String.format("Die ValueGroup sollte für valueGroup.isValid() true liefern (%s)", valueGroup), valueGroup.isValid());
    }

    @When("^sie mit den Ziffern (\\d+) und (\\d+) befüllt ist,$")
    public void sie_mit_den_Ziffern_und_befüllt_ist(int arg1, int arg2) throws Throwable {
        valueGroup.set(arg1, arg1);
        valueGroup.set(arg2, arg2);
    }

    @Then("^dann ist sie ungültig$")
    public void dann_ist_sie_ungültig() throws Throwable {
        Assert.assertFalse(String.format("Die ValueGroup sollte für valueGroup.isValid() false liefern (%s)", valueGroup), valueGroup.isValid());
    }

    @When("^sie ausser der (\\d+) mit den Ziffern von (\\d+) bis (\\d+) befüllt ist,$")
    public void sie_ausser_der_mit_den_Ziffern_von_bis_befüllt_ist(int expect, int fromIndex, int toIndex) throws Throwable {

        for (int i = fromIndex; i <= toIndex;i++) {
            if (i != expect) {
                valueGroup.set(i,i);
            }
        }

    }

    @Then("^dann ist (\\d+) der fehlende Wert$")
    public void dann_ist_der_fehlende_Wert(int arg1) throws Throwable {
        List<Integer> missingValues = valueGroup.getMissingValues();
        assertThat(missingValues.size(), is(1));
        assertThat(missingValues.get(0), is(5));
    }

}
