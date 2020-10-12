package qqai.leetcode;


/**
 * @author qqai
 * @createTime 2020/10/12 12:32
 * @description：二叉搜索树的最小绝对差 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 1
 * \
 * 3
 * /
 * 2
 * <p>
 * 输出：
 * 1
 * <p>
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 */

public class GetMinimumDifference {
    private int min = Integer.MAX_VALUE;
    private TreeNode pre = null;

    public static void main(String[] args) {

    }

    public int getMinimumDifference(TreeNode root) {
        min(root);
        return min;
    }

    private void min(TreeNode root) {
        if (root == null) {
            return;
        }
        min(root.left);
        if (pre != null) {
            min = Math.min(min, Math.abs(pre.val - root.val));
        }
        min(root.right);
    }
}
