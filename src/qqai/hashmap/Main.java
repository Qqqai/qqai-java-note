package qqai.hashmap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：关于hashmap
 *
 * @author qqai
 * @createTime 2020-09-24 12:53
 */

public class Main {
    public static void main(String[] args) {
        int i = 999;
        System.out.println(i >>>= 8);
        Map<String, Object> map1 = new HashMap<>(1000);
        map1.put("111", 1);
        map1.put(null, 1);
        map1.containsKey("111");
        System.out.println(map1);
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
        //这段代码是全参构造器会执行的一个方法，这个方法的参数是传入的容量，这个容量如果不是2的幂次倍的话  这个方法就会执行直接把容量换成
        //2的幂次倍

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        Map<String, Integer> hashtable = new Hashtable<>();

        Stack<Integer> stack = new Stack<>();
        NBL nbl = new NBL();
        System.out.println(nbl.wbl);
        List<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("paramName", "\u2062\u2063\u2062兔");
        hashMap.put("paramValue", "");
        list.add(hashMap);
        HashMap<String, String> map = new HashMap<>();
        for (HashMap<String, String> stringStringHashMap : list) {
            String value = stringStringHashMap.get("paramValue") == null ? "" : stringStringHashMap.get("paramValue");
            map.put(stringStringHashMap.get("paramName"), value);
        }
        System.out.println(map);

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