package me.playbosswar.playapi.arena;

import me.playbosswar.playapi.arena.events.ArenaCreatedEvent;
import me.playbosswar.playapi.debug.DebugMessages;
import me.playbosswar.playapi.exceptions.AlreadyRegisteredException;
import me.playbosswar.playapi.storage.StorageManager;
import me.playbosswar.playapi.storage.exceptions.AdapterTransactionException;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArenaManager {
    private final StorageManager storageManager;
    private final List<Arena> arenas = new ArrayList<>();

    public ArenaManager(StorageManager storageManager) {
        this.storageManager = storageManager;
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
}
