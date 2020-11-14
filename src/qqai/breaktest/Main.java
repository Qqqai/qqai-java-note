package qqai.breaktest;

/**
 * 描述：break标志位的测试
 *
 * @author qqai
 * @createTime 2020-09-16 11:29
 */

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            ok:
            //笔记 这个是break的标志位 在哪个for上面 break的后面加上这个标志为就表示在哪个for结束
            for (int j = 0; j < 200; j++) {
                if (i == 10 && j == 20) {
                    System.out.println(i + ", " + j);
                    break ok;
                }
            }
            System.out.println("11111");
        }
    }
}
