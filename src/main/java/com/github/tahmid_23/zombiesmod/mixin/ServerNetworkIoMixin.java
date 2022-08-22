//#if FABRIC
//$$ package com.github.tahmid_23.zombiesmod.mixin;
//$$
//$$ import com.github.tahmid_23.zombiesmod.event.ClientJoinServerEvent;
//$$ import com.github.tahmid_23.zombiesmod.event.PacketEvents;
//$$ import com.github.tahmid_23.zombiesmod.network.PacketChannelHandler;
//$$ import io.netty.channel.*;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$
//$$ @Mixin(targets = {"net.minecraft.server.ServerNetworkIo$1", "net.minecraft.server.ServerNetworkIo$2"})
//$$ abstract class ServerNetworkIoMixin {
//$$
//$$     @Inject(method = "initChannel(Lio/netty/channel/Channel;)V", at = @At("TAIL"))
//$$     private void onInitChannel(Channel channel, CallbackInfo callbackInfo) {
//$$         ClientJoinServerEvent.EVENT.getInvoker().onJoin(channel);
//$$     }
//$$
//$$ }
//#endif