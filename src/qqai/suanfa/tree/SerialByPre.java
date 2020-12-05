package qqai.suanfa.tree;

import qqai.suanfa.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qqai
 * @createTime 2020/12/3 19:13
 * @description：二叉树得先序序列化
 */

public class SerialByPre {

    /*标记 src/qqai/suanfa/img/二叉树得先序序列化.png*/
    public static String serialByPre(TreeNode node) {
        if (node == null) {
            return "#!";
        }
        String res = node.val + "!";
        res += serialByPre(node.left);
        res += serialByPre(node.right);
        return res;
    }

    /*标记 先序的反序列化*/
    public static TreeNode reconByPreString(String s) {
        String[] values = s.split("!");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return reconByOrder(queue);
    }

    private static TreeNode reconByOrder(Queue<String> queue) {
        String val = queue.poll();
        if ("#".equals(val)) {
            return null;
        }
        assert val != null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = reconByOrder(queue);
        node.right = reconByOrder(queue);
        return node;
    }
}
