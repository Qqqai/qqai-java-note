package qqai.suanfa.leetcode;

/**
 * @author qqai
 * @createTime 2020/10/20 15:21
 * @description：重排链表 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */

public class ReorderList {

    private static Node temp;

    public static void main(String[] args) {
        Node l2 = new Node(1);
        l2.next = new Node(2);
        l2.next.next = new Node(3);
        l2.next.next.next = new Node(4);
        reorderList(l2);
        System.out.println(l2);
    }

    public static void reorderList(Node head) {

        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        Node h = head;
        //求出节点数
        while (h != null) {
            len++;
            h = h.next;
        }

        reorderListHelper(head, len);
    }

    private static Node reorderListHelper(Node head, int len) {
        if (len == 1) {
            Node outTail = head.next;
            head.next = null;
            return outTail;
        }
        if (len == 2) {
            Node outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        Node tail = reorderListHelper(head.next, len - 2);
        Node subHead = head.next;//中间链表的头结点
        head.next = tail;
        Node outTail = tail.next;  //上一层 head 对应的 tail
        tail.next = subHead;
        return outTail;
    }


//    public static void reorderList(ListNode head) {
//        dfs = head;
//        sec = head.next;
//        if (sec.next == null) {
//            return;
//        }
//        dfs(sec.next);
//    }
//
//    private static ListNode dfs(ListNode third) {
//        if (third == null) {
//            return dfs;
//        }
//        temp1 = dfs.next;
//        dfs.next = third;
//        temp2 = third.next;
//        third.next = temp1;
//        sec.next = temp2;
//        sec = sec.next;
//        return dfs(sec.next);
//    }
}

