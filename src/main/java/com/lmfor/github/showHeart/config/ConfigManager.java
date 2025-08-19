package com.lmfor.github.showHeart.config;

import com.lmfor.github.showHeart.ShowHeart;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final ShowHeart plugin;
    private FileConfiguration config;

    // Config values
    private boolean pluginEnabled;
    private boolean debugMode;
    private boolean alertsEnabled;
    private boolean consoleAlerts;

    // Check enabled states
    private boolean speedCheckEnabled;
    private boolean velocityCheckEnabled;
    private boolean reachCheckEnabled;
    private boolean killauraCheckEnabled;
    private boolean flyCheckEnabled;
    private boolean autoclickerCheckEnabled;
    private boolean scaffoldCheckEnabled;

    // Check thresholds
    private double speedThreshold;
    private double reachThreshold;
    private double autoclickerMinimum;

    public ConfigManager(ShowHeart plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        // Save default config if it doesn't exist
        plugin.saveDefaultConfig();

        // Get the config
        config = plugin.getConfig();

        // Load values into variables
        pluginEnabled = config.getBoolean("enabled", true);
        debugMode = config.getBoolean("debug", true);
        alertsEnabled = config.getBoolean("alerts.enabled", true);
        consoleAlerts = config.getBoolean("alerts.console", true);

        // Load check enabled states
        speedCheckEnabled = config.getBoolean("checks.speed.enabled", true);
        velocityCheckEnabled = config.getBoolean("checks.velocity.enabled", true);
        reachCheckEnabled = config.getBoolean("checks.reach.enabled", true);
        killauraCheckEnabled = config.getBoolean("checks.killaura.enabled", true);
        flyCheckEnabled = config.getBoolean("checks.fly.enabled", true);
        autoclickerCheckEnabled = config.getBoolean("checks.autoclicker.enabled", true);
        scaffoldCheckEnabled = config.getBoolean("checks.scaffold.enabled", true);

        // Load check thresholds
        speedThreshold = config.getDouble("checks.speed.threshold", 0.5);
        reachThreshold = config.getDouble("checks.reach.threshold", 3.0);
        autoclickerMinimum = config.getDouble("checks.autoclicker.minimum", 10.0);

        if (debugMode) {
            plugin.getLogger().info("Configuration loaded successfully!");
        }
    }

    public void reload() {
        plugin.reloadConfig();
        loadConfig();
        plugin.getLogger().info("Configuration reloaded!");
    }

    // Getter methods for basic settings
    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public boolean isAlertsEnabled() {
        return alertsEnabled;
    }

    public boolean isConsoleAlertsEnabled() {
        return consoleAlerts;
    }

    // Getter methods for check enabled states
    public boolean isSpeedCheckEnabled() {
        return speedCheckEnabled;
    }

    public boolean isVelocityCheckEnabled() {
        return velocityCheckEnabled;
    }

    public boolean isReachCheckEnabled() {
        return reachCheckEnabled;
    }

    public boolean isKillauraCheckEnabled() {
        return killauraCheckEnabled;
    }

    public boolean isFlyCheckEnabled() {
        return flyCheckEnabled;
    }

    public boolean isAutoclickerCheckEnabled() {
        return autoclickerCheckEnabled;
    }

    public boolean isScaffoldCheckEnabled() {
        return scaffoldCheckEnabled;
    }

    // Getter methods for check thresholds
    public double getSpeedThreshold() {
        return speedThreshold;
    }

    public double getReachThreshold() {
        return reachThreshold;
    }

    public double getAutoclickerMinimum() {
        return autoclickerMinimum;
    }
}