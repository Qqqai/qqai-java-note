package qqai.hashmap;

import java.util.HashMap;
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
        Map<String, Object> map = new HashMap<>();
//        map.
        Stack<Integer> stack = new Stack<>();
        NBL nbl = new NBL();
        System.out.println(nbl.wbl);

    }

    static class NBL {
        public WBL wbl;
    }
}

class WBL {
    private int age;
}