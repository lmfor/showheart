package com.lmfor.github.showHeart;


import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShowHeart extends JavaPlugin {

    private static ShowHeart instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getLogger().info("  ____  _                   _   _                 _   ");
        getLogger().info(" / ___|| |__   _____      _| | | | ___  __ _ _ __| |_ ");
        getLogger().info(" \\___ \\| '_ \\ / _ \\ \\ /\\ / / |_| |/ _ \\/ _` | '__| __|");
        getLogger().info("  ___) | | | | (_) \\ V  V /|  _  |  __/ (_| | |  | |_ ");
        getLogger().info(" |____/|_| |_|\\___/ \\_/\\_/ |_| |_|\\___|\\__,_|_|   \\__|");



    }

    @Override
    public void onDisable() {
        getLogger().info("ShowHeart is shutting down...");
    }

}
