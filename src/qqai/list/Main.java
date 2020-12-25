package qqai.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述：list集合的取别
 *
 * @author qqai
 * @createTime 2020-09-26 13:46
 */

public class Main {

    /**
     * 由 list的源码我们不难发现，不管是ArrayList还是LinkedList都是线程不安全的
     *
     * @param
     */
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(2);

        List<Integer> list = new ArrayList<>();

//        LinkedList.Node;内部类  所以LinkedList是一个双向链表。
//        private static class Node<E> {
//            E item;
//            Node<E> next;
//            Node<E> prev;
//
//            Node(Node<E> prev, E element, Node<E> next) {
//                this.item = element;
//                this.next = next;
//                this.prev = prev;
//            }
//        }
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        System.out.println(arrayList.get(0));
        //arrayList添加是这样的
        // elementData：  transient Object[] elementData;  是这样定义的  所以arrayList底层是一个object和
        //public boolean add(E e) {
        //        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //        elementData[size++] = e;
        //        return true;
        //    }
    }
}
