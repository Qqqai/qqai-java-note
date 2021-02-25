package qqai.suanfa.leetcode;

/**
 * @author qqai
 * @createTime 2020/9/29 19:22
 * @description：给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

public class AddTwoNumbers {
    public static void main(String[] args) {
        Node node = new AddTwoNumbers().addTwoNumbers(new Node(2), new Node(2));
        System.out.println(node);
    }

    /*标记 成功  按位运算，一个节点算一位  最后一位进位按1算*/
    public Node addTwoNumbers(Node l1, Node l2) {
        l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);

        l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        Node head = new Node(0);
        Node temp = head;
        int position = 0;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + position;
            position = sum / 10;
            sum = sum % 10;
            temp.next = new Node(sum);
            temp = temp.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (position == 1) {
            temp.next = new Node(position);
        }
        return head.next;
    }

    /*标记 递归失败*/
    public Node addTwoNumbersQQQQ(Node l1, Node l2) {
        Node node1 = new Node(0);
        node1.next = new Node(4);
        node1.next.next = new Node(3);

        Node node2 = new Node(0);
        node2.next = new Node(6);
        node2.next.next = new Node(4);

        int a = getValue(node1);
        int b = getValue(node2);
        if (a + b == 0) {
            return new Node(0);
        }
        return setValue(a + b);
    }

    private Node setValue(int i) {
        if (i <= 0) {
            return null;
        }
        Node node = new Node(i % 10);
        Node setValue = setValue(i / 10);
        if (setValue != null) {
            node.next = setValue;
        }
        return node;
    }

    private Integer getValue(Node l) {
        if (l.next == null) {
            return l.val;
        }
        Integer next = getValue(l.next);

        if (next != null) {
            return Integer.parseInt(next + String.valueOf(l.val));
        }
        return l.val;
    }


}