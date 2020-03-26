package de.donythepony.simplespawn.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SpawnEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final Location location;

    public SpawnEvent(Player player, Location location) {
        this.player = player;
        this.location = location;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLocation() {
        return location;
    }
}
