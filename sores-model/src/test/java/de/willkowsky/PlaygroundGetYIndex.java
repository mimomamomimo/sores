package de.willkowsky;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PlaygroundGetYIndex {

    private Playground playground = new Playground();

    @Before
    public void setup() {
        playground.setDims(9, 9);
    }


    @Test
    public void returnsCorrectValuesForBlock0() throws Exception {
        Assert.assertThat(playground.getYIndex(0, 0), is(0));
        Assert.assertThat(playground.getYIndex(0, 3), is(1));
        Assert.assertThat(playground.getYIndex(0, 6), is(2));
    }

    @Test
    public void returnsCorrectValuesForBlock1() throws Exception {
        Assert.assertThat(playground.getYIndex(1, 0), is(0));
        Assert.assertThat(playground.getYIndex(1, 1), is(0));
        Assert.assertThat(playground.getYIndex(1, 2), is(0));
    }

    @Test
    public void returnsCorrectValuesForBlock8() throws Exception {
        Assert.assertThat(playground.getYIndex(8, 0), is(6));
        Assert.assertThat(playground.getYIndex(8, 1), is(6));
        Assert.assertThat(playground.getYIndex(8, 2), is(6));
        Assert.assertThat(playground.getYIndex(8, 3), is(7));
        Assert.assertThat(playground.getYIndex(8, 4), is(7));
        Assert.assertThat(playground.getYIndex(8, 5), is(7));
        Assert.assertThat(playground.getYIndex(8, 6), is(8));
        Assert.assertThat(playground.getYIndex(8, 7), is(8));
        Assert.assertThat(playground.getYIndex(8, 8), is(8));

    }

    @Test
    public void returnsCorrectValuesForField_0() throws Exception {
        int fieldNumber = 0;
        Assert.assertThat(playground.getYIndex(0, fieldNumber), is(0));
        Assert.assertThat(playground.getYIndex(1, fieldNumber), is(0));
        Assert.assertThat(playground.getYIndex(2, fieldNumber), is(0));
        Assert.assertThat(playground.getYIndex(3, fieldNumber), is(3));
        Assert.assertThat(playground.getYIndex(4, fieldNumber), is(3));
        Assert.assertThat(playground.getYIndex(5, fieldNumber), is(3));
        Assert.assertThat(playground.getYIndex(8, fieldNumber), is(6));
        Assert.assertThat(playground.getYIndex(7, fieldNumber), is(6));
        Assert.assertThat(playground.getYIndex(6, fieldNumber), is(6));
    }

    @Test
    public void returnsCorrectValuesForField_1() throws Exception {
        int fieldNumber = 1;
        Assert.assertThat(playground.getYIndex(0, fieldNumber), is(0));
        Assert.assertThat(playground.getYIndex(1, fieldNumber), is(0));
        Assert.assertThat(playground.getYIndex(2, fieldNumber), is(0));
        Assert.assertThat(playground.getYIndex(3, fieldNumber), is(3));
        Assert.assertThat(playground.getYIndex(4, fieldNumber), is(3));
        Assert.assertThat(playground.getYIndex(5, fieldNumber), is(3));
        Assert.assertThat(playground.getYIndex(8, fieldNumber), is(6));
        Assert.assertThat(playground.getYIndex(7, fieldNumber), is(6));
        Assert.assertThat(playground.getYIndex(6, fieldNumber), is(6));
    }

    @Test
    public void returnsCorrectValuesForField_3() throws Exception {
        int fieldNumber = 3;
        Assert.assertThat(playground.getYIndex(0, fieldNumber), is(1));
        Assert.assertThat(playground.getYIndex(1, fieldNumber), is(1));
        Assert.assertThat(playground.getYIndex(2, fieldNumber), is(1));
        Assert.assertThat(playground.getYIndex(3, fieldNumber), is(4));
        Assert.assertThat(playground.getYIndex(4, fieldNumber), is(4));
        Assert.assertThat(playground.getYIndex(5, fieldNumber), is(4));
        Assert.assertThat(playground.getYIndex(8, fieldNumber), is(7));
        Assert.assertThat(playground.getYIndex(7, fieldNumber), is(7));
        Assert.assertThat(playground.getYIndex(6, fieldNumber), is(7));
    }

    @Test
    public void returnsCorrectValuesForField_6() throws Exception {
        int fieldNumber = 6;
        Assert.assertThat(playground.getYIndex(0, fieldNumber), is(2));
        Assert.assertThat(playground.getYIndex(1, fieldNumber), is(2));
        Assert.assertThat(playground.getYIndex(2, fieldNumber), is(2));
        Assert.assertThat(playground.getYIndex(3, fieldNumber), is(5));
        Assert.assertThat(playground.getYIndex(4, fieldNumber), is(5));
        Assert.assertThat(playground.getYIndex(5, fieldNumber), is(5));
        Assert.assertThat(playground.getYIndex(8, fieldNumber), is(8));
        Assert.assertThat(playground.getYIndex(7, fieldNumber), is(8));
        Assert.assertThat(playground.getYIndex(6, fieldNumber), is(8));
    }

    @Test
    public void returnsCorrectValuesForBlock3() throws Exception {
        Assert.assertThat(playground.getYIndex(3, 0), is(3));
        Assert.assertThat(playground.getYIndex(3, 3), is(4));
        Assert.assertThat(playground.getYIndex(3, 6), is(5));
    }


}