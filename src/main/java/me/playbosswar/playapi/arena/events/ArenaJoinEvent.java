package me.playbosswar.playapi.arena.events;

import me.playbosswar.playapi.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaJoinEvent extends Event implements Cancellable {
    private final Arena arena;
    private final Player player;

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled = false;

    public ArenaJoinEvent(Arena arena, Player player) {
        this.arena = arena;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
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
