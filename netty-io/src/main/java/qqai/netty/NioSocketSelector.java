package qqai.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qqai
 * @createTime 2020/10/24 18:01
 * @description：多路复用
 */

public class NioSocketSelector {
    // socket通道
    private ServerSocketChannel server = null;
    // 多路复用器
    private Selector selector = null;
    // 端口
    int port = 9098;

    public void initServer() {
        try {
            // 初始化一个socket通道
            server = ServerSocketChannel.open();
            // 配置阻塞状态
            server.configureBlocking(false);
            // 绑定端口
            server.bind(new InetSocketAddress(port));
            // 初始化一个多路复用器
            selector = Selector.open();
            // 把server注册到selector里，并且只要未来通道出现有客户端建立连接，就通知server
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServer();
        System.out.println("服务器启动了...");
        try {
            while (true) {
                // 这里是查询内核有没有产生事件
                while (selector.select(0) > 0) {
                    // 从多路复用器取出有效的链接
                    Set<SelectionKey> keys = selector.selectedKeys();
                    // 拿到迭代器
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        // 拿到一个有效的key
                        SelectionKey key = iterator.next();
                        // 从set中删除
                        iterator.remove();
                        // 如果事件是获取客户端
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else
                            // 是否可读取
                            if (key.isReadable()) {
                                readHandler(key);
                            }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey key) throws IOException {
        // 这个key就是客户端的key 保存的有客户端的通道和buffer数组
        SocketChannel client = (SocketChannel) key.channel();
        // 拿到buffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        // 清空这个buffer 防止有脏数据
        buffer.clear();
        int read;
        while (true) {
            // 开始读取
            read = client.read(buffer);
            // 如果read大于0 表示读到东西了
            if (read > 0) {
                // 反转buffer
                buffer.flip();
                // 读取的条件
                while (buffer.hasRemaining()) {
                    // 通过客户端写出去
                    client.write(buffer);
                }
                // 清空buffer
                buffer.clear();
            } else
                // 如果读到0表示没有数据不用做任何事情
                if (read == 0) {
                    break;
                } else {
                    // 表示读到-1了  读到-1表示客户端进入了 close_wait状态，如果不关闭客户端就会进入死循环  cou炸掉
                    System.out.println("客户端：" + client.getLocalAddress() + "关闭");
                    System.out.println("-----------------------------------------------------------");
                    client.close();
                    break;
                }
        }

    }

    private void acceptHandler(SelectionKey key) throws IOException {
        // 注册的时候是SelectionKey register = server.register(selector, SelectionKey.OP_ACCEPT);
        // 这个方法的返回值是SelectionKey所以这个key是包含server和selector的
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        // 获取客户端
        SocketChannel socket = ssc.accept();
        // 设置非阻塞
        socket.configureBlocking(false);
        // 分配一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
        // 把客户端也注册到多路复用器上，并且把buffer也添加进去，这一步就是以后在获取的时候可以随意的取出client和buffer
        socket.register(selector, SelectionKey.OP_READ, buffer);
        // 打印
        System.out.println("-----------------------------------------------------------");
        System.out.println("新客户端：" + socket.getRemoteAddress());
        System.out.println("-----------------------------------------------------------");
    }

    public static void main(String[] args) {
        new NioSocketSelector().start();
    }
}
