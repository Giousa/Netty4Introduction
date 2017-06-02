package com.zmm.netty4introduction.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/6/2
 * Time:下午2:56
 */

public abstract class CommonHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("---" + ctx.channel().remoteAddress() + " 收到消息 = "+s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("---" + ctx.channel().remoteAddress() + " 活跃中...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("---" + ctx.channel().remoteAddress() + " 闲置中...");
    }

}
