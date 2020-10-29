package qqai.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.sql.Time;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qqai
 * @createTime 2020/10/28 00:35
 * @description：boss-workers模式
 */

public class NioSocketMultipaleXingThreads {
    // socket通道
    private ServerSocketChannel server = null;
    // 多路复用器
    private Selector selector1 = null;
    private Selector selector2 = null;
    private Selector selector3 = null;
    // 端口
    int port = 9098;

    public void initServer() throws IOException {
        // 初始化一个socket通道
        server = ServerSocketChannel.open();
        // 配置阻塞状态
        server.configureBlocking(false);
        // 绑定端口
        server.bind(new InetSocketAddress(port));
        // 初始化一个多路复用器
        selector1 = Selector.open();
        selector2 = Selector.open();
        selector3 = Selector.open();
        // 把server注册到selector1里，并且只要未来通道出现有客户端建立连接，就通知server
        server.register(selector1, SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioSocketMultipaleXingThreads service = new NioSocketMultipaleXingThreads();
        service.initServer();
        NioThread T1 = new NioThread(service.selector1, 2);
        NioThread T2 = new NioThread(service.selector2);
        NioThread T3 = new NioThread(service.selector3);
        T1.start();
        TimeUnit.SECONDS.sleep(1);
        T2.start();
        T3.start();
        System.out.println("服务启动了...");
    }
}

class NioThread extends Thread {
    Selector selector = null;
    static int selectors = 0;
    int id = 0;
    boolean boss = false;
    static BlockingDeque<SocketChannel>[] queue;
    static AtomicInteger idx = new AtomicInteger();

    NioThread(Selector sel, int n) {
        this.selector = sel;
        selectors = n;
        boss = true;
        queue = new LinkedBlockingDeque[n];
        for (int i = 0; i < n; i++) {
            queue[i] = new LinkedBlockingDeque<>();
        }
        System.out.println("boss启动...");
    }

    public NioThread(Selector selector) {
        this.selector = selector;
        id = idx.getAndIncrement() % selectors;
        System.out.println("worker:" + id + "---启动");
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 这里是查询内核有没有产生事件
                while (selector.select(10) > 0) {
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
                // boss不参与读
                if (!queue[id].isEmpty() && !boss) {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
                    SocketChannel client = queue[id].take();
                    client.register(selector, SelectionKey.OP_READ, buffer);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("新客户端：" + client.getRemoteAddress() + "被分配到了" + id + "workers");
                    System.out.println("-----------------------------------------------------------");
                }
            }
        } catch (IOException | InterruptedException e) {
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
                    idx.getAndDecrement();
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
        // 把这个请求分配到队列中去
        int num = idx.getAndIncrement() % selectors;
        queue[num].add(socket);
    }
}