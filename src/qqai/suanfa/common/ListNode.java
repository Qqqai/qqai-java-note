package qqai.suanfa.common;

/**
 * @author qqai
 * @createTime 2020/12/2 18:16
 * @description：节点类
 */

public class ListNode {
    public Integer value;
    public ListNode next;
    public ListNode rand;

    public ListNode(Integer value) {
        this.value = value;
    }

    public ListNode() {

    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                ", rand=" + rand +
                '}';
    }
}
