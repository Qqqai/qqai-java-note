package qqai.mianshi.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/10/18 10:42
 * @description：美团2 某比赛已经进入了淘汰赛阶段, 已知共有n名选手参与了此阶段比赛，他们的得分分别是a_1,a_2….a_n,小美作为比赛的裁判希望设定一个分数线m，使得所有分数大于m的选手晋级，其他人淘汰。
 * <p>
 * 但是为了保护粉丝脆弱的心脏，小美希望晋级和淘汰的人数均在[x,y]之间。
 * <p>
 * 显然这个m有可能是不存在的，也有可能存在多个m，如果不存在，请你输出-1，如果存在多个，请你输出符合条件的最低的分数线。
 * <p>
 * <p>
 * <p>
 * 输入描述
 * 输入第一行仅包含三个正整数n,x,y，中间用空格隔开，分别表示参赛的人数和晋级淘汰人数区间。(1<=n<=50000,1<=x,y<=n)
 * <p>
 * 输入第二行包含n个整数，中间用空格隔开，表示从1号选手到n号选手的成绩。(1<=a_i<=1000)
 * <p>
 * 输出描述
 * 输出仅包含一个整数，如果不存在这样的m，则输出-1，否则输出符合条件的最小的值。
 * <p>
 * <p>
 * 样例输入
 * 6 2 3
 * 1 2 3 4 5 6
 * 样例输出
 * 3
 */

public class MainTuan2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//人数
        int p = scanner.nextInt();//区间小
        int q = scanner.nextInt();//区间大

        if (p > q) {
            int temp = q;
            q = p;
            p = temp;
        }

        int[] ints = new int[n];//成绩数组
        for (int i = 0; i < n; i++) {
            ints[i] = scanner.nextInt();
        }
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        int m = Integer.MAX_VALUE;//分数线
        int x = p;//从区间最小开始淘汰
        while (x <= q) {
            if (n - x >= p && n - x <= q) {
                if (ints[x - 1] < m) {
                    m = ints[x - 1];
                }
            }
            x++;
        }
        if (m == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(m);
        }
    }
}
