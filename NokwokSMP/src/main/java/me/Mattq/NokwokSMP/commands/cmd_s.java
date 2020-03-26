package me.Mattq.NokwokSMP.commands;

import me.Mattq.NokwokSMP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_s implements CommandExecutor {
    Main plugin;

    public cmd_s(Main instance) {
        plugin = instance;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Player only command");
        } else {
            Player p = (Player) sender;

            if (p.hasPermission(plugin.getConfig().getString("perm-sc"))) {
                if (args.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for(int i = 1; i < args.length; i++)
                        sb.append(args[i]).append(" ");

                    String s = sb.toString();


                    Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-staff").replace("<player>", p.getDisplayName()).replace("<message>", s)), plugin.getConfig().getString("perms-sc"));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-staff")));
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("perm-msg")));
            }
        }


        return false;
    }
}