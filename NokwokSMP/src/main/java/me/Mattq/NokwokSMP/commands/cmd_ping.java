package me.Mattq.NokwokSMP.commands;

import me.Mattq.NokwokSMP.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_ping implements CommandExecutor {
    Main plugin;

    public cmd_ping(Main instance) {
        plugin = instance;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Player only command");
        } else {
            Player p = (Player) sender;

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-ping").replace("<ping>", "10")));

        }


        return false;
    }
}