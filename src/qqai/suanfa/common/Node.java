package qqai.suanfa.common;

/**
 * @author qqai
 * @createTime 2020/12/2 18:16
 * @description：节点类
 */

public class Node {
    public Integer value;
    public Node next;
    public Node rand;

    public Node(Integer value) {
        this.value = value;
    }

    public Node() {

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
