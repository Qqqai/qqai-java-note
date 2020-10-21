package qqai.wb.bs;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qqai
 * @createTime 2020/10/21 20:01
 * @description：bs客户端是浏览器
 */

public class Server {
    public static void main(String[] args) throws IOException {

        // 监听8080端口
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            new Thread(() -> {
                try {
                    // 获取请求信息
                    Socket socket = serverSocket.accept();
                    // 获取输入流对象
                    InputStream inputStream = socket.getInputStream();
                    //        int len;
                    //        byte[] buffer = new byte[1024];
                    //        while ((len = inputStream.read(buffer)) != -1) {
                    //            System.out.println(new String(buffer, 0, len));
                    //        }
                    // 使用buffeedr eader读取信息
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    // 获取第一行也就是请求得方式以及地址等信息
                    String s = reader.readLine();
                    // 分隔 数组第二个元素就是url地址
                    String[] strings = s.split(" ");
                    // 找到这个路径下得请求资源
                    String path = strings[1].substring(1);
                    // 我这里有个模块名  所以这里从qqai这个包开始得
                    path = "qqai/" + path;
                    System.out.println(path);
                    // 获取流
                    FileInputStream fileInputStream = new FileInputStream(path);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                    outputStream.write("Content-Type:text/html\r\n".getBytes());
                    // 必须写入空行
                    outputStream.write("\r\n".getBytes());
                    // 读取 并写出
                    int len;
                    byte[] buffer = new byte[1024];
                    while ((len = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // 这里就不需要关闭了
//        serverSocket.close();
    }
}
