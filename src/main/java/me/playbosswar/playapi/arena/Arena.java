package me.playbosswar.playapi.arena;

import java.util.UUID;

public class Arena {
    private String name;
    private final UUID uuid;

    public Arena(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }
}
