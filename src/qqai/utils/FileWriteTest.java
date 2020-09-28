package qqai.utils;

import java.io.*;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/9/28 16:30
 * @description：写入
 */

public class FileWriteTest {
    public void bufferRead(Object into, String path, String filename) throws IOException {
        File f = new File(path + "/" + filename);
        FileOutputStream fop = new FileOutputStream(f);
        // 构建FileOutputStream对象,文件不存在会自动新建
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
        writer.append((CharSequence) into);
        // 写入到缓冲区
        writer.append("\r\n");
        // 换行
//        writer.append("English");
        // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入
        writer.close();
        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
        fop.close();
        // 关闭输出流,释放系统资源
    }
}
