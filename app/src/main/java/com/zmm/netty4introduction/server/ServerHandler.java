package com.zmm.netty4introduction.server;

import com.zmm.netty4introduction.handler.CommonHandler;

import io.netty.channel.ChannelHandlerContext;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/6/2
 * Time:下午3:02
 */

public class ServerHandler extends CommonHandler {

    private int count = 1;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("---Server 收到客户端消息 = "+s);

        ctx.writeAndFlush("服务端返回信息"+count++);

    }
}
