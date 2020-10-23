package qqai.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author qqai
 * @createTime 2020/9/27 20:03
 * @description：测试io流
 */

public class BufferReaderTest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = reader.read();//笔记 这个方法只会读取一个字符！！！  a  == 97   ab == 97  这样子
        System.out.println(i);
        String line = reader.readLine();  //笔记 这个方法是读读取一行字符串  读取到\n 换行符表示一行结束
        System.out.println(line);
    }
}
