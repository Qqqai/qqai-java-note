package qqai.leetcode;

/**
 * @author qqai
 * @createTime 2020/10/13 15:59
 * @description：两两交换链表中的节点 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 */

public class SwapPairs {
    private int count = 0;
    private Node resp = null;

    public Node swapPairs(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(new SwapPairs().swapPairs(node1));
    }
}
