package com.github.tahmid_23.zombiesmod.render;

import com.github.tahmid_23.zombiesmod.chat.Components;
import com.github.tahmid_23.zombiesmod.event.RenderPlayerEvent;
import com.github.tahmid_23.zombiesmod.util.Toggleable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

//#if MC>=11602
//$$ import net.minecraft.entity.Pose;
//#endif

import java.util.Objects;

public class PlayerRenderTester implements RenderPlayerEvent.RenderPlayerListener, Toggleable {

    private static final IChatComponent DISPLAY_NAME = Components.text("PlayerVisibility", EnumChatFormatting.GRAY);

    private final Minecraft minecraft;

    private final double renderDistanceSquared;

    private boolean enabled = false;

    public PlayerRenderTester(Minecraft minecraft, double renderDistance) {
        this.minecraft = minecraft;
        this.renderDistanceSquared = renderDistance * renderDistance;
    }

    @Override
    public boolean shouldRenderPlayer(AbstractClientPlayer player) {
        Objects.requireNonNull(player, "player");
        if (!isEnabled() || minecraft.thePlayer == null || minecraft.thePlayer == player
                //#if MC>=11602
                //$$ || player.getPose() == Pose.SLEEPING
                //#else
                || player.isPlayerSleeping()
                //#endif
        ) {
            return true;
        }

        return minecraft.thePlayer.getDistanceSqToEntity(player) > renderDistanceSquared;
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
