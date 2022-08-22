//#if FABRIC
//$$ package com.github.tahmid_23.zombiesmod.mixin;
//$$
//$$ import com.github.tahmid_23.zombiesmod.event.RenderPlayerEvent;
//$$ import net.minecraft.client.network.AbstractClientPlayerEntity;
//$$ import net.minecraft.client.render.VertexConsumerProvider;
//$$ import net.minecraft.client.render.entity.PlayerEntityRenderer;
//$$ import net.minecraft.client.util.math.MatrixStack;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$
//$$ @Mixin(PlayerEntityRenderer.class)
//$$ abstract class PlayerEntityRendererMixin {
//$$
//$$     @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"), cancellable = true)
//$$     public void render(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo callbackInfo) {
//$$         if (!RenderPlayerEvent.EVENT.getInvoker().shouldRenderPlayer(abstractClientPlayerEntity)) {
//$$             callbackInfo.cancel();
//$$         }
//$$     }
//$$
//$$ }
//#endif
