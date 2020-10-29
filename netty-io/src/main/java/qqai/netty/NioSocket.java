package qqai.netty;

import com.sun.jdi.IntegerType;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qqai
 * @createTime 2020/10/24 18:01
 * @description：nio
 */

public class NioSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<SocketChannel> clients = new LinkedList<>();
        // 开启socket通道
        ServerSocketChannel ss = ServerSocketChannel.open();
        // 绑定一个端口9090
        ss.bind(new InetSocketAddress(9099));
        // 配置通道是否可以非阻塞
        ss.configureBlocking(false);
        while (true) {
            // 线程睡眠3秒
            TimeUnit.SECONDS.sleep(3);
            // 获取客户端  不会阻塞！！！
            SocketChannel client = ss.accept();
            // 没有阻塞的情况下，这个client可能是null的
            if (client == null) {
                System.out.println("null...");
            } else {
                // 配置客户端是非阻塞的
                client.configureBlocking(false);
                // 获取端口号
                int port = client.socket().getPort();
                // 打印
                System.out.println("client..." + port);
                // 添加到链表中
                clients.add(client);
            }
            // 缓冲区
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            // 循环表示串行化
            for (SocketChannel c : clients) {
                int num = c.read(buffer);  // 不会阻塞！！！
                // num > 0 读到数据了
                if (num > 0) {
                    // buffer指针翻转
                    // public Buffer flip() {
                    //     limit = position;
                    //     position = 0;
                    //     mark = -1;
                    //     return this;
                    // }
                    buffer.flip();
                    //  读取数据
                    byte[] aaa = new byte[buffer.limit()];
                    // 把buffer 0 - aaa.length长度的数据读取到aaa中
                    buffer.get(aaa);
                    // 转换成字符
                    String s = new String(aaa);
                    // 打印
                    System.out.println(c.socket().getPort() + ":" + s);
                    // 清空buffer  让下一个客户端使用
                    buffer.clear();
                }
            }
        }
    }
}
