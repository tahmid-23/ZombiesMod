package com.github.tahmid_23.zombiesmod.network;

import com.github.tahmid_23.zombiesmod.event.Event;
import com.github.tahmid_23.zombiesmod.event.PacketEvents;
import io.netty.channel.*;
import net.minecraft.network.Packet;

public class PacketChannelHandler {

    private PacketChannelHandler() {

    }

    public static ChannelHandler forEvents(Event<PacketEvents.PacketInListener> packetReadEvent, Event<PacketEvents.PacketOutListener> packetWriteEvent) {
        return new CombinedChannelDuplexHandler<>(new SimpleChannelInboundHandler<Packet<?>>() {
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, Packet<?> packet) throws Exception {
                if (!packetReadEvent.getInvoker().readPacket(ctx, packet)) {
                    ctx.fireChannelRead(packet);
                }
            }
        }, new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                if (!(msg instanceof Packet<?>)) {
                    super.write(ctx, msg, promise);
                    return;
                }

                Packet<?> packet = (Packet<?>) msg;
                if (!packetWriteEvent.getInvoker().writePacket(ctx, packet, promise)) {
                    ctx.write(packet, promise);
                }
            }
        });
    }

}
