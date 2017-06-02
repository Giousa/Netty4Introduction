package com.zmm.netty4introduction.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/6/2
 * Time:下午2:55
 */

public class Client {

    private static final String host = "127.0.0.1";
    private static final int port = 7878;

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());
                            p.addLast(new ClientHandler());
                        }
                    });

            // 连接服务端
            Channel ch = bootstrap.connect(host, port).sync().channel();

            System.out.println("---Server connect Success---");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                System.out.println("请输入：");
                String line = in.readLine();
                if (line == null) {
                    continue;
                }

                ch.writeAndFlush(line + "\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
