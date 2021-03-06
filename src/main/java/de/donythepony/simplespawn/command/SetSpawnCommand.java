package de.donythepony.simplespawn.command;

import de.donythepony.simplespawn.SimpleSpawn;
import de.donythepony.simplespawn.event.SetSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.isOp() || player.hasPermission("simplespawn.create")) {
                Location location = player.getLocation();
                SimpleSpawn.getManager().setNewSpawn(location);
                Bukkit.getPluginManager().callEvent(new SetSpawnEvent(player, location));
            }
            return true;
        }
        return false;
    }
}
