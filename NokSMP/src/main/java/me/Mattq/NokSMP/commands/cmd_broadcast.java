package me.Mattq.NokSMP.commands;

import me.Mattq.NokSMP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class cmd_broadcast implements CommandExecutor {
    Main plugin;

    public cmd_broadcast(Main instance) {
        plugin = instance;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission(plugin.getConfig().getString("perm-broadcast"))) {
            if (args.length > 0) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    str.append(args[i] + " ");
                }
                String s = str.toString();


                for (Player a : Bukkit.getOnlinePlayers()) {
                    a.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-broadcast").replace("<message>", s)));
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("usage-broadcast")));
            }


        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("perm-msg")));
        }
        return false;
    }
}

