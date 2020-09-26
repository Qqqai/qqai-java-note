package qqai.hashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

/**
 * 描述：关于hashmap
 *
 * @author qqai
 * @createTime 2020-09-24 12:53
 */

public class Main {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(12);
        map.put("111", 1);
//   static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16  如果不传递的话 hashmap的默认容量就是16
        //使用hashmap的一个参数的构造器的时候 这个参数表示集合容量  重要代码：
        // static final int tableSizeFor(int cap) {
        //        int n = cap - 1;
        //        n |= n >>> 1;
        //        n |= n >>> 2;
        //        n |= n >>> 4;
        //        n |= n >>> 8;
        //        n |= n >>> 16;
        //        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        //    }
        //这段代码是全残构造器会执行的一个方法，这个方法的参数是传入的容量，这个容量如果不是2的幂次倍的话  这个方法就会执行直接把容量换成
        //2的幂次倍

        Map<String, Integer> hashtable = new Hashtable<>();

        Stack<Integer> stack = new Stack<>();
        NBL nbl = new NBL();
        System.out.println(nbl.wbl);

    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }

    static class NBL {
        public WBL wbl;
    }
}

class WBL {
    private int age;
}