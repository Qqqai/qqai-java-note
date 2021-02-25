package qqai.suanfa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/9/29 19:02
 * @description：
 */

public class 后序遍历树两种方式 {
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
//        dfs(root);
        dfs1(root);
        return list;
    }

    //TODO 迭代
    private void dfs1(TreeNode root) {

    }

    //标记 递归
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        dfs(root.right);
        list.add(root.val);
    }
}

