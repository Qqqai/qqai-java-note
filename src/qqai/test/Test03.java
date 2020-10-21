package qqai.test;

import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/10/21 01:05
 * @description：测试3
 */

public class Test03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            res.append("\n").append(scanner.nextLine());
        }
        System.out.println(res);
    }
}
