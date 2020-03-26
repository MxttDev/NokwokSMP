package me.Mattq.NokwokSMP.commands;

import me.Mattq.NokwokSMP.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_config implements CommandExecutor {
    Main plugin;

    public cmd_config(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command!");
        } else {
            Player p = (Player) sender;
            if (p.hasPermission(plugin.getConfig().getString("perm-config")))
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                            plugin.reloadConfig();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-config")));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("usage-config")));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("usage-config")));
                }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("perm-msg")));
            }
        }


        return false;
    }
}
