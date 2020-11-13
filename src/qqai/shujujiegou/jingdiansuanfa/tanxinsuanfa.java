package qqai.shujujiegou.jingdiansuanfa;

import java.util.Arrays;

/**
 * 描述：贪心算法
 * @author qqai
 * @createTime 2020-9-26 10:59:35
 */
public class tanxinsuanfa {
    private int MAX_WEIGHT = 150;
    private int[] weights = new int[] { 35, 30, 60, 50, 40, 10, 24 };
    private int[] values = new int[] { 10, 40, 30, 50, 35, 40, 30 };

    public void greedypackage(int capacity, int[] wegiht, int[] values) {
        int n = wegiht.length;
        double[] r = new double[n];// 性价比数组
        int[] index = new int[n]; // 存放性价比下标
        for (int i = 0; i < n; i++) { // 求性价比并初始化
            r[i] = (double) values[i] / weights[i];
            index[i] = i; // 默认排序
        }
        // r[]对性价比进行排序(冒泡)，以及对index[]性价比下标排序
        double temp = 0;
        int x = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (r[i] < r[j]) {
                    temp = r[i];
                    r[i] = r[j];
                    r[j] = temp;
                    x = index[i];
                    index[i] = index[j];
                    index[j] = x;
                }
            }
        }
        // 利用性价比下标的排序 对原数据 wegiht，values 排序
        int[] w1 = new int[n];
        int[] v1 = new int[n];
        for (int i = 0; i < n; i++) {
            w1[i] = wegiht[index[i]];
            v1[i] = values[index[i]];
        }
        // 填充背包
        int[] y = new int[n];
        double maxvalue = 0;
        for (int i = 0; i < n; i++) {
            if (w1[i] < capacity) {
                // 还可以装得下
                y[i] = 1; // 表示该物品已经被装过了
                maxvalue += v1[i];
                System.out.println("物品:" + w1[i] + "放进了包包");
                capacity = capacity - w1[i];
            }
        }
        // 打印背包信息和最大价值
        System.out.println("总共放下的物品有:" + Arrays.toString(y));
        System.out.println("背包里面物品的总价值为:" + maxvalue);
    }

    public static void main(String[] args) {
        tanxinsuanfa greedyPackage = new tanxinsuanfa();
        greedyPackage.greedypackage(greedyPackage.MAX_WEIGHT, greedyPackage.weights, greedyPackage.values);
    }
}
