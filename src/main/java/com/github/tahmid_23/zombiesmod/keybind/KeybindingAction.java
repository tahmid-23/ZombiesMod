package com.github.tahmid_23.zombiesmod.keybind;

import net.minecraft.client.settings.KeyBinding;

import java.util.Objects;

public abstract class KeybindingAction implements Runnable {

    private final KeyBinding keyBinding;

    public KeybindingAction(KeyBinding keyBinding) {
        this.keyBinding = Objects.requireNonNull(keyBinding, "keyBinding");
    }

    @Override
    public void run() {
        if (keyBinding.isPressed()) {
            onPress();
        }
    }

    protected abstract void onPress();

}
