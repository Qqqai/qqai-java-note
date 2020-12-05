package qqai.suanfa.common;

/**
 * @author qqai
 * @createTime 2020/10/12 12:50
 * @description：节点
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode getParent() {
        return parent;
    }

    public TreeNode setParent(TreeNode parent) {
        this.parent = parent;
        return this;
    }

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode setVal(int val) {
        this.val = val;
        return this;
    }

    public TreeNode setLeft(TreeNode left) {
        this.left = left;
        return this;

    }

    public TreeNode setRight(TreeNode right) {
        this.right = right;
        return this;

    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
