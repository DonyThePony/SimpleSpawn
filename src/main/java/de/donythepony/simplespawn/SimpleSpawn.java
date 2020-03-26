package de.donythepony.simplespawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SimpleSpawn {

    private static SimpleSpawn manager;

    private Main plugin;
    public Location spawnLocation;
    private Particle setSpawnParticle;
    private Particle spawnParticle;


    public static SimpleSpawn getManager() {
        return SimpleSpawn.manager;
    }

    public SimpleSpawn(Main plugin) {
        this.plugin = plugin;
        SimpleSpawn.manager = this;
    }

    public void setup() {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
            File spawnData = new File(plugin.getDataFolder(), "simplespawn.yml");


            if (!spawnData.exists()) {
                World world = Bukkit.getWorlds().get(0);
                Location location = world.getSpawnLocation();

                YamlConfiguration yaml = new YamlConfiguration();
                yaml.createSection("spawn");
                yaml.set("spawn.world", world.getName());
                yaml.set("spawn.x", location.getX());
                yaml.set("spawn.y", location.getX());
                yaml.set("spawn.z", location.getX());
                yaml.set("spawn.yaw", location.getYaw());
                yaml.set("spawn.pitch", location.getPitch());
                yaml.set("particle.setspawn", Particle.FIREWORKS_SPARK.toString());
                yaml.set("particle.spawn", Particle.CLOUD.toString());
                try {
                    spawnData.createNewFile();
                    yaml.save(spawnData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadInfoFromFile() {
        File file = new File(plugin.getDataFolder(), "simplespawn.yml");
        YamlConfiguration yamlConfig = new YamlConfiguration();
        try {
            yamlConfig.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Location location = null;
        World world = Bukkit.getWorld(yamlConfig.getString("spawn.world"));
        double x = yamlConfig.getDouble("spawn.x");
        double y = yamlConfig.getDouble("spawn.y");
        double z = yamlConfig.getDouble("spawn.z");
        float yaw = (float) yamlConfig.getDouble("spawn.yaw");
        float pitch = (float) yamlConfig.getDouble("spawn.pitch");
        location = new Location(world, x, y, z, yaw, pitch);
        this.spawnLocation = location;

        Particle particle = Particle.valueOf(yamlConfig.getString("particle.setspawn"));
        if(particle == null) {
            particle = Particle.FIREWORKS_SPARK;
        }
        this.setSpawnParticle = particle;

        particle = Particle.valueOf(yamlConfig.getString("particle.spawn"));
        if(particle == null) {
            particle = Particle.FIREWORKS_SPARK;
        }
        this.spawnParticle = particle;
    }

    public Particle getSetSpawnParticleFromFile() {
        File file = new File(plugin.getDataFolder(), "simplespawn.yml");
        YamlConfiguration yamlConfig = new YamlConfiguration();
        try {
            yamlConfig.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Particle particle = Particle.valueOf(yamlConfig.getString("particle.setspawn"));
        if(particle == null) {
            particle = Particle.FIREWORKS_SPARK;
        }
        this.setSpawnParticle = particle;
        return particle;
    }

    public Particle getSpawnParticleFromFile() {
        File file = new File(plugin.getDataFolder(), "simplespawn.yml");
        YamlConfiguration yamlConfig = new YamlConfiguration();
        try {
            yamlConfig.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Particle particle = Particle.valueOf(yamlConfig.getString("particle.spawn"));
        if(particle == null) {
            particle = Particle.FIREWORKS_SPARK;
        }
        this.spawnParticle = particle;
        return particle;
    }

    public void setNewSpawn(Location location) {
        File file = new File(plugin.getDataFolder(), "simplespawn.yml");
        YamlConfiguration yamlConfig = new YamlConfiguration();

        yamlConfig.set("spawn.world", location.getWorld().getName());
        yamlConfig.set("spawn.x", location.getX());
        yamlConfig.set("spawn.y", location.getY());
        yamlConfig.set("spawn.z", location.getZ());
        yamlConfig.set("spawn.pitch", location.getPitch());
        yamlConfig.set("spawn.yaw", location.getYaw());
        yamlConfig.set("particle.setspawn", setSpawnParticle.toString());
        yamlConfig.set("particle.spawn", spawnParticle.toString());

        try {
            yamlConfig.save(file);
            this.spawnLocation = location;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
