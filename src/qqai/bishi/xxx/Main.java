package qqai.bishi.xxx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;

/**
 * by qqai
 * 2021/1/7 16:09
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 定义服务器监听端口
        ServerSocket server = new ServerSocket(10010);
        while (true) {
            // 阻塞点
            Socket socket = server.accept();
            new Thread(() -> {
                try {
                    InputStream inputStream = socket.getInputStream();
                    // 请求都在inputStream中封装
                   /* byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        System.out.println(new String(buffer, 0, len));
                    }*/
                    // 包装成字符流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    // 读取第一行
                    String s = reader.readLine();
                    System.out.println(s);
                    // 分隔 数组第二个元素就是url地址
                    String[] strings = s.split(" ");
                    // 找到这个路径下得请求资源
                    String path = strings[1].substring(1);
                    // 分隔获取到path和参数
                    String[] split = path.split("\\?");
                    System.out.println(Arrays.toString(split));
                    // 输出流
                    OutputStream outputStream = socket.getOutputStream();
                    if (split.length > 1) {
                        // 全部参数
                        String split2 = split[1];
                        System.out.println(split2);
                        // 获取到每个参数
                        String[] params = split2.split("&");
                        HashMap<String, String> map = new HashMap<>();
                        // 把参数保存起来
                        for (String param : params) {
                            String[] x = param.split("=");
                            map.put(x[0], x[1]);
                        }
                        // 定义返回数据
                        int res = 0;
                        if (split[0].equals("add")) {
                            System.out.println("加法");
                            for (String value : map.values()) {
                                // 每个元素相加
                                res += Integer.parseInt(value);
                            }
                        } else if (split[0].equals("mult")) {
                            System.out.println("乘法");
                            res = 1;
                            for (String value : map.values()) {
                                // 每个元素相乘
                                res *= Integer.parseInt(value);
                            }
                        }
                        // 指定输出的响应格式
                        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                        outputStream.write("Content-Type:application/json\r\n".getBytes());
                        // 必须写入空行
                        outputStream.write("\r\n".getBytes());
                        // 直接写res乱码, 转换成string类型之后获取到bytes再写出
                        outputStream.write((res + "").getBytes());
                    }
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
