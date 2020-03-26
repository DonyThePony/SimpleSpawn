package de.donythepony.simplespawn;

import de.donythepony.simplespawn.event.SetSpawnEvent;
import de.donythepony.simplespawn.event.SpawnEvent;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SpawnEventListener implements Listener {

    @EventHandler
    public void onPlayerSetSpawn(SetSpawnEvent event) {
        Player player = event.getPlayer();
        Location location = event.getLocation();
        location.getWorld().spawnParticle(SimpleSpawn.getManager().getSetSpawnParticleFromFile(), location, 10);
    }

    @EventHandler
    public void onPlayerSpawn(SpawnEvent event) {
        Player player = event.getPlayer();
        Location location = event.getLocation();
        location.getWorld().spawnParticle(SimpleSpawn.getManager().getSpawnParticleFromFile(), location, 10);
    }
}
