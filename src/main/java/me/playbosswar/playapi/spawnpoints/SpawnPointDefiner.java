package me.playbosswar.playapi.spawnpoints;

import me.playbosswar.playapi.arena.Arena;
import me.playbosswar.playapi.teams.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public interface SpawnPointDefiner {
    Map<Player, Location> getSpawnLocationsForPlayers(Arena arena, List<Player> players);
    Map<Team, Location> getSpawnLocationsForTeams(Arena arena, List<Team> teams);
}
