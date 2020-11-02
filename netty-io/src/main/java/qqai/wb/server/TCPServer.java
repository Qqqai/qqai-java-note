package qqai.wb.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qqai
 * @createTime 2020/10/21 16:26
 * @description：tcp得服务器
 */

public class TCPServer {
    public static void main(String[] args) throws IOException {
        // 创捷一个指定得服务器套接字类 端口是8888；
        ServerSocket server = new ServerSocket(8888);
        // 用这个方法获取到请求得客户端得socket对象
        Socket socket = server.accept();
        // 使用请求得socket获取到网络请求中得输入流对象
        InputStream inputStream = socket.getInputStream();
        // 使用网络字节输入流得read方法，读取客户端发送得数据
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) != -1) {
            System.out.println(new String(buffer, 0, buffer.length));
        }
        // 回写数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好客户端".getBytes());
        // 刷新
        outputStream.flush();

        socket.close();
        server.close();
    }
}
