package com.lmfor.github.showHeart;

import com.lmfor.github.showHeart.config.ConfigManager;
import com.lmfor.github.showHeart.data.PlayerDataManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShowHeart extends JavaPlugin {

    private static ShowHeart instance;
    private ConfigManager configManager;
    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Initialize configuration
        configManager = new ConfigManager(this);

        // Initialize player data manager
        playerDataManager = new PlayerDataManager(this);

        getLogger().info("  ____  _                   _   _                 _   ");
        getLogger().info(" / ___|| |__   _____      _| | | | ___  __ _ _ __| |_ ");
        getLogger().info(" \\___ \\| '_ \\ / _ \\ \\ /\\ / / |_| |/ _ \\/ _` | '__| __|");
        getLogger().info("  ___) | | | | (_) \\ V  V /|  _  |  __/ (_| | |  | |_ ");
        getLogger().info(" |____/|_| |_|\\___/ \\_/\\_/ |_| |_|\\___|\\__,_|_|   \\__|");

        // Log startup status
        if (configManager.isPluginEnabled()) {
            getLogger().info("ShowHeart has been enabled successfully!");
            if (configManager.isDebugMode()) {
                getLogger().info("Debug mode is enabled");
                getLogger().info("Tracking " + playerDataManager.getTrackedPlayerCount() + " players");
                getLogger().info("Enabled checks:");
                if (configManager.isSpeedCheckEnabled()) getLogger().info("- Speed (threshold: " + configManager.getSpeedThreshold() + ")");
                if (configManager.isVelocityCheckEnabled()) getLogger().info("- Velocity");
                if (configManager.isReachCheckEnabled()) getLogger().info("- Reach (threshold: " + configManager.getReachThreshold() + ")");
                if (configManager.isKillauraCheckEnabled()) getLogger().info("- KillAura");
                if (configManager.isFlyCheckEnabled()) getLogger().info("- Fly");
                if (configManager.isAutoclickerCheckEnabled()) getLogger().info("- AutoClicker (minimum: " + configManager.getAutoclickerMinimum() + ")");
                if (configManager.isScaffoldCheckEnabled()) getLogger().info("- Scaffold");
            }
        } else {
            getLogger().warning("ShowHeart is disabled in config.yml");
        }
    }

    @Override
    public void onDisable() {
        // Clean up player data
        if (playerDataManager != null) {
            playerDataManager.clearAllPlayerData();
        }

        getLogger().info("ShowHeart is shutting down...");
    }

    // Static getter for instance
    public static ShowHeart getInstance() {
        return instance;
    }

    // Getter for ConfigManager
    public ConfigManager getConfigManager() {
        return configManager;
    }

    // Getter for PlayerDataManager
    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }
}