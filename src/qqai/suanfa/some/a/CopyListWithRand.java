package qqai.suanfa.some.a;

import qqai.suanfa.common.ListNode;

import java.util.HashMap;

/**
 * @author qqai
 * @createTime 2020/12/2 23:04
 * @description：根据原链表的节点拷贝新的链表节点 笔记 src/qqai/suanfa/img/复制随机链.png
 */

public class CopyListWithRand {

    public static ListNode copyListWithRand(ListNode head) {
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            map.put(cur, new ListNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // 直接把所有得节点都放到map  也就是hash表中，直接把对应的引用关系修改掉就形成了原结构
            map.get(cur).rand = map.get(cur.rand);
            map.get(cur).next = map.get(cur.next);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static void main(String[] args) {

    }
}
