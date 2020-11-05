package qqai.leetcode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：踩方格
 *
 * @author qqai
 * @createTime 2020-09-17 17:02
 */

public class CaiFangGe {
    public static void main(String[] args) {
//        int i = getShuWeiZhiHe(120, 320);
//        System.out.println(i);
    }

    //获取数位之和的方法
    private static int getShuWeiZhiHe(int i, int j) {
        int res = 0;
        while (i > 0) {
            res += i % 10;
            i /= 10;
        }
        while (j > 0) {
            res += j % 10;
            j /= 10;
        }
        return res;
    }
}
