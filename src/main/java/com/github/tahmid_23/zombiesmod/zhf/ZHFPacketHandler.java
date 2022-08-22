package com.github.tahmid_23.zombiesmod.zhf;

import com.github.tahmid_23.zombiesmod.chat.Components;
import com.github.tahmid_23.zombiesmod.event.PacketEvents;
import com.github.tahmid_23.zombiesmod.util.Toggleable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.EnumChatFormatting;

//#if MC>=11802
//$$ import com.github.tahmid_23.zombiesmod.mixin.PlayerInteractEntityC2SPacketAccessor;
//#endif
//#if MC>=11602
//$$ import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
//$$ import net.minecraft.util.Hand;
//#else
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
//#endif

import java.util.Objects;

public class ZHFPacketHandler implements PacketEvents.PacketOutListener, Toggleable {

    private static final IChatComponent DISPLAY_NAME = Components.text("ZHF", EnumChatFormatting.GOLD);

    private final Minecraft minecraft;

    private boolean enabled = true;

    public ZHFPacketHandler(Minecraft minecraft) {
        this.minecraft = Objects.requireNonNull(minecraft, "minecraft");
    }

    @Override
    public boolean writePacket(ChannelHandlerContext ctx, Packet<?> msg, ChannelPromise promise) {
        if (isEnabled() && msg instanceof C02PacketUseEntity) {
            C02PacketUseEntity packet = (C02PacketUseEntity) msg;
            C02PacketUseEntity.Action interactionType;
            //#if MC>=11802
            //$$ PlayerInteractEntityC2SPacketAccessor accessor = (PlayerInteractEntityC2SPacketAccessor) packet;
            //$$ interactionType = accessor.getType().getType();
            //#else
            interactionType = packet.getAction();
            //#endif
            if (interactionType != C02PacketUseEntity.Action.ATTACK) {
                WorldClient world = minecraft.theWorld;
                if (world != null) {
                    Entity clicked;
                    //#if MC>=11802
                    //$$ clicked = world.getEntityById(accessor.getEntityId());
                    //#else
                    clicked = packet.getEntityFromWorld(world);
                    //#endif
                    if (clicked instanceof EntityArmorStand) {
                        //#if MC>=11602
                        //$$ ctx.write(new CPlayerTryUseItemPacket(Hand.MAIN_HAND), promise);
                        //#else
                        ctx.write(new C08PacketPlayerBlockPlacement(minecraft.thePlayer.inventory.getCurrentItem()), promise);
                        //#endif
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public IChatComponent getDisplayName() {
        return DISPLAY_NAME;
    }
}