package qqai.suanfa.tree;

import qqai.suanfa.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qqai
 * @createTime 2020/12/3 22:43
 * @description：二叉搜索树
 */

public class IsCbt {
    public static boolean isCbt(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            else {
                leaf = true;
            }
//            if (l == null && r == null) {
//                leaf = true;
//            }
        }
        return true;
    }
}
