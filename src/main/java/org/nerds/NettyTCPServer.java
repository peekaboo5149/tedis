package org.nerds;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyTCPServer implements TCPServer {

    private static final EventLoopGroup masterGroup = new NioEventLoopGroup();
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup();


    @Override
    public void start(int port) throws Exception {
        System.out.println("Server started at: " + port);
        ServerBootstrap b = new ServerBootstrap();
        b.group(masterGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleTcpChannelInitializer())
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        ChannelFuture cf = b.bind(port).sync();
        if (cf.isSuccess()) System.out.println("Server started successfully!!!");
        cf.channel().closeFuture().sync();

    }

    @Override
    public void close() {
        System.out.println("Server stopped successfully!!!");
        masterGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
