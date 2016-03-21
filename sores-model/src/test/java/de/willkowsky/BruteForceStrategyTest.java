package de.willkowsky;

import org.junit.Test;

import static org.junit.Assert.*;

public class BruteForceStrategyTest {

    @Test
    public void testResolve() throws Exception {
        BruteForceStrategy bruteForceStrategy = new BruteForceStrategy();

        Playground playground = new Playground();

        playground.setValue(0,0,0);
        bruteForceStrategy.resolve(playground);



    }
}