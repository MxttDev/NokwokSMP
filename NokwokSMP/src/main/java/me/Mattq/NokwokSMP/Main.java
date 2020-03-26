package me.Mattq.NokwokSMP;

import me.Mattq.NokwokSMP.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    FileConfiguration config = getConfig();

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


        int num = 1;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                if (num == 1) {
                    int num = 2;
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',config.getString("broadcast1")));
                } else if (num == 2) {
                    int num = 1;
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',config.getString("broadcast2")));
                }
            }
        }, 0L, 20L * 300); //first is delay time, second is repeating time so 5 min.



    }
}
