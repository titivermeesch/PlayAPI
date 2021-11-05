package me.playbosswar.playapi.arena;

import java.util.UUID;

public class Arena {
    private final UUID uuid;
    private String name;
    private Boolean enabled;

    public Arena(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
