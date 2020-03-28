package me.Mattq.NokSMP.commands;

import me.Mattq.NokSMP.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd_gmsp implements CommandExecutor {
    Main plugin;

    public cmd_gmsp(Main instance) {
        plugin = instance;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Player only command");
        } else {
            Player p = (Player) sender;

            if (p.hasPermission(plugin.getConfig().getString("perm-gamemode"))) {
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg-gmsp")));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("perm-msg")));
            }
        }


        return false;
    }
}