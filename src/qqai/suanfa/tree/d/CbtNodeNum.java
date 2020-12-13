package qqai.suanfa.tree.d;

import qqai.suanfa.common.TreeNode;

/**
 * @author qqai
 * @createTime 2020/12/3 23:48
 * @description：完全二叉树得节点个数
 */

public class CbtNodeNum {
    public static int nodeNum(TreeNode node) {
        // 这棵树为空
        if (node == null) {
            return 0;
        }
        return bs(node, 1, mostLeftLevel(node, 1));
    }

    /**
     * @param node 根节点
     * @param l    此节点在第几层
     * @param h    整棵树得深度
     * @return 节点个数
     */
    private static int bs(TreeNode node, int l, int h) {
        // 深度只有1
        if (l == h) {
            return 1;
        }
        // 如果右边得深度等于完全二叉树得深度
        if (mostLeftLevel(node.right, l + 1) == h) {
            // 根节点得左子树得节点个数就能得到 2 ^ h - 1 细分一下某层得话就是 2 ^ (h - l) - 1
            // 然后递归求根节点得右子树得节点个数
            return (1 << (h - l)) + bs(node.right, l + 1, h);
        } else {
            // 同上 不过这里是求根节点得右子树得节点个数以为根节点得右子树得左边界没有到深度h
            // 递归求得是根节点得左子树得节点个数
            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }

    /**
     * @param node  当前节点
     * @param level 层数
     * @return 该层得最大深度
     */
    private static int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            node = node.left;
            level++;
        }
        return level - 1;
    }
}
