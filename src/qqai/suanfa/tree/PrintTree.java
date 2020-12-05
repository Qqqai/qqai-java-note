package qqai.suanfa.tree;


import qqai.suanfa.common.TreeNode;

/**
 * @author qqai
 * @createTime 2020/12/3 18:11
 * @description：中序遍历后找到后继节点
 */

public class PrintTree {
    /*标记 src/qqai/suanfa/img/中序遍历求某个节点得后继节点.png*/
    public static TreeNode getSuccessNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        // 存在右子树 找到后继节点就是右子树得最左节点
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            TreeNode parent = node.parent;
            // 找到一个节点等于他父节点得left就停止  parent为空得话就是指当前节点没有后继节点直接返回null
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static TreeNode getLeftMost(TreeNode node) {
        if (node == null) {
            return null;
        }
        // 一直找到最左边得节点
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
