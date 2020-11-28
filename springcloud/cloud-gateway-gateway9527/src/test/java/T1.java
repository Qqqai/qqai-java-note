import java.time.ZonedDateTime;

/**
 * @author qqai
 * @createTime 2020/11/28 15:59
 * @description：笔记 获取当前地区的时间标志
 */

public class T1 {
    public static void main(String[] args) {
        ZonedDateTime time = ZonedDateTime.now();
        System.out.println(time);
        //2020-11-28T15:59:50.790300900+08:00[Asia/Shanghai]
    }
}
