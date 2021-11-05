package me.playbosswar.playapi.spawnpoints.definers;

import me.playbosswar.playapi.arena.Arena;
import me.playbosswar.playapi.spawnpoints.SpawnPointDefiner;
import me.playbosswar.playapi.teams.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class FixedConfigurationSpawnPointDefiner implements SpawnPointDefiner {
    @Override
    public Map<Player, Location> getSpawnLocationsForPlayers(Arena arena, List<Player> players) {
        return null;
    }

    @Override
    public Map<Team, Location> getSpawnLocationsForTeams(Arena arena, List<Team> teams) {
        return null;
    }
}
