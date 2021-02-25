package qqai.suanfa.leetcode;

/**
 * @author qqai
 * @createTime 2020/9/30 09:33
 * @description：二叉搜索树
 */

public class InsertIntoBST {
    public static void main(String[] args) {

    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
