package de.willkowsky;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PlaygroundGetXForBlock {

    private Playground playground = new Playground();

    @Before
    public void setup (){
        playground.setDims(9,9);
    }

    @Test
    public void returnsCorrectValuesForBlock0() throws Exception {
        Assert.assertThat(playground.getXForBlock(0,0), is(0));
        Assert.assertThat(playground.getXForBlock(0,1), is(1));
        Assert.assertThat(playground.getXForBlock(0,2), is(2));
        Assert.assertThat(playground.getXForBlock(0,3), is(0));
        Assert.assertThat(playground.getXForBlock(0,4), is(1));
        Assert.assertThat(playground.getXForBlock(0,5), is(2));
        Assert.assertThat(playground.getXForBlock(0,6), is(0));
        Assert.assertThat(playground.getXForBlock(0,7), is(1));
        Assert.assertThat(playground.getXForBlock(0,8), is(2));

    }

    @Test
    public void returnsCorrectValuesForBlock1() throws Exception {
        Assert.assertThat(playground.getXForBlock(1,0), is(3));
        Assert.assertThat(playground.getXForBlock(1,1), is(4));
        Assert.assertThat(playground.getXForBlock(1,2), is(5));
    }

    @Test
    public void returnsCorrectValuesForBlock2() throws Exception {
        Assert.assertThat(playground.getXForBlock(2,0), is(6));
        Assert.assertThat(playground.getXForBlock(2,1), is(7));
        Assert.assertThat(playground.getXForBlock(2,2), is(8));
    }

    @Test
    public void returnsCorrectValuesForBlock3() throws Exception {
        Assert.assertThat(playground.getXForBlock(3,0), is(0));
        Assert.assertThat(playground.getXForBlock(3,1), is(1));
        Assert.assertThat(playground.getXForBlock(3,2), is(2));
        Assert.assertThat(playground.getXForBlock(3,3), is(0));
        Assert.assertThat(playground.getXForBlock(3,4), is(1));
        Assert.assertThat(playground.getXForBlock(3,5), is(2));
        Assert.assertThat(playground.getXForBlock(3,6), is(0));
        Assert.assertThat(playground.getXForBlock(3,7), is(1));
        Assert.assertThat(playground.getXForBlock(3,8), is(2));
    }

    @Test
    public void returnsCorrectValuesForBlock6() throws Exception {
        Assert.assertThat(playground.getXForBlock(6,0), is(0));
        Assert.assertThat(playground.getXForBlock(6,1), is(1));
        Assert.assertThat(playground.getXForBlock(6,2), is(2));
    }

    @Test
    public void returnsCorrectValuesForBlock8() throws Exception {
        Assert.assertThat(playground.getXForBlock(8,0), is(6));
        Assert.assertThat(playground.getXForBlock(8,1), is(7));
        Assert.assertThat(playground.getXForBlock(8,2), is(8));
        Assert.assertThat(playground.getXForBlock(8,3), is(6));
        Assert.assertThat(playground.getXForBlock(8,4), is(7));
        Assert.assertThat(playground.getXForBlock(8,5), is(8));
        Assert.assertThat(playground.getXForBlock(8,6), is(6));
        Assert.assertThat(playground.getXForBlock(8,7), is(7));
        Assert.assertThat(playground.getXForBlock(8,8), is(8));
    }


}