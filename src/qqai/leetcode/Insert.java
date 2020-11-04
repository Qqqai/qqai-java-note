package qqai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/4 16:10
 * @description：
 */
//给出一个无重叠的 ，按照区间起始端点排序的区间列表。
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
//
//
//
// 示例 1：
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
//
//
// 示例 2：
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
//
//
//
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
// Related Topics 排序 数组
// 👍 272 👎 0

public class Insert {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 0;
        int len = intervals.length;

        while (i < len && intervals[i][1] < newInterval[0]) { // 当前遍历的是蓝左边的，不重叠的区间
            res.add(intervals[i]);
            i++;
        }

        while (i < len && intervals[i][0] <= newInterval[1]) { // 当前遍历是有重叠的区间
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]); //左端取较小者，更新给兰区间的左端
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]); //右端取较大者，更新给兰区间的右端
            i++;
        }
        res.add(newInterval); // 循环结束后，兰区间为合并后的区间，推入res

        while (i < len) {                 // 在蓝右边，没重叠的区间
            res.add(intervals[i]);
            i++;
        }
        int[][] ints = new int[res.size()][2];
        for (int j = 0; j < res.size(); j++) {
            ints[j][0] = res.get(j)[0];
            ints[j][1] = res.get(j)[1];
        }
        return ints;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] b = {4, 8};
        System.out.println(Arrays.deepToString(new Insert().insert(a, b)));
    }
}
