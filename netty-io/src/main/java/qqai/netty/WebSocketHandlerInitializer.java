package qqai.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 通道初始化器 用来加载通道处理器(ChannelHandler)
 * by qqai
 * 2021/2/23 18:55
 */
public class WebSocketHandlerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 初始化通道  在这个方法中去加载对应的ChannelHandler
     *
     * @param ch 通道
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) {
        // 获取通道, 将一个一个的ChannelHandler添加到通道中
        ChannelPipeline pipeline = ch.pipeline();

        /** 标记 对于http固定需要添加的几个处理器 **/
        // 给通道添加一个http请求的编解码器         给通道添加大数据流的支持        添加一个聚合器,聚合器的主要作用是将HttpMassage聚合成FullHttpRequest/Response
        pipeline.addLast(new HttpServerCodec(), new ChunkedWriteHandler(), new HttpObjectAggregator(1024 * 60));
        /** 标记 对于http固定需要添加的几个处理器 **/

        // 指定接受请求的路由必须以ws为后缀的请求才能访问
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 添加自定义处理器
        pipeline.addLast(new ChatHandler());
    }
}
