package de.willkowsky;

import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ValueGroupTest {
    @Test
    public void createSolveTreeForEmptyFields() {
        ValueField[] valueFields = new ValueField[2];
        valueFields[0] = new ValueField(0, 0);
        valueFields[1] = new ValueField(1, 0);
        ValueGroup valueGroup = new ValueGroup(valueFields, 0);

        TreeNode rootNode = new TreeNode("root");
        valueGroup.resolve(rootNode);
        assertThat(rootNode.childrenList.size(), greaterThan(0));
        assertThat(rootNode.childrenList.get(0).getChildrenList().size(),
                greaterThan(0));
    }

}