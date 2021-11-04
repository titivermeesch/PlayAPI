package me.playbosswar.playapi.storage.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.playbosswar.playapi.arena.Arena;
import me.playbosswar.playapi.storage.StorageAdapter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class YAMLAdapter<T> implements StorageAdapter<T> {
    private final Yaml yaml;
    private final ObjectMapper mapper;
    private final Plugin plugin;
    private File file;
    private FileConfiguration config;
    private final String fileName;
    private final String name;
    private final Class<T> clazz;

    /**
     * @param plugin   -
     * @param fileName -
     * @param clazz    - Required due to how java managed generic types
     */
    public YAMLAdapter(Plugin plugin, String fileName, Class<T> clazz) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.name = fileName.split("\\.")[0];
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.clazz = clazz;
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setAllowReadOnlyProperties(true);

        Representer representer = new Representer();
        representer.addClassTag(Arena.class, Tag.MAP);
        representer.addClassTag(UUID.class, Tag.MAP);

        yaml = new Yaml(new Constructor(clazz), representer, options);
    }

    @Override
    public String getName() {
        return "YAML";
    }

    @Override
    public void setup() {
        try {
            file = new File(plugin.getDataFolder(), this.fileName);
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
    public void insert(T data) {
        /**
         * 1. Get current file
         * 2.
         */
        System.out.println(config.getKeys(true));
        List<T> def = new ArrayList<>();
        List<T> existingData = (List<T>) config.getList(name, def);
        System.out.println(existingData);
        //            Gson gson = new Gson();
        //            System.out.println(gson.toJson(data));
        //            JsonObject object = gson.toJsonTree(data).getAsJsonObject();
        //            PrintWriter writer = new PrintWriter(file);
        //            yaml.dump(data, writer);

        // getOne(object.getAsJsonObject().get("uuid").getAsString());
    }

    @Override
    public T getOne(Object query) {
        Object o = config.get(query.toString());
        System.out.println(o);
        T data = yaml.load(o.toString());
        System.out.println(data);
        return data;
    }

    @Override
    public List<T> getAll(Object query) {
        return null;
    }

    @Override
    public void deleteOne(Object query) {
        return;
    }

    @Override
    public void deleteAll(Object query) {
        return;
    }

    @Override
    public void updateOne(Object query) {
        return;
    }

    @Override
    public void updateMany(Object query) {
        return;
    }
}
