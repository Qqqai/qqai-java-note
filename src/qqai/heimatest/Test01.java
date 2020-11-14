package qqai.heimatest;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：测试1
 *
 * @author qqai
 * @createTime 2020-09-14 11:06
 */

public class Test01 {

    public static void main(String[] args) {
        // *************查找 binarySearch()****************
        char[] e = {'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B'};
        // 排序后再进行二分查找，否则找不到
        Arrays.sort(e);
        System.out.println("Arrays.sort(e)" + Arrays.toString(e));
        System.out.println("Arrays.binarySearch(e, 'c')：");
        int s = Arrays.binarySearch(e, 'c');
        System.out.println("字符c在数组的位置：" + s);

        // *************转列表 asList()****************
        /*
         * 返回由指定数组支持的固定大小的列表。
         * （将返回的列表更改为“写入数组”。）该方法作为基于数组和基于集合的API之间的桥梁，与Collection.toArray()相结合 。
         * 返回的列表是可序列化的，并实现RandomAccess 。
         * 此方法还提供了一种方便的方式来创建一个初始化为包含几个元素的固定大小的列表如下：
         */
        List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
        System.out.println(stooges);

        // *************复制 copy****************
        // copyOf 方法实现数组复制,h为数组，6为复制的长度
        int[] h = {1, 2, 3, 3, 3, 3, 6, 6, 6,};
        int[] i = Arrays.copyOf(h, 6);
        System.out.println("Arrays.copyOf(h, 6);：");
        // 输出结果：123333
        for (int j : i) {
            System.out.print(j);
        }
        // 换行
        System.out.println();
        // copyOfRange将指定数组的指定范围复制到新数组中
        int[] j = Arrays.copyOfRange(h, 6, 11);
        System.out.println("Arrays.copyOfRange(h, 6, 11)：");
        // 输出结果66600(h数组只有9个元素这里是从索引6到索引11复制所以不足的就为0)
        for (int j2 : j) {
            System.out.print(j2);
        }
        // 换行
        System.out.println();
    }

}
