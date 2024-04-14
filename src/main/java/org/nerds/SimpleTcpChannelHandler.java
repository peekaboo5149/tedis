package org.nerds;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleTcpChannelHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {

        RESPParser parser = new RESPParser();
        var resp = parser.parse(msg);
        System.out.printf("<%s> type = %s, commands = %s\n", ctx.channel().remoteAddress(), resp.type(), resp.commands());
        var executor = new CommandExecutor();
        ctx.writeAndFlush(executor.execute(resp));
        ctx.fireChannelActive();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.printf("<%s> Active\n", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.printf("<%s> Inactive\n", ctx.channel().remoteAddress());
    }
}
