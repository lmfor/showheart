package com.lmfor.github.showHeart.data;

import com.lmfor.github.showHeart.ShowHeart;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager implements Listener {

    private final ShowHeart plugin;
    private final Map<UUID, PlayerData> playerDataMap;

    public PlayerDataManager(ShowHeart plugin) {
        this.plugin = plugin;
        this.playerDataMap = new HashMap<>();

        // Register this class as an event listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // Create PlayerData for all currently online players
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            createPlayerData(player);
        }
    }

    // Create PlayerData for a player
    public PlayerData createPlayerData(Player player) {
        PlayerData data = new PlayerData(player);
        playerDataMap.put(player.getUniqueId(), data);

        if (plugin.getConfigManager().isDebugMode()) {
            plugin.getLogger().info("Created PlayerData for: " + player.getName());
            player.sendMessage(ChatColor.RED + "[!] Created PlayerData for: " + ChatColor.AQUA + player.getName());
        }

        return data;
    }

    // Get PlayerData for a player (creates if it doesn't exist)
    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId(), player);
    }

    // Get PlayerData by UUID (with player fallback for creation)
    public PlayerData getPlayerData(UUID uuid, Player player) {
        PlayerData data = playerDataMap.get(uuid);

        // If data doesn't exist, create it
        if (data == null && player != null) {
            data = createPlayerData(player);
        }

        return data;
    }

    // Get PlayerData by UUID only (returns null if doesn't exist)
    public PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.get(uuid);
    }

    // Remove PlayerData for a player
    public void removePlayerData(Player player) {
        removePlayerData(player.getUniqueId());
    }

    // Remove PlayerData by UUID
    public void removePlayerData(UUID uuid) {
        PlayerData removed = playerDataMap.remove(uuid);

        if (removed != null && plugin.getConfigManager().isDebugMode()) {
            plugin.getLogger().info("Removed PlayerData for: " + removed.getPlayer().getName());
        }
    }

    // Check if PlayerData exists for a player
    public boolean hasPlayerData(Player player) {
        return playerDataMap.containsKey(player.getUniqueId());
    }

    // Check if PlayerData exists by UUID
    public boolean hasPlayerData(UUID uuid) {
        return playerDataMap.containsKey(uuid);
    }

    // Get all PlayerData objects
    public Map<UUID, PlayerData> getAllPlayerData() {
        return new HashMap<>(playerDataMap); // Return copy to prevent external modification
    }

    // Get count of tracked players
    public int getTrackedPlayerCount() {
        return playerDataMap.size();
    }

    // Clear all PlayerData (useful for reloads)
    public void clearAllPlayerData() {
        int count = playerDataMap.size();
        playerDataMap.clear();

        if (plugin.getConfigManager().isDebugMode()) {
            plugin.getLogger().info("Cleared PlayerData for " + count + " players");
        }
    }

    // Event Handlers
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Create PlayerData for the joining player
        createPlayerData(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Remove PlayerData for the leaving player to free memory
        removePlayerData(player);
    }
}