package qqai.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty服务器
 * by qqai
 * 2021/2/23 18:50
 */
public class NettyServer {
    public static void main(String[] args) {
        // 主线程池
        NioEventLoopGroup mainGrp = new NioEventLoopGroup();
        // 从线程池
        NioEventLoopGroup subGrp = new NioEventLoopGroup();
        try {
            // 创建netty服务器的启动对象
            ServerBootstrap server = new ServerBootstrap();
            // 初始化启动对象
            server
                    // 指定创建的两个线程池
                    .group(mainGrp, subGrp)
                    // 指定netty使用的通道类型
                    .channel(NioServerSocketChannel.class)
                    // 指定通道初始化加载器来加载当channel收到事件消息后如何进行业务处理
                    .childHandler(new WebSocketHandlerInitializer());
            // 绑定服务器端口 已同步的方式启动服务
            ChannelFuture future = server.bind(9090);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭服务器
            mainGrp.shutdownGracefully();
            subGrp.shutdownGracefully();
        }

    }
}
