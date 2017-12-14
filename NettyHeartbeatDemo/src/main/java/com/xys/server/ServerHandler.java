package com.xys.server;

import com.xys.common.CustomHeartbeatHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * @author xiongyongshun
 * @version 1.0
 * @email yongshun1228@gmail.com
 * @created 16/9/18 13:08
 */
public class ServerHandler extends CustomHeartbeatHandler {
    public ServerHandler() {
        super("server");
    }

    @Override
    protected void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf buf) {
        int i = 0;
        String inStr = "";

        byte[] result1 = new byte[buf.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        buf.readBytes(result1);
        String resultStr = new String(result1);
        System.out.println("Client said:" + resultStr);
        // 释放资源，这行很关键
        buf.release();
        while (buf.isReadable()) {
            i = i + 1;
            System.out.println(buf.readByte());
            System.out.println("i === " + i);
        }

        System.out.println("server received:::" + inStr);
    /*    for(int i = 0; i< buf.capacity();i++) {
            byte b = buf.getByte(0);
            System.out.println(b);
            if (b == 0x01) {
                System.out.println("lalala");
            }
        }*/

      /*  System.out.println("buf:::"+buf.toString());
        byte[] data = new byte[buf.readableBytes() - 5];*/
        //ByteBuf responseBuf = Unpooled.copiedBuffer(buf);
        byte[] myByte = new byte[3];
        myByte[0] = 0x1a;
        myByte[1] = 0x1b;
        myByte[2] = 0x1c;
     /*   buf.skipBytes(5);
        buf.readBytes(data);
        String content = new String(data);
        System.out.println(name + " get content: " + content);*/
        String response = "mylove";
        ByteBuf encoded = channelHandlerContext.alloc().buffer(4 * response.length());
        //encoded.writeBytes(response.getBytes());
        encoded.writeBytes(response.getBytes());
        channelHandlerContext.writeAndFlush(encoded);

        //channelHandlerContext.write(myByte);
    }

    protected void handleData(ChannelHandlerContext context, String data){
        String response = data;
        ByteBuf encoded = context.alloc().buffer(4 * response.length());
        //encoded.writeBytes(response.getBytes());
        encoded.writeBytes(response.getBytes());
        context.writeAndFlush(encoded);
    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        super.handleReaderIdle(ctx);
        System.err.println("---client " + ctx.channel().remoteAddress().toString() + " reader timeout, close it---");
        ctx.close();
    }
}