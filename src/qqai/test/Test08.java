package qqai.test;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 打印设备信息 jvm信息 等各类信息
 *
 * @author qqai
 * @createTime 2020/12/31 17:15
 */
public class Test08 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> {
            System.out.println(k.toString() + " = " + v.toString());
        });
    }
}
