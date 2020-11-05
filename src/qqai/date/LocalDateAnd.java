package qqai.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author qqai
 * @createTime 2020/11/5 17:18
 * @description：jdk得localdate等类得学习
 */

public class LocalDateAnd {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(new Date(l));
        System.out.println(s);
        LocalDate date = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime dateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalTime time = LocalTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(date);
        System.out.println(dateTime);
        System.out.println(time);

        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(now);
    }
}
