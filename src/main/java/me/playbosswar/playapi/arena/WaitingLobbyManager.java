package me.playbosswar.playapi.arena;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class WaitingLobbyManager implements Listener {
    private final Arena arena;

    public WaitingLobbyManager(Arena arena) {
        this.arena = arena;
    }
    public abstract Location getSpawnLocation(Player p);
    public abstract List<ItemStack> getItems(Player p);
}
