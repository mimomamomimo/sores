package de.willkowsky.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.willkowsky.Block;
import de.willkowsky.Playground;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import java.io.*;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.core.Is.is;

@SuppressWarnings("unused")
public class StepsPlayground {

    private static final Log LOG = LogFactory.getLog(StepsPlayground.class);

    private Playground playground;

    @Given("^ist eine Spielfeld$")
    public void ist_eine_Spielfeld() throws Throwable {
        playground = new Playground();
    }

    @When("^es (\\d+) x (\\d+) Wertefelder hat$")
    public void es_x_Wertefelder_hat(int rows, int columns) throws Throwable {
        playground.setDims(rows, columns);
    }

    @Then("^dann kann das Wertefeld Zeile (\\d+), Spalte (\\d+) auf den Wert (\\d+) gesetzt werden$")
    public void dann_kann_das_Wertefeld_Zeile_Spalte_auf_den_Wert_gesetzt_werden(
            int row, int column, int value) throws Throwable {
        playground.setValue(row, column, value);
    }

    @When("^und das Feld in Zeile (\\d+), Spalte (\\d+) auf den Wert (\\d+) gesetzt wird$")
    public void und_das_Feld_in_Zeile_Spalte_auf_den_Wert_gesetzt_wird(int row,
            int column, int value) throws Throwable {
        playground.setValue(row, column, value);
    }

    @Then("^dann ist in dem Feld Zeile (\\d+) Spalte (\\d+) den Wert (\\d+) gesetzt$")
    public void dann_ist_in_dem_Feld_Zeile_Spalte_den_Wert_gesetzt(int row,
            int column, int value) throws Throwable {
        Integer fieldValue = playground.get(row, column);
        Assert.assertTrue(
                String.format("Das Feld soll den Wert %d haben", fieldValue),
                value == fieldValue);
    }

    @Given("^ist ein Spielfeld mit (\\d+) Blöcken$")
    public void ist_ein_Spielfeld_mit_Blöcken(int amountOfBlocks)
            throws Throwable {
        playground = new Playground();
        playground.setDims(9, 9);
    }

    @When("^der Block (\\d+) mit den Ziffern (\\d+) bis (\\d+) befüllt ist,$")
    public void der_Block_mit_den_Ziffern_bis_befüllt_ist(int arg1, int min,
            int max) throws Throwable {
        Block block = playground.getBlock(1);

        for (int i = min; i <= max; i++) {
            block.set(i, i);
        }
    }

    @Then("^ist der Block gültig$")
    public void ist_der_Block_gültig() throws Throwable {
        Block block = playground.getBlock(1);
        boolean valid = block.isValid();
        Assert.assertTrue("Block sollte gültig sein!: " + block, valid);
        LOG.info(playground.toString());
    }

    @Given("^ist ein Spielfeld vom (\\d+)\\.Januar$")
    public void ist_ein_Spielfeld_vom_Januar(int day) throws Throwable {
        String name = day + "-Januar.txt";
        ist_ein_Spielfeld_aus(name);
    }

    @Given("^ist ein Spielfeld aus \"(.*?)\"$")
    public void ist_ein_Spielfeld_aus(String fileName) throws Throwable {
        InputStream in = this.getClass().getResourceAsStream(("/" + fileName));
        if (in == null) {
            throw new RuntimeException(
                    String.format("Die Datei %s konnte nicht gefunden werden!",
                            fileName));
        }
        playground = new Playground(in);
        LOG.info(fileName + playground.toString());
    }

    @When("^das Spielfeld in jedem Block nur eine fehlende Ziffer hat$")
    public void das_Spielfeld_in_jedem_Block_nur_eine_fehlende_Ziffer_hat()
            throws Throwable {
        playground.setValue(0, 0, 0);
        playground.setValue(0, 4, 0);
        playground.setValue(0, 8, 0);
        playground.setValue(3, 1, 0);
        playground.setValue(3, 5, 0);
        playground.setValue(3, 6, 0);
        playground.setValue(7, 2, 0);
        playground.setValue(7, 4, 0);
        playground.setValue(7, 7, 0);

        LOG.info(playground.toString());
    }

    @Then("^dann kann es gelöst werden$")
    public void dann_kann_es_gelöst_werden() throws Throwable {
        playground.resolveWithPlanB();
        Assert.assertThat(playground.toString(), playground.isValid(),
                is(true));

        LOG.info(playground.toString());
    }

    @When("^das Spielfeld seine Lücken hat$")
    public void das_Spielfeld_seine_Lücken_hat() throws Throwable {
        // Spiel hat bereits Lücken ..
    }

}
