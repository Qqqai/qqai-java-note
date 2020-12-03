package qqai.suanfa.some;


import qqai.suanfa.common.Node;

/**
 * @author qqai
 * @createTime 2020/12/2 21:03
 * @description：单链表的荷兰数问题 笔记 src/qqai/suanfa/img/单链表的荷兰数问题.png
 */

public class NodeHeLanShu {

    public static Node listPartition(Node head, int num) {
        Node sh = new Node(); // small head
        Node st = null; // small tail
        Node eh = new Node(); // equal head
        Node et = null; // equal tail
        Node lh = new Node(); // large head
        Node lt = null; // large tail
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
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);
        l1.next.next.next = new Node(2);
        l1.next.next.next.next = new Node(8);
        l1.next.next.next.next.next = new Node(4);
        System.out.println(NodeHeLanShu.listPartition(l1, 4));
    }

}
