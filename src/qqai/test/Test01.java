package qqai.test;

import java.util.Random;
import java.util.Scanner;

/**
 * @author qqai
 * @createTime 2020/10/15 22:49
 * @description：测试1
 */

public class Test01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long seed = 12345L;

        int xPosition = -123;
        int zPosition = -10;

        System.out.println("input seed:");
        seed = scanner.nextLong();

        Random rnd = new Random();

        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; j++) {
                xPosition = i;
                zPosition = j;
                rnd.setSeed(seed +
                        (xPosition * xPosition * 4987142) +
                        (xPosition * 0x5ac0db) +
                        (zPosition * zPosition) * 0x4307a7L +
                        (zPosition * 0x5f24f) ^ 0x3ad8025f);
                if (rnd.nextInt(10) == 0) {
                    System.out.println("i --> " + i + " j --> " + j);
                }
            }
        }
//        for (int i = -100; i <= 100; i++) {
//            System.out.println("input seed:");
//            seed = scanner.nextLong();
//            System.out.println("input xPosition:");
//            xPosition = scanner.nextInt();
//            System.out.println("input zPosition:");
//            zPosition = scanner.nextInt();
//
//            rnd.setSeed(seed +
//                    (xPosition * xPosition * 4987142) +  //永远为-
//                    (xPosition * 0x5ac0db) +
//                    (zPosition * zPosition) * 0x4307a7L +   //永远为+
//                    (zPosition * 0x5f24f) ^ 0x3ad8025f);
//
//            System.out.println(rnd.nextInt(10) == 0);
//        }
//
//
//        for (int i = 0; i < 20; i++) {
//            System.out.println(rnd.nextInt(10));
//        }
//        boolean flag = rnd.nextInt(10) == 0;
//        System.out.println(flag);
        // 输出所有满足以上等式的xPositon和yPosition
    }
}
