package qqai.suanfa.tree.c;

import qqai.suanfa.common.TreeNode;

/**
 * @author qqai
 * @createTime 2020/12/3 21:45
 * @description：平衡二叉树
 */

public class BalanceTree {

    /*标记 判断一个二叉树是否是平衡二叉树  */
    public static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(true, 0);
        }
        ReturnData leftData = process(head);
        if (!leftData.is) {
            return leftData;
        }
        ReturnData rightData = process(head);
        if (!rightData.is) {
            return rightData;
        }
        if (Math.abs(leftData.h - rightData.h) > 1) {
            return new ReturnData(false, 0);
        }
        return new ReturnData(true, Math.max(leftData.h, rightData.h) + 1);
    }

    public static class ReturnData {
        private boolean is;
        private int h;

        public ReturnData(boolean is, int h) {
            this.h = h;
            this.is = is;
        }
    }
}
