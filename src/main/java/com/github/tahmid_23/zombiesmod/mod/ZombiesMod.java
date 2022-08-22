package com.github.tahmid_23.zombiesmod.mod;

import com.github.tahmid_23.zombiesmod.event.ClientJoinServerEvent;
import com.github.tahmid_23.zombiesmod.event.TickEvent;
import com.github.tahmid_23.zombiesmod.event.PacketEvents;
import com.github.tahmid_23.zombiesmod.event.RenderPlayerEvent;
import com.github.tahmid_23.zombiesmod.keybind.ToggleKeybindingAction;
import com.github.tahmid_23.zombiesmod.network.PacketChannelHandler;
import com.github.tahmid_23.zombiesmod.render.PlayerRenderTester;
import com.github.tahmid_23.zombiesmod.zhf.ZHFPacketHandler;
import io.netty.channel.ChannelHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

//#if MC >= 11602
//$$ import org.lwjgl.glfw.GLFW;
//#else
import org.lwjgl.input.Keyboard;
//#endif
//#if FABRIC
//$$ import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
//#else
import net.minecraftforge.fml.client.registry.ClientRegistry;
//#endif

public class ZombiesMod {

    private final KeyBinding playerVisibilityKeybind = new KeyBinding("Toggle PlayerVisibility",
            //#if MC >= 11602
            //$$ GLFW.GLFW_KEY_Z,
            //#else
            Keyboard.KEY_Z,
            //#endif
            "Zombies Mod");

    private final KeyBinding zhfKeybind = new KeyBinding("Toggle ZHF",
            //#if MC >= 11602
            //$$ GLFW.GLFW_KEY_J,
            //#else
            Keyboard.KEY_J,
            //#endif
            "Zombies Mod");

    public void initializeKeybinds() {
        //#if FABRIC
        //$$ KeyBindingHelper.registerKeyBinding(playerVisibilityKeybind);
        //$$ KeyBindingHelper.registerKeyBinding(zhfKeybind);
        //#else
        ClientRegistry.registerKeyBinding(playerVisibilityKeybind);
        ClientRegistry.registerKeyBinding(zhfKeybind);
        //#endif
    }

    public void initializeEvents() {
        Minecraft minecraft = Minecraft.getMinecraft();

        ClientJoinServerEvent.EVENT.register(channel -> {
            ChannelHandler channelHandler = PacketChannelHandler.forEvents(PacketEvents.PACKET_IN, PacketEvents.PACKET_OUT);
            channel.pipeline().addBefore("packet_handler", "zombies_mod", channelHandler);
        });
        PlayerRenderTester playerRenderTester = new PlayerRenderTester(minecraft, 3.0D);
        RenderPlayerEvent.EVENT.register(playerRenderTester);
        ZHFPacketHandler zhfPacketHandler = new ZHFPacketHandler(minecraft);
        PacketEvents.PACKET_OUT.register(zhfPacketHandler);

        TickEvent.EVENT.register(new ToggleKeybindingAction(playerVisibilityKeybind, minecraft, playerRenderTester));
        TickEvent.EVENT.register(new ToggleKeybindingAction(zhfKeybind, minecraft, zhfPacketHandler));
    }
}
