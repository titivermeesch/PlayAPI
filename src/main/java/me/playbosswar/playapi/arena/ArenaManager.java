package me.playbosswar.playapi.arena;

import me.playbosswar.playapi.arena.events.ArenaCreatedEvent;
import me.playbosswar.playapi.debug.DebugMessages;
import me.playbosswar.playapi.exceptions.AlreadyRegisteredException;
import me.playbosswar.playapi.spawnpoints.SpawnPointDefiner;
import me.playbosswar.playapi.storage.StorageManager;
import me.playbosswar.playapi.storage.exceptions.AdapterTransactionException;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ArenaManager {
    private final StorageManager storageManager;
    private final List<Arena> arenas = new ArrayList<>();
    private final SpawnPointDefiner defaultSpawnPointDefiner;

    public ArenaManager(StorageManager storageManager, SpawnPointDefiner defaultSpawnPointDefiner) {
        this.storageManager = storageManager;
        this.defaultSpawnPointDefiner = defaultSpawnPointDefiner;
    }

    /**
     * Register a new arena to the manager
     *
     * @param arena - Arena to register
     *
     * @throws AlreadyRegisteredException - Thrown when arena is already registered
     */
    public void registerArena(Arena arena)
            throws AlreadyRegisteredException, AdapterTransactionException {
        final Optional<Arena> existingArena =
                arenas.stream().filter(a -> a.getUuid().equals(arena.getUuid())).findAny();

        if (existingArena.isPresent()) {
            throw new AlreadyRegisteredException("Arena " + arena.getName() + " is already registered");
        }

        ArenaCreatedEvent arenaCreatedEvent = new ArenaCreatedEvent(arena);
        Bukkit.getPluginManager().callEvent(arenaCreatedEvent);

        if (arenaCreatedEvent.isCancelled()) {
            return;
        }

        arenas.add(arena);
        DebugMessages.sendDebugMessage("Arena " + arena.getName() + " has been added");

        storageManager.getStorageAdapter(Arena.class).insert(arena);
    }

    public void unregisterArena(UUID arenaUuid) {
        // TODO: Implement
    }

    public List<Arena> getArenas() {
        return arenas;
    }
}
