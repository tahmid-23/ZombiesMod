package com.github.tahmid_23.zombiesmod.keybind;

import com.github.tahmid_23.zombiesmod.chat.Components;
import com.github.tahmid_23.zombiesmod.util.Toggleable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.util.Objects;

public class ToggleKeybindingAction extends KeybindingAction {

    private final Minecraft minecraft;

    private final Toggleable toggleable;

    public ToggleKeybindingAction(KeyBinding keyBinding, Minecraft minecraft, Toggleable toggleable) {
        super(keyBinding);
        this.minecraft = Objects.requireNonNull(minecraft, "minecraft");
        this.toggleable = Objects.requireNonNull(toggleable, "toggleable");
    }

    @Override
    protected void onPress() {
        boolean newState = !toggleable.isEnabled();
        toggleable.setEnabled(newState);

        //#if MC>=11602
        //$$ minecraft.execute(() -> {
        //#else
        minecraft.addScheduledTask(() -> {
        //#endif
            EntityPlayerSP player = minecraft.thePlayer;
            if (player == null) {
                return;
            }

            IChatComponent result = newState
                    ? Components.text("ON", EnumChatFormatting.GREEN)
                    : Components.text("OFF", EnumChatFormatting.RED);
            IChatComponent message = Components.text("", EnumChatFormatting.YELLOW)
                    .appendText("Set ")
                    .appendSibling(toggleable.getDisplayName())
                    .appendText(" to ")
                    .appendSibling(result)
                    .appendText("!");
            //#if MC>=11602
            //$$ player.sendStatusMessage(message, false);
            //#else
            player.addChatMessage(message);
            //#endif
        });
    }
}
