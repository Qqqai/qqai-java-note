package qqai.suanfa.jinjie.tanxin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 会议室开会的问题
 *
 * @author qqai
 * @createTime 2020/12/15 14:57
 */
public class MeetingProblem {

    public static int bestArrange(Program[] programs, int start) {
        // 所有项目都按照结束时间排序
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));
        int result = 0;
        // 遍历排完序的序列
        for (Program program : programs) {
            // 如果当前项目的起始时间在现在可以开始的时间之后
            if (start <= program.start) {
                // 可以安排的会议数加一
                result++;
                // 可以开始的时间来到当前项目的结束时间
                start = program.end;
            }
        }
        // 返回所有可以安排的项目的数量  (最大)
        return result;
    }

    /*项目节点*/
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
