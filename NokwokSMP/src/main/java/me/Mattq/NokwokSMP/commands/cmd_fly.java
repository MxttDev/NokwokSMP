package me.Mattq.NokwokSMP.commands;

import me.Mattq.NokwokSMP.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_fly implements CommandExecutor {
    Main plugin;

    public cmd_fly(Main instance) {
        plugin = instance;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Player only command");
        } else {
            Player p = (Player) sender;

            if (p.hasPermission(plugin.getConfig().getString("perm-fly"))) {
                if (p.isFlying()) {
                    p.setFlying(false);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-fly-1")));
                } else {
                    p.setFlying(true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-fly-2")));
                }


            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("perm-msg")));
            }
        }


        return false;
    }
}
