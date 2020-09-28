package qqai.utils;

import java.io.*;

/**
 * @author qqai
 * @createTime 2020/9/28 16:36
 * @description：读取
 */

public class FileReadUtils {
    public void read(String path, String filename) throws IOException {
        File f = new File(path + "/" + filename);
        FileInputStream fip = new FileInputStream(f);
        // 构建FileInputStream对象
        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        // 构建InputStreamReader对象,编码与写入相同
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append((char) reader.read());
            // 转成char加到StringBuffer对象中
        }


        System.out.println(sb.toString());


        reader.close();
        // 关闭读取流
        fip.close();
        // 关闭输入流,释放系统资源
    }
}
