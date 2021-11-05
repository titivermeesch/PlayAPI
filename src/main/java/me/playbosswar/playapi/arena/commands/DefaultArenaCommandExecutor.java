package me.playbosswar.playapi.arena.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Default command behaviour for everything under the /arena command
 */
public class DefaultArenaCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             @NotNull String[] args) {
        /**
         * /arena create name
         * /arena name
         * /arena remove name
         * /arena pos1 name
         * /arena pos2 name
         * /arena enable/disable name
         */
        return true;
    }
}
