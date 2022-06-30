package me.playbosswar.playapi;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayAPI extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Plugin getInstance() {
        return instance;
    }
}
