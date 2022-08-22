package com.github.tahmid_23.zombiesmod.chat;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

//#if MC>=11602
//$$ import net.minecraft.util.text.IFormattableTextComponent;
//#endif

public class Components {

    private Components() {
        throw new UnsupportedOperationException();
    }

    //#if MC>=11602
    //$$ public static <TComponent extends IFormattableTextComponent> TComponent setColor(TComponent component, TextFormatting color) {
    //#else
    public static <TComponent extends IChatComponent> TComponent setColor(TComponent component, EnumChatFormatting color) {
    //#endif
        //#if MC>=11602
        //$$ component.mergeStyle(color);
        //#else
        component.setChatStyle(component.getChatStyle().setColor(color));
        //#endif

        return component;
    }

    public static ChatComponentText text(String message) {
        return new ChatComponentText(message);
    }

    public static ChatComponentText text(String message, EnumChatFormatting color) {
        return setColor(text(message), color);
    }

    public static ChatComponentTranslation translatable(String key, Object... args) {
        return new ChatComponentTranslation(key, args);
    }

    public static ChatComponentTranslation translatable(String key, EnumChatFormatting color, Object... args) {
        return setColor(translatable(key, args), color);
    }

}
