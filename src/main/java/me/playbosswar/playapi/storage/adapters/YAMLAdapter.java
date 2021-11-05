package me.playbosswar.playapi.storage.adapters;

import me.playbosswar.playapi.storage.StorageAdapter;
import me.playbosswar.playapi.storage.exceptions.AdapterTransactionException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Generic purpose adapter for storing data in YAML files
 *
 * @param <T> - Class type to be stored
 */
public class YAMLAdapter<T> implements StorageAdapter<T, Predicate<? super T>> {
    private final Plugin plugin;
    private FileConfiguration config;
    private final String fileName;
    private final String name;

    /**
     * @param plugin   - Plugin instance
     * @param fileName - Name of file to store data in
     */
    public YAMLAdapter(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.name = fileName.split("\\.")[0];
    }

    @Override
    public void setup() {
        try {
            File file = new File(plugin.getDataFolder(), this.fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            config = new YamlConfiguration();

            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(T data) throws AdapterTransactionException {
        try {
            List<T> all = (List<T>) config.getList(name, new ArrayList<T>());

            all.add(data);
            config.set(name, all);
        } catch (Exception e) {
            throw new AdapterTransactionException("Failed to insert data entry in file " + name, e);
        }
    }

    @Override
    @Nullable
    public T findOne(Predicate<? super T> predicate) {
        List<T> all = (List<T>) config.getList(name, new ArrayList<T>());

        return all.stream().filter(predicate).findFirst().orElse(null);
    }

    @Override
    public List<T> find(Predicate<? super T> query) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void deleteOne(Predicate<? super T> query) {
        return;
    }

    @Override
    public void deleteAll(Predicate<? super T> query) {
        return;
    }

    @Override
    public void updateOne(Predicate<? super T> query) {
        return;
    }

    @Override
    public void updateMany(Predicate<? super T> query) {
        return;
    }
}
