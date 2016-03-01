package de.willkowsky;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Created by andre on 29.02.16.
 */
public class TreeNode {
    public ValueField valueField;
    public List<TreeNode> childrenList = new ArrayList<>();
    public String nodeName;

    public TreeNode(String root) {
        nodeName = root;
    }

    public void addChild(TreeNode treeNode) {
       childrenList.add(treeNode);
    }

    public List<TreeNode> getChildrenList() {
        return Collections.unmodifiableList(childrenList);
    }
}
