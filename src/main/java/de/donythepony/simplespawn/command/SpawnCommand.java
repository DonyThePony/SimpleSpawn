package de.donythepony.simplespawn.command;

import de.donythepony.simplespawn.SimpleSpawn;
import de.donythepony.simplespawn.event.SpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.teleport(SimpleSpawn.getManager().spawnLocation);
            Bukkit.getPluginManager().callEvent(new SpawnEvent(player, player.getLocation()));
            return true;
        }

        return false;
    }
}
