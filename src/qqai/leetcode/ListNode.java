package qqai.leetcode;

/**
 * @author qqai
 * @createTime 2020/10/9 10:48
 * @description：节点类
 */

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
