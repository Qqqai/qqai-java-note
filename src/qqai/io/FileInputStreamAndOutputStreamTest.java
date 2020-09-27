package qqai.io;

import java.io.*;

/**
 * @author qqai
 * @createTime 2020/9/27 20:27
 * @description：文件流测试
 */

public class FileInputStreamAndOutputStreamTest {
    public static void main(String[] args) throws IOException {
        byte[] bytes = {1, 21, 31, 31};
        System.out.println("用户的当前工作目录:" + System.getProperty("user.dir"));
        FileOutputStream outputStream = new FileOutputStream("qqai\\src\\qqai\\io\\Test.txt", true);
        for (byte aByte : bytes) {
            outputStream.write(aByte);
        }
        outputStream.close();

        FileInputStream inputStream = new FileInputStream(new File("qqai\\src\\qqai\\io\\Test.txt"));
        byte[] in = new byte[32];
        int size = inputStream.available();
        for (int i = 0; i < size; i++) {
            System.out.println((char) inputStream.read());
        }
        inputStream.close();
    }
}
