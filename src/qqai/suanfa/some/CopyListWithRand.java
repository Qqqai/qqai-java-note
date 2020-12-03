package qqai.suanfa.some;

import qqai.suanfa.common.Node;

import java.util.HashMap;

/**
 * @author qqai
 * @createTime 2020/12/2 23:04
 * @description：根据原链表的节点拷贝新的链表节点 笔记 src/qqai/suanfa/img/复制随机链.png
 */

public class CopyListWithRand {

    public static Node copyListWithRand(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
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
