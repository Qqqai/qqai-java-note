package qqai.suanfa.some.g;


import qqai.suanfa.common.ListNode;

/**
 * @author qqai
 * @createTime 2020/12/2 21:03
 * @description：单链表的荷兰数问题
 */

public class NodeHeLanShu {

    public static ListNode listPartition(ListNode head, int num) {
        ListNode sh = new ListNode(); // small head
        ListNode st = null; // small tail
        ListNode eh = new ListNode(); // equal head
        ListNode et = null; // equal tail
        ListNode lh = new ListNode(); // large head
        ListNode lt = null; // large tail
        while (head != null) {
            if (head.value == num) {
                if (et == null) {
                    eh.next = head;
                } else {
                    et.next = head;
                }
                et = head;
            } else if (head.value < num) {
                if (st == null) {
                    sh.next = head;
                } else {
                    st.next = head;
                }
                st = head;
            } else {
                if (lt == null) {
                    lh.next = head;
                } else {
                    lt.next = head;
                }
                lt = head;
            }
            head = head.next;
        }
        st.next = eh.next;
        et.next = lh.next;
        lt.next = null;
        return sh.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(2);
        l1.next.next.next.next = new ListNode(8);
        l1.next.next.next.next.next = new ListNode(4);
        System.out.println(NodeHeLanShu.listPartition(l1, 4));
    }

}
