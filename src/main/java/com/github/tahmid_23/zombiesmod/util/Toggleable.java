package com.github.tahmid_23.zombiesmod.util;

import net.minecraft.util.IChatComponent;

public interface Toggleable {

    boolean isEnabled();

    void setEnabled(boolean enabled);

    IChatComponent getDisplayName();

}
