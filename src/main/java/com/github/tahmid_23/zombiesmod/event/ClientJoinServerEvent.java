package com.github.tahmid_23.zombiesmod.event;

import io.netty.channel.Channel;

public class ClientJoinServerEvent {

    private ClientJoinServerEvent() {
        throw new UnsupportedOperationException();
    }

    public static final Event<JoinServerListener> EVENT = new Event<>(listeners -> (channel) -> {
        for (JoinServerListener listener : listeners) {
            listener.onJoin(channel);
        }
    });

    public interface JoinServerListener {

        void onJoin(Channel channel);

    }

}
