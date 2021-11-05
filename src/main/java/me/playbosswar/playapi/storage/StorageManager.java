package me.playbosswar.playapi.storage;

import me.playbosswar.playapi.storage.events.AdapterRegisteredEvent;
import me.playbosswar.playapi.storage.events.AdapterSetupEvent;
import me.playbosswar.playapi.storage.exceptions.AdapterSetupFailedException;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Main manager which registers all the different adapters that are currently in use
 */
public class StorageManager {
    private final Map<Class<?>, StorageAdapter<?, ?>> adapters = new HashMap<>();

    public StorageManager() {}

    /**
     * Create a new link between a class and a storage adapter
     *
     * @param clazz   - Class to link an adapter to
     * @param adapter - Data storage adapter
     *
     * @throws AdapterSetupFailedException - thrown when adapter failed to setup
     */
    public <T, Q> void registerStorageAdapter(Class<T> clazz, StorageAdapter<T, Q> adapter) throws AdapterSetupFailedException, IOException {
        AdapterRegisteredEvent adapterRegisteredEvent = new AdapterRegisteredEvent(adapter, clazz);
        Bukkit.getPluginManager().callEvent(adapterRegisteredEvent);

        if (adapterRegisteredEvent.isCancelled()) {
            return;
        }

        adapters.put(clazz, adapter);

        AdapterSetupEvent adapterSetupEvent = new AdapterSetupEvent(adapter, clazz);
        Bukkit.getPluginManager().callEvent(adapterSetupEvent);

        if (adapterSetupEvent.isCancelled()) {
            return;
        }

        adapter.setup();
    }

    /**
     * Get an already registered StorageAdapter
     *
     * @param clazz - Linked class
     *
     * @return -
     * @throws NullPointerException -
     */
    @SuppressWarnings("unchecked")
    public <T, Q> StorageAdapter<T, Q> getStorageAdapter(Class<T> clazz) throws NullPointerException {
        if (adapters.get(clazz) == null) {
            throw new NullPointerException("Tried to get adapter for "
                                                   + clazz.getName() +
                                                   " but no adapter has been registered");
        }

        return (StorageAdapter<T, Q>) adapters.get(clazz);
    }
}
