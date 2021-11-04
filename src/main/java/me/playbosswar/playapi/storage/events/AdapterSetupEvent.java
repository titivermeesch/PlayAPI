package me.playbosswar.playapi.storage.events;

import me.playbosswar.playapi.storage.StorageAdapter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AdapterSetupEvent extends Event implements Cancellable {
    private final StorageAdapter adapter;
    private final Class<?> targetClass;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled = false;

    public AdapterSetupEvent(StorageAdapter adapter, Class<?> targetClass) {
        this.adapter = adapter;
        this.targetClass = targetClass;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public StorageAdapter getAdapter() {
        return adapter;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }
}
