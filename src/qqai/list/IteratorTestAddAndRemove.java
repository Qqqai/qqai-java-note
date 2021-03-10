package qqai.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/9/27 18:29
 * @description：测试迭代器得添加和删除 并且 测试为什么foreach不能add和remove
 */

public class IteratorTestAddAndRemove {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add("dada");
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().equalsIgnoreCase("aaa")) {
        iterator.remove();
      }
    }
    list.removeIf(s -> s.equalsIgnoreCase("aaa"));
    /*标记 以上两种一摸一样 拉姆达真好用啊*/
    //exception:  java.util.ConcurrentModificationException
//        for (String s : list) {  //笔记 遍历得时候会默认记住这集合得长度，当遍历得时候长度不相等得时候就会抛出异常
//            // private class Itr implements Iterator<E> {
//            //  ......
//            //这里抛出异常
//            //  final void checkForComodification() {
//            //            if (modCount != expectedModCount)
//            //                throw new ConcurrentModificationException();
//            //        }
//            //    }
//            if (s.equalsIgnoreCase("aaa")) {
//                list.remove(s);
//            }
//        }
    list.removeIf(item -> {
      return item.equals("aaa");
    });
  }
}
