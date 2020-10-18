package qqai.mianshi.meituan;

import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/10/18 10:58
 * @description：美团3 小美和小团所在公司的食堂有N张餐桌，从左到右摆成一排，每张餐桌有2张餐椅供至多2人用餐，公司职员排队进入食堂用餐。
 * <p>
 * 小美发现职员用餐的一个规律并告诉小团：当男职员进入食堂时，他会优先选择已经坐有1人的餐桌用餐，只有当每张餐桌要么空着要么坐满2人时，他才会考虑空着的餐桌；当女职员进入食堂时，她会优先选择未坐人的餐桌用餐，只有当每张餐桌都坐有至少1人时，她才会考虑已经坐有1人的餐桌；无论男女，当有多张餐桌供职员选择时，他会选择最靠左的餐桌用餐。
 * <p>
 * 现在食堂内已有若干人在用餐，另外M个人正排队进入食堂，小团会根据小美告诉他的规律预测排队的每个人分别会坐哪张餐桌。
 * <p>
 * <p>
 * <p>
 * 输入描述
 * 第一行输入一个整数T（1<=T<=10），表示数据组数。
 * <p>
 * 每组数据占四行，第一行输入一个整数N（1<=N<=500000）；
 * <p>
 * 第二行输入一个长度为N且仅包含数字0、1、2的字符串，第i个数字表示左起第i张餐桌已坐有的用餐人数；
 * <p>
 * 第三行输入一个整数M（1<=M<=2N且保证排队的每个人进入食堂时都有可供选择的餐桌）；
 * <p>
 * 第四行输入一个长度为M且仅包含字母M、F的字符串，若第 i 个字母为M，则排在第 i 的人为男性，否则其为女性。
 * <p>
 * 输出描述
 * 每组数据输出占M行，第 i 行输出一个整数 j （1<=j<=N），表示排在第 i 的人将选择左起第 j 张餐桌用餐。
 * <p>
 * <p>
 * 样例输入
 * 1
 * 5
 * 01102
 * 6
 * MFMMFF
 * 样例输出
 * 2
 * 1
 * 1
 * 3
 * 4
 * 4
 */

public class MeiTuan3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//数据组

        //有多少组数据就执行多少次
        for (int i = 0; i < n; i++) {
            int z = scanner.nextInt();//这个数据组的桌子数量
            int[] ints = new int[z]; //每张桌子已经有多少人了
            String next = scanner.next();
            for (int j = 0; j < ints.length; j++) {
                ints[i] = Integer.parseInt(String.valueOf(next.charAt(j)));
            }
            int p = scanner.nextInt();//多少个人
            char[] chars = new char[p];
            String next1 = scanner.next();
            for (int j = 0; j < chars.length; j++) {
                chars[i] = next1.charAt(i);
            }
            get(ints, chars);
        }
    }

    private static void get(int[] z, char[] chars) {
        for (char p : chars) {
            if (p == 'F') {
                boolean flag = false;
                for (int i = 0; i < z.length; i++) {
                    if (z[i] == 0) {
                        z[i] = 1;
                        System.out.println(i + 1);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    for (int i = 0; i < z.length; i++) {
                        if (z[i] != 2) {
                            z[i] = 2;
                            System.out.println(i + 1);
                            break;
                        }
                    }
                }
            }
            if (p == 'M') {
                boolean flag = false;
                for (int i = 0; i < z.length; i++) {
                    if (z[i] == 1) {
                        z[i] = 2;
                        flag = true;
                        System.out.println(i + 1);
                        break;
                    }
                }
                if (!flag) {
                    for (int i = 0; i < z.length; i++) {
                        if (z[i] == 0) {
                            z[i] = 1;
                            System.out.println(i + 1);
                            break;
                        }
                    }
                }
            }
        }
    }
}
