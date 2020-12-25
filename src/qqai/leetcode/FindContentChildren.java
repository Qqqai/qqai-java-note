package qqai.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 力扣455
 *
 * @author qqai
 * @createTime 2020/12/25 15:49
 */
public class FindContentChildren {
    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{3}));
    }

    public static int findContentChildren(int[] g, int[] s) {
        // 对两个数组排序
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, count = 0;
        while (i < g.length && j < s.length) {
            // 每个孩子只能拿到 标记 一块  排序好了  小的肯定在前面  所以越往后只能是越来越大
            while (j < s.length && g[i] > s[j]) {
                j++;
            }
            if (j < s.length) count++;
            i++;
            j++;
        }
        return count;
    }
}
