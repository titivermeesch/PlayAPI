package me.playbosswar.playapi.game;

import me.playbosswar.playapi.PlayAPI;
import me.playbosswar.playapi.arena.ArenaManager;
import me.playbosswar.playapi.storage.StorageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

// Main entrypoint of the plugin, this will manage everything
public class GameManager implements Listener {
    private final String gameIdentifier;
    private final GameType gameType;
    private final StorageManager storageManager;
    private final ArenaManager arenaManager;

    public GameManager(String gameIdentifier, GameType gameType, ArenaManager arenaManager) throws InstantiationException {
        this.gameIdentifier = gameIdentifier;
        this.gameType = gameType;
        this.arenaManager = arenaManager;
        this.storageManager = new StorageManager();

        if(gameType.equals(GameType.BUNGEE) && arenaManager.getArenas().size() > 1) {
            throw new InstantiationException("Only 1 arena can be registered when using GameType.BUNGEE");
        }

        PlayAPI.getInstance().getServer().getPluginManager().registerEvents(this, PlayAPI.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(gameType.equals(GameType.BUNGEE)) {
            arenaManager.getArenas().get(0).addPlayer(e.getPlayer());
        }
    }
}
