package com.github.tahmid_23.zombiesmod.event;

import net.minecraft.client.entity.AbstractClientPlayer;

public class RenderPlayerEvent {

    private RenderPlayerEvent() {
        throw new UnsupportedOperationException();
    }

    public static final Event<RenderPlayerListener> EVENT = new Event<>(listeners -> (player) -> {
        boolean shouldRender = true;
        for (RenderPlayerListener listener : listeners) {
            if (!listener.shouldRenderPlayer(player)) {
                shouldRender = false;
            }
        }

        return shouldRender;
    });

    public interface RenderPlayerListener {

        boolean shouldRenderPlayer(AbstractClientPlayer player);

    }

}
