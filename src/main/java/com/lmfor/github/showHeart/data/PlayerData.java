package com.lmfor.github.showHeart.data;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private final UUID playerUUID;
    private final Player player;

    // Movement tracking
    private Location lastLocation;
    private Location currentLocation;
    private long lastMoveTime;
    private double lastDistance;

    // Violation counts for each check
    private int speedViolations;
    private int velocityViolations;
    private int reachViolations;
    private int killauraViolations;
    private int flyViolations;
    private int autoclickerViolations;
    private int scaffoldViolations;

    // Constructor
    public PlayerData(Player player) {
        this.player = player;
        this.playerUUID = player.getUniqueId();
        this.lastLocation = player.getLocation();
        this.currentLocation = player.getLocation();
        this.lastMoveTime = System.currentTimeMillis();
        this.lastDistance = 0.0;

        // Initialize all violation counts to 0
        this.speedViolations = 0;
        this.velocityViolations = 0;
        this.reachViolations = 0;
        this.killauraViolations = 0;
        this.flyViolations = 0;
        this.autoclickerViolations = 0;
        this.scaffoldViolations = 0;
    }

    // Update movement data
    public void updateLocation(Location newLocation) {
        this.lastLocation = this.currentLocation.clone();
        this.currentLocation = newLocation.clone();

        // Calculate distance moved
        if (lastLocation != null && currentLocation.getWorld().equals(lastLocation.getWorld())) {
            this.lastDistance = lastLocation.distance(currentLocation);
        } else {
            this.lastDistance = 0.0;
        }

        this.lastMoveTime = System.currentTimeMillis();
    }

    // Violation increment methods
    public void incrementSpeedViolations() {
        this.speedViolations++;
    }

    public void incrementVelocityViolations() {
        this.velocityViolations++;
    }

    public void incrementReachViolations() {
        this.reachViolations++;
    }

    public void incrementKillauraViolations() {
        this.killauraViolations++;
    }

    public void incrementFlyViolations() {
        this.flyViolations++;
    }

    public void incrementAutoclickerViolations() {
        this.autoclickerViolations++;
    }

    public void incrementScaffoldViolations() {
        this.scaffoldViolations++;
    }

    // Reset violation methods
    public void resetSpeedViolations() {
        this.speedViolations = 0;
    }

    public void resetAllViolations() {
        this.speedViolations = 0;
        this.velocityViolations = 0;
        this.reachViolations = 0;
        this.killauraViolations = 0;
        this.flyViolations = 0;
        this.autoclickerViolations = 0;
        this.scaffoldViolations = 0;
    }

    // Utility methods
    public long getTimeSinceLastMove() {
        return System.currentTimeMillis() - lastMoveTime;
    }

    public double getDistancePerSecond() {
        long timeDiff = getTimeSinceLastMove();
        if (timeDiff == 0) return 0.0;
        return (lastDistance * 1000.0) / timeDiff; // Convert to per second
    }

    // Getters
    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLastLocation() {
        return lastLocation != null ? lastLocation.clone() : null;
    }

    public Location getCurrentLocation() {
        return currentLocation != null ? currentLocation.clone() : null;
    }

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public double getLastDistance() {
        return lastDistance;
    }

    // Violation getters
    public int getSpeedViolations() {
        return speedViolations;
    }

    public int getVelocityViolations() {
        return velocityViolations;
    }

    public int getReachViolations() {
        return reachViolations;
    }

    public int getKillauraViolations() {
        return killauraViolations;
    }

    public int getFlyViolations() {
        return flyViolations;
    }

    public int getAutoclickerViolations() {
        return autoclickerViolations;
    }

    public int getScaffoldViolations() {
        return scaffoldViolations;
    }
}