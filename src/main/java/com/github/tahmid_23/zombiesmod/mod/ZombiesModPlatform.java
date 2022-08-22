package com.github.tahmid_23.zombiesmod.mod;

import com.github.tahmid_23.zombiesmod.event.ClientJoinServerEvent;
import com.github.tahmid_23.zombiesmod.event.TickEvent;

//#if FABRIC
//$$ import net.fabricmc.api.ClientModInitializer;
//$$ import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
//#else
import com.github.tahmid_23.zombiesmod.event.RenderPlayerEvent;
import io.netty.channel.Channel;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
//#if MC>=11602
//$$ import net.minecraft.network.NetworkManager;
//$$ import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
//$$ import net.minecraftforge.eventbus.api.IEventBus;
//$$ import net.minecraftforge.eventbus.api.SubscribeEvent;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//$$ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//#else
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
//#endif
//#endif

//#if FABRIC
//#elseif MC>=11602
//$$ @Mod("zombiesmod")
//#else
@Mod(modid = "zombiesmod", name = "Zombies Mod", version = "0.0.1-SNAPSHOT", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
//#endif
public class ZombiesModPlatform
//#if FABRIC
//$$ implements ClientModInitializer
//#endif
{

    private final ZombiesMod zombiesMod = new ZombiesMod();

    public ZombiesModPlatform() {
        //#if FABRIC
        //#elseif MC>=11602
        //$$ IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //$$ eventBus.addListener(this::onFMLClientSetup);
        //#endif
    }

    //#if FABRIC
    //$$ @Override
    //$$ public void onInitializeClient() {
    //#elseif MC>=11602
    //$$ private void onFMLClientSetup(FMLClientSetupEvent event) {
    //#else
    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
    //#endif
        setupPlatformEvents();

        zombiesMod.initializeKeybinds();
        zombiesMod.initializeEvents();
    }

    private void setupPlatformEvents() {
    //#if FABRIC
    //$$     ClientTickEvents.END_CLIENT_TICK.register(client -> TickEvent.EVENT.getInvoker().run());
    //#else
        MinecraftForge.EVENT_BUS.register(new Object() {

            @SubscribeEvent
            public void onPlayerRender(net.minecraftforge.client.event.RenderPlayerEvent.Pre event) {
                AbstractClientPlayer player;
                //#if MC>=11602
                //$$ player = (AbstractClientPlayerEntity) event.getPlayer();
                //#else
                player = (AbstractClientPlayer) event.entityPlayer;
                //#endif
                if (!RenderPlayerEvent.EVENT.getInvoker().shouldRenderPlayer(player)) {
                    event.setCanceled(true);
                }
            }

            @SubscribeEvent
            public void onPlayerConnect(
                    //#if MC>=11602
                    //$$ ClientPlayerNetworkEvent.LoggedInEvent event
                    //#else
                    FMLNetworkEvent.ClientConnectedToServerEvent event
                    //#endif
            ) {
                Channel channel;
                //#if MC>=11602
                //$$ NetworkManager networkManager = event.getNetworkManager();
                //$$ if (networkManager == null) {
                //$$     return;
                //$$ }
                //$$ channel = networkManager.channel();
                //#else
                channel = event.manager.channel();
                //#endif

                ClientJoinServerEvent.EVENT.getInvoker().onJoin(channel);
            }

            @SubscribeEvent
            public void onClientTick(net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent event) {
                if (event.phase == net.minecraftforge.fml.common.gameevent.TickEvent.Phase.END) {
                    TickEvent.EVENT.getInvoker().run();
                }
            }

        });
    //#endif
    }

}
