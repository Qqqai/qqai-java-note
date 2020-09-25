package qqai.leetcode;


/**
 * 描述：反转二叉树
 *
 * @author qqai
 * @createTime 2020-09-16 15:31
 */

public class InvertTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode root_left = new TreeNode(2);
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode root_right = new TreeNode(7);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(9);
        root.left = root_left;
        root.right = root_right;
        root_left.left = treeNode;
        root_left.right = treeNode1;
        root_right.left = treeNode3;
        root_right.right = treeNode4;

        TreeNode result = invertTree(root);
        System.out.println(result);
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
