package qqai.suanfa.some;

import qqai.suanfa.common.Node;

/**
 * @author qqai
 * @createTime 2020/12/3 00:31
 * @description：两个链表相交得一系列问题
 */

public class Intersect {
    public static Node intersect(Node head1, Node head2) {
        // two linked list intersect
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 两条链表都无环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, head2, loop1, loop2);
        }
        // 一个有环一个无环 单链表结构 必定不相交
        return null;
    }

    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            if (head1 == null || head2 == null) {
                return null;
            }
            // 链表长度得差值
            int n = 0;
            while (cur1.next != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2) {
                n--;
                cur2 = cur2.next;
            }
            // 单链表结构如果两条链相交那么最后一个节点一定相同
            if (cur1 != cur2) {
                return null;
            }
            // 找到较长得链
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            // 长一点得链向后移动n位
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }

            //  1-2-3-4-5-6-7   len = 6
            //  0-9-0-9-0-4-5-6-7-8   // len = 10  10 - 4 = 6

            // 此时如果两条链相交那么一定会在相交得节点相遇
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                // 如果环内找得到loop2  那么就返回loop1
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            // 两个有环链表 但是不相交
            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        // 复用
        return bothLoop(head1, head2, null, null);
//        if (head1 == null || head2 == null) {
//            return null;
//        }
//        // 链表长度得差值
//        int n = 0;
//        Node cur1 = head1;
//        Node cur2 = head2;
//        while (cur1.next != null) {
//            n++;
//            cur1 = cur1.next;
//        }
//        while (cur2.next != null) {
//            n--;
//            cur2 = cur2.next;
//        }
//        // 单链表结构如果两条链相交那么最后一个节点一定相同
//        if (cur1 != cur2) {
//            return null;
//        }
//        // 找到较长得链
//        cur1 = n > 0 ? head1 : head2;
//        cur2 = cur1 == head1 ? head2 : head1;
//        n = Math.abs(n);
//        // 长一点得链向后移动n位
//        while (n != 0) {
//            n--;
//            cur1 = cur1.next;
//        }
//
//        //  1-2-3-4-5-6-7   len = 6
//        //  0-9-0-9-0-4-5-6-7-8   // len = 10  10 - 4 = 6
//
//        // 此时如果两条链相交那么一定会在相交得节点相遇
//        while (cur1 != cur2) {
//            cur1 = cur1.next;
//            cur2 = cur2.next;
//        }
//        return cur1;
    }

    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next;  // slow
        Node n2 = head.next.next; // fast
        while (n1 != n2) { // first intersect
            if (n2.next == null || n2.next.next == null) {  // n2 meet null return null, There is no ring
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // slow
        while (n1 != n2) { // second intersect
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1; // this node is the link points
    }
}
