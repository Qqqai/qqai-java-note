package qqai.shujujiegou.jingdiansuanfa;

import java.util.Random;

/**
 * 描述：查找最长公共子序列 （动态规划！！！）
 *
 * @author qqai
 * @createTime 2020-9-26 11:03:23
 */
//使用动态规划找出最长公共子序列
public class zuichangzixulie {

    private static int length = 0;

    public static void main(String[] args) {
        // 随机生成指定长度的字符串
        int size = 90;
        String x = generateRandomStr(size);// 调用方法
        String y = generateRandomStr(size);// 调用两次生成两个由generateRandomStr方法中base而产生的新序列

        // 也可以指定初始的序列
         x = "ACBDEGGGG";
         y = "DEGGDJFFD";

        // 都是20
        int m = x.length();
        int n = y.length();

        // 创建二维数组，也就是填表的过程 c数组对应就是x，y的元素
        int[][] c = new int[m + 1][n + 1];

        // 初始化二维数组
        for (int i = 0; i < m + 1; i++) {
            // 二维数组第一列所有元素初值为0
            c[i][0] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            // 二维数组第一行所有元素为0； c[0][0] 已经为0 不用重新赋值
            c[0][i] = 0;
        }

        // 实现公式逻辑
        int[][] path = new int[m + 1][n + 1]; // 记录通过哪个子问题解决的，也就是递推的路径

        // 从行数为1开始遍历
        for (int i = 1; i < m + 1; i++) {

            // 从当前行的第一个元素开始遍历
            for (int j = 1; j < n + 1; j++) {

                // 查看对应下标的字符是否相同
                if (x.charAt(i - 1) == y.charAt(j - 1)) {

                    // 相同调用公式 上一行，当先列的前一列 所保存的数值+1
                    c[i][j] = c[i - 1][j - 1] + 1;

                    // 不满足第一个条件 则判断二维数组c中的 当前行的前一个数据是否小于等于当前列的上一行所对应的数据
                } else if (c[i - 1][j] >= c[i][j - 1]) {

                    // 匹配调用公式 将当前列的上一行的数据赋值给当前行当前列的数据
                    c[i][j] = c[i - 1][j];

                    // path数组中当前行当前列对应的数据更改为1
                    path[i][j] = 1;

                    // 以上都不满足
                } else {
                    // 调用第三个公式 将当前行的前一个数据赋值给当前行当前列
                    c[i][j] = c[i][j - 1];

                    // path数组中当前行当前列对应的数据更改为-1
                    path[i][j] = -1;
                }
            }
        }

        // 输出查看c
        System.out.println("c:");
        // 遍历c数组由二维数组的方式输出
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                // 输出当前行的所有元素 并加上空格
                System.out.print(c[i][j] + "\t");
            }
            // 当前行遍历完成换行遍历下一行元素
            System.out.println();
        }

        // 输出查看path
        System.out.println("path:");
        // 遍历path和c数组相同方式
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }

        // 输出x与y序列
        System.out.printf("%s与%s的最长公共子序列为：\n", x, y);
        // 调用方法输出组后结果
        PrintLCS(path, x, m, n);
        // 输出总长度
        System.out.println("\t长度为:" + length);
        // length为静态变量，使用完成后赋值为0 方便下一次使用
        length = 0;
    }

    public static String generateRandomStr(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 序列
        Random random = new Random(); // 随机方法类
        StringBuilder sb = new StringBuilder(); // 包存随机返回的字符串
        for (int i = 0; i < length; i++) { // 循环20次 ---- stringbuilder的内容长度为20
            int number = random.nextInt(base.length()); // 随机返回一个下标
            sb.append(base.charAt(number)); // 将当前下标的元素取出并存入stringbuilder对象中
        }
        return sb.toString(); // 输出stringbuilder的内容
    }

    public static void PrintLCS(int[][] path, String x, int i, int j) {

        // 如果i和j都为0则没有数据
        if (i == 0 || j == 0) {
            // 返回空值
            return;
        }
        // 如果path[i][j]元素为0
        if (path[i][j] == 0) {
            // 则这个元素为最长子序列的元素之一
            PrintLCS(path, x, i - 1, j - 1);
            // 置于当前所得序列的最前面
            System.out.printf("%c", x.charAt(i - 1));

            // 记录最长的子序列的长度
            length++;

            // 若path[i][j] == 1 则符合第二种子问题
        } else if (path[i][j] == 1) {
            // 递归调用 i = i - 1
            PrintLCS(path, x, i - 1, j);
        } else {
            // 不满足以上条件则为第三种子问题 递归调用 j = j - 1
            PrintLCS(path, x, i, j - 1);
        }
    }
}
