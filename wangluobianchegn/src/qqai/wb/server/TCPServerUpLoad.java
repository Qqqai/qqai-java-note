package qqai.wb.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qqai
 * @createTime 2020/10/21 19:29
 * @description：上传
 */

public class TCPServerUpLoad {
    public static void main(String[] args) throws IOException {
        // 创建服务器
        ServerSocket serverSocket = new ServerSocket(8888);
        // 获取请求得客户端对象
        Socket socket = serverSocket.accept();
        // 获取输入流
        InputStream inputStream = socket.getInputStream();
        // 拿到上传文件夹
        File file = new File("C:\\Users\\2b\\Desktop\\upload");
        // 判断这个文件夹是否存在
        if (!file.exists()) {
            file.mkdir();
        }
        // 得到当前文件夹得输出流
        FileOutputStream fileOutputStream = new FileOutputStream(file + "/yyxn.jpg");
        // 从网络输入字节流获取文件流信息 并且写到当前文件夹里
        int len;
        byte[] buffer = new byte[1024];
        // 从本地文件流按照指定长度读取字节流
        while ((len = inputStream.read(buffer)) != -1) {
            // 从网络字节输入流获取文件
            fileOutputStream.write(buffer, 0, len);
        }

        // 上传成功 向客户端回写一个确定
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("上传成功".getBytes());

        fileOutputStream.close();
        // 关闭请求
        socket.close();
        // 关闭服务器
        serverSocket.close();
    }
}
