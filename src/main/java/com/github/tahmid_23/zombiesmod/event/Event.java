package com.github.tahmid_23.zombiesmod.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class Event<TListener> {

    private final Collection<TListener> listeners = new ArrayList<>();

    private final Function<Collection<TListener>, TListener> invokerFactory;

    private TListener invoker;

    public Event(Function<Collection<TListener>, TListener> invokerFactory) {
        this.invokerFactory = Objects.requireNonNull(invokerFactory, "invokerFactory");
        refreshInvoker();
    }

    public void register(TListener listener) {
        Objects.requireNonNull(listener, "listener");
        listeners.add(listener);
        refreshInvoker();
    }

    public void unregister(TListener listener) {
        Objects.requireNonNull(listener, "listener");
        listeners.remove(listener);
        refreshInvoker();
    }

    private void refreshInvoker() {
        invoker = Objects.requireNonNull(invokerFactory.apply(listeners));
    }

    public TListener getInvoker() {
        return invoker;
    }

}
