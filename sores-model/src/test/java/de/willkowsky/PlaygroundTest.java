package de.willkowsky;

import org.junit.Test;

import java.io.InputStream;

/**
 * Created by andre on 11.03.16.
 */
public class PlaygroundTest {

    @Test
    public void solveGameLoadedFromFile() {
        InputStream resourceAsStream =
                getClass().getResourceAsStream("/10-maerz.txt");

        Playground playground = new Playground(resourceAsStream);
        System.out.println("playground is " + playground);

//        playground.resolve();
    }
}