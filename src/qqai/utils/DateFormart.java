package qqai.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qqai
 * @createTime 2020/10/2 23:43
 * @description：
 */

public class DateFormart {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(new Date());
        System.out.println(s);
    }
}
