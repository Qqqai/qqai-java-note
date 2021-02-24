package qqai.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 继承自SimpleChannelInboundHandler泛型TextWebSocketFrame表示从socket接收到的消息会封装到一个Frame对象中去
 * by qqai
 * 2021/2/23 19:09
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 保存所有的客户端连接
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 日期格式化器
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/HH/mm/ss");

    /**
     * 给通道添加这个handler之后通道收到消息 会自动调用这个方法
     *
     * @param ctx 通道上下文
     * @param msg 接收到的消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端发送来的文本消息
        String text = msg.text();
        // 打印
        System.out.println("接收到的消息是:" + text);
        // 将消息发送给所有的客户端
        for (Channel client : clients) {
            // 写入并刷新客户端
            client.writeAndFlush(new TextWebSocketFrame(sdf.format(new Date()) + ":" + text));
        }
    }

    /**
     * 把客户端全部加到clients中
     *
     * @param ctx 通道上下文
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }
}
