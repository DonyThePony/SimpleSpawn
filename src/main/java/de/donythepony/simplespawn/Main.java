package de.donythepony.simplespawn;

import de.donythepony.simplespawn.command.SetSpawnCommand;
import de.donythepony.simplespawn.command.SpawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new SimpleSpawn(this);
        SimpleSpawn.getManager().setup();
        SimpleSpawn.getManager().loadInfoFromFile();

        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());

        getServer().getPluginManager().registerEvents(new SpawnEventListener(), this);
    }
}
