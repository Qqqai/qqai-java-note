package qqai.suanfa.hash;

import java.util.HashMap;

/**
 * @author qqai
 * @createTime 2020/12/5 09:57
 * @description：随机hash
 */

public class RandomPool {
    private final HashMap<String, Integer> map1;
    private final HashMap<Integer, String> map2;
    private int size;

    public RandomPool() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        size = 0;
    }

    public void add(String s) {
        if (!map1.containsKey(s)) {
            map1.put(s, size);
            map2.put(size++, s);
        }
    }

    public String getRandom() {
        if (size == 0) {
            return map2.get(size);
        }
        // 在size区间获取一个值
        int index = (int) (Math.random() * size); // 0 ~ size - 1
        return map2.get(index);
    }

    public void remove(String s) {
        if (map1.containsKey(s)) {
            // 查出这个s对应的index
            Integer index = map1.get(s);
            // 得到最后一个元素得string内容
            String s1 = map2.get(--size);
            // 把最后一个元素得index换成要删除得元素得index
            map1.put(s1, index);
            // 把index对应的元素换成最后一个元素
            map2.put(index, s1);
            // 删除
            map1.remove(s);
            map2.remove(size);
        }
    }
}
