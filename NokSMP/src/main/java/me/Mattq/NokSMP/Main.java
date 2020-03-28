package me.Mattq.NokSMP;

import me.Mattq.NokSMP.commands.*;
import me.Mattq.NokSMP.events.event_scoreboard;
import net.milkbowl.vault.chat.Chat;
import me.Mattq.NokSMP.events.event_chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Main extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();
    private Chat chat = null;
    File f;


    @Override
    public void onEnable() {
        config.options().copyDefaults(true);
        saveConfig();
        f = new File(getDataFolder(), "rules.yml");
        YamlConfiguration.loadConfiguration(f);
        setupChat();
        setupEvents();
        setupCommands();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            boolean st = true;

            public void run() {
                if (st) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString("broadcast1")));
                    st = false;
                } else if (!st) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString("broadcast2")));
                    st = true;
                }
            }
        }, 0L, 20L * 300); //first is delay time, second is repeating time so 5 min.


        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupCommands() {
        this.getCommand("gmc").setExecutor(new cmd_gmc(this));
        this.getCommand("gms").setExecutor(new cmd_gms(this));
        this.getCommand("gmsp").setExecutor(new cmd_gmsp(this));
        this.getCommand("config").setExecutor(new cmd_config(this));
        this.getCommand("ping").setExecutor(new cmd_ping(this));
        this.getCommand("fly").setExecutor(new cmd_fly(this));
        this.getCommand("announce").setExecutor(new cmd_broadcast(this));
        this.getCommand("rules").setExecutor(new cmd_rules(this));
    }

    private void setupEvents() {
        getServer().getPluginManager().registerEvents(new event_scoreboard(this, chat), this);
        //    getServer().getPluginManager().registerEvents(new event_chat(this), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new event_chat(this, chat), this);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public Chat getChat() {
        return chat;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        File PlayerConfig = new File(getDataFolder() + File.separator + "player" + File.separator + p.getUniqueId()+".yml");
        FileConfiguration userconfig = YamlConfiguration.loadConfiguration(PlayerConfig);

        p.sendMessage(chat.getName());
        if (!PlayerConfig.exists()) {
            try {
                PlayerConfig.getParentFile().mkdir();
                PlayerConfig.createNewFile();
                userconfig.set("rules", false);
                userconfig.save(PlayerConfig);
            } catch (IOException d) {
                d.printStackTrace();
            }
        }


    }
}
