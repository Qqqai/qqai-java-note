package qqai.wb.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author qqai
 * @createTime 2020/10/21 15:42
 * @description：网络编程
 */

public class TCPClient {
    public static void main(String[] args) throws IOException {
        // new一个socket对象，指定一个服务器得ip和服务器的port
        // java.net.ConnectException: Connection refused: connect 没有服务器就会抛出这个异常
        Socket socket = new Socket("127.0.0.1", 8888);
        // 从socket中获取网络io得输出流对象
        OutputStream outputStream = socket.getOutputStream();
        // 使用网络字节输出流得read方法回写服务器得数据
        outputStream.write("你好服务器".getBytes());
        // 刷新stream对象
        outputStream.flush();
        // 加上这个不然服务器read拿不到-1
        socket.shutdownOutput();
//        outputStream.close();
        // 获取服务器发送的数据
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) != -1) {
            System.out.println(new String(buffer, 0, buffer.length));
        }
        // 关闭输入流
        socket.close();
    }
}
