package de.willkowsky;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BruteForceStrategyTest {

    @Test
    public void testResolve() throws Exception {
        BruteForceStrategy bruteForceStrategy = new BruteForceStrategy();
        Playground playground = new Playground();
        playground.setValue(0, 0, 0);
        boolean resolve = bruteForceStrategy.resolve(playground);
        assertThat(resolve, is(equalTo(true)));
    }
}