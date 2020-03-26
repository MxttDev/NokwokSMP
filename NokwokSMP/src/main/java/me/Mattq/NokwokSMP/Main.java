package me.Mattq.NokwokSMP;

import me.Mattq.NokwokSMP.commands.*;
import me.Mattq.NokwokSMP.events.event_scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    FileConfiguration config = getConfig();
    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable() {
        config.options().copyDefaults(true);
        saveConfig();

        this.getCommand("gmc").setExecutor(new cmd_gmc(this));
        this.getCommand("gms").setExecutor(new cmd_gms(this));
        this.getCommand("gmsp").setExecutor(new cmd_gmsp(this));
        this.getCommand("config").setExecutor(new cmd_config(this));
        this.getCommand("ping").setExecutor(new cmd_ping(this));
        this.getCommand("fly").setExecutor(new cmd_fly(this));
        this.getCommand("announce").setExecutor(new cmd_broadcast(this));
        createCustomConfig();
        getServer().getPluginManager().registerEvents(new event_scoreboard(),this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            boolean st = true;
            public void run() {
                if (st) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',config.getString("broadcast1")));
                    st = false;
                } else if (!st) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',config.getString("broadcast2")));
                    st = true;
                }
            }
        }, 0L, 20L * 300); //first is delay time, second is repeating time so 5 min.
    }


    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "rules.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("rules.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
