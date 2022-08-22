package com.github.tahmid_23.zombiesmod.event;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.Packet;

public class PacketEvents {

    private PacketEvents() {
        throw new UnsupportedOperationException();
    }

    public static final Event<PacketInListener> PACKET_IN = new Event<>(listeners -> (ctx, packet) -> {
        for (PacketInListener listener : listeners) {
            if (listener.readPacket(ctx, packet)) {
                return true;
            }
        }

        return false;
    });

    public static final Event<PacketOutListener> PACKET_OUT = new Event<>(listeners -> (ctx, packet, promise) -> {
        for (PacketOutListener listener : listeners) {
            if (listener.writePacket(ctx, packet, promise)) {
                return true;
            }
        }

        return false;
    });

    public interface PacketInListener {

        boolean readPacket(ChannelHandlerContext ctx, Packet<?> packet);

    }

    public interface PacketOutListener {

        boolean writePacket(ChannelHandlerContext ctx, Packet<?> packet, ChannelPromise promise);

    }

}
