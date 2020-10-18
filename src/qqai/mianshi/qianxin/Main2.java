package qqai.mianshi.qianxin;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/10/18 15:24
 * @description：奇安信
 */

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n >= 1 && n <= 200) {
            BigInteger integer = new BigInteger("1");
            while (n > 0) {
                integer = integer.multiply(new BigInteger(String.valueOf(n)));
                n--;
            }
            System.out.println(integer);
        } else {
            System.out.println("Error");
        }
    }
}
