package com.github.tahmid_23.zombiesmod.event;

public class TickEvent {

    private TickEvent() {
        throw new UnsupportedOperationException();
    }

    public static final Event<Runnable> EVENT = new Event<>(listeners -> () -> {
        for (Runnable listener : listeners) {
            listener.run();
        }
    });

}
