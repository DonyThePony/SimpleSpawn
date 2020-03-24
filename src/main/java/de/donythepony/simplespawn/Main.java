package de.donythepony.simplespawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
            File spawnData = new File(getDataFolder(), "simplespawn.yml");
            boolean fileLoaded;
            if (!spawnData.exists()) {
                try {
                    spawnData.createNewFile();
                } catch (IOException ex) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.RED + "Could not create the simplespawn.yml file");
                    ex.printStackTrace();
                }
            }
            FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(spawnData);
            if(!fileConfig.contains("location")) {
                fileConfig.set("location", getServer().getWorlds().get(0).getSpawnLocation());
                try {
                    fileConfig.save(spawnData);
                } catch(IOException ex) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.RED + "Could not save the simplespawn.yml file");
                    ex.printStackTrace();
                }
            }
        }
    }
}
