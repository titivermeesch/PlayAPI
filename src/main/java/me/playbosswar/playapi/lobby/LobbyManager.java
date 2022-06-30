package me.playbosswar.playapi.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class LobbyManager implements Listener {
    private final Plugin plugin;
    private Location spawnLocation;
    private boolean teleportOnJoin;

    public LobbyManager(Plugin plugin, Location spawnLocation, boolean teleportOnJoin) {
        this.plugin = plugin;
        this.spawnLocation = spawnLocation;
        this.teleportOnJoin = teleportOnJoin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (teleportOnJoin) {
            e.getPlayer().teleport(spawnLocation);
        }
    }
}
