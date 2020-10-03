package qqai.leetcode;

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
        ListNode node = new AddTwoNumbers().addTwoNumbers(new ListNode(2), new ListNode(2));
        System.out.println(node);
    }

    /*标记 成功  按位运算，一个节点算一位  最后一位进位按1算*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode head = new ListNode(0);
        ListNode temp = head;
        int position = 0;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + position;
            position = sum / 10;
            sum = sum % 10;
            temp.next = new ListNode(sum);
            temp = temp.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (position == 1) {
            temp.next = new ListNode(position);
        }
        return head.next;
    }

    /*标记 递归失败*/
    public ListNode addTwoNumbersQQQQ(ListNode l1, ListNode l2) {
        ListNode node1 = new ListNode(0);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(3);

        ListNode node2 = new ListNode(0);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        int a = getValue(node1);
        int b = getValue(node2);
        if (a + b == 0) {
            return new ListNode(0);
        }
        ListNode res = setValue(a + b);
        return res;
    }

    private ListNode setValue(int i) {
        if (i <= 0) {
            return null;
        }
        ListNode node = new ListNode(i % 10);
        ListNode setValue = setValue(i / 10);
        if (setValue != null) {
            node.next = setValue;
        }
        return node;
    }

    private Integer getValue(ListNode l) {
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


class ListNode {

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
