package qqai.suanfa.list.a;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author qqai
 * @createTime 2020/12/13 20:34
 * @description：集合的节点数
 */

public class UnionFindSet {
    public static class Node {
        //whatever you like here
    }

    public HashMap<Node, Node> fatherMap;// key child value father
    public HashMap<Node, Integer> sizeMap;

    public UnionFindSet(List<Node> nodes) {
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        makeSets(nodes);
    }

    private void makeSets(List<Node> nodes) {
        for (Node node : nodes) {
            // 每一个集合自己形成一个集合 代表节点都是node本身
            fatherMap.put(node, node);
            // 每一个集合的初始化大小都是1
            sizeMap.put(node, 1);
        }
    }

    /*查找可以表示集合的节点*/
    private Node findHead(Node node) {
        // 获取当前节点对象
        Node father = fatherMap.get(node);
        // 如果node存的father不是node本身那么向上查找
        if (father != node) {
            // 没递归一次就会更新每个当前node  把他的father节点指向最上面的那个节点 ---  node.father = node
            father = findHead(node);
        }
        // 找到本身的父节点是本身的节点  node所在链表的表示节点就是这个father节点
        fatherMap.put(node, father);
        // 返回这个节点
        return father;

        /*标记 非递归方式*/
        /*Stack<Node> stack = new Stack<>();
        Node cur = node;
        Node parent = fatherMap.get(cur);
        while (cur != parent) {
            stack.push(cur);
            cur = parent;
            parent = fatherMap.get(cur);
        }
        while (!stack.isEmpty()) {
            fatherMap.put(stack.pop(), parent);
        }
        return parent;*/
    }

    /*是否是相同的集合*/
    public boolean isSameSet(Node a, Node b) {
        return findHead(a) == findHead(b);
    }

    /*合并两个集合*/
    public void union(Node a, Node b) {
        if (a == null || b == null) {
            return;
        }
        // 获取两个节点的代表头
        Node ahead = findHead(a);
        Node bhead = findHead(b);
        if (ahead != bhead) {
            // 获取集合的大小
            Integer asize = sizeMap.get(ahead);
            Integer bsize = sizeMap.get(bhead);
            // 把a集合的代表头指向b
            fatherMap.put(ahead, bhead);
            // 把b集合的size增大
            sizeMap.put(bhead, asize + bsize);
            // 删除掉a集合的size
            sizeMap.remove(ahead);
        }
    }
}
