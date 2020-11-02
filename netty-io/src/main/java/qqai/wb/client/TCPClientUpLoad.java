package qqai.wb.client;

import java.io.*;
import java.net.Socket;

/**
 * @author qqai
 * @createTime 2020/10/21 19:07
 * @description：tcp客户端上传图片
 */

public class TCPClientUpLoad {
    public static void main(String[] args) throws IOException {
        // 读取本地文件
        File file = new File("D:\\视频图片音频\\视频\\原野小年\\图片\\原野小年 - 宽松背心\\gxflzh.com (23).jpg");
        // 获取本地文件得读取流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 创建socket客户端
        Socket socket = new Socket("127.0.0.1", 8888);
        // 获取网络输出流
        OutputStream outputStream = socket.getOutputStream();
        int len;
        byte[] buffer = new byte[1024];
        // 从本地文件流按照指定长度读取字节流
        while ((len = fileInputStream.read(buffer)) != -1) {
            // 用网络上传流把本地得文件流上传到网络上
            outputStream.write(buffer, 0, len);
            // 刷新上传流
//            outputStream.flush();
        }
        // 加上一个标志位
        socket.shutdownOutput();
        /*
            笔记 为什么要加这个标志位：
            笔记     因为在本地读取这个文件之后，这个文件后面得-1是没有被读取到的，也就是本地文件默认得结束标志位没了，
            笔记     socket就提供了这样一个方法void shutdownOutput() 禁用此套接字的输出流。
            笔记     对于TCP套接字，任何先前写入的数据将被发送，随后是TCP的正常连接终止序列。
         */

        // 获取服务器得响应
        InputStream inputStream = socket.getInputStream();
        // 从本地文件流按照指定长度读取字节流
        while ((len = inputStream.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, len));
        }

        fileInputStream.close();
        socket.close();
    }
}
