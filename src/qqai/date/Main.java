package qqai.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qqai
 * @createTime 2020/11/5 17:10
 * @description：date日期得各种转换
 */

public class Main {
    public static void main(String[] args) throws ParseException {
        // 字符串转成日期
        String s = "2020-1-5";
        // 系统和时间戳
        long l = System.currentTimeMillis();
        // 打印
        System.out.println(l);
        // 获取格式化对象
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 转换以上得字符串时间
        System.out.println(format.parse(s));
        // 根据当前系统得时间戳获取当前时间并且格式化
        System.out.println(format.format(new Date(l)));
        // 根据当前得系统时间戳获取当前时间不格式化
        System.out.println(new Date(l));
        // 根据一个事件对象获取时间戳
        long time = new Date(l).getTime();
        // 打印这个时间戳
        System.out.println(time);
    }
}
