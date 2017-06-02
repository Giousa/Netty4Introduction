package com.zmm.netty4introduction.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/6/2
 * Time:下午2:55
 */

public class Server {

    private static final int port = 7878;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel socketChannel) throws Exception {

                             ChannelPipeline p = socketChannel.pipeline();

                             p.addLast(new StringDecoder());
                             p.addLast(new StringEncoder());
                             p.addLast(new ServerHandler());
                         }

            });

            // 服务器绑定端口监听
            Channel ch = bootstrap.bind(port).sync().channel();

            System.out.println("---Server Start---");

            ch.closeFuture().sync();

        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
