package me.Mattq.NokSMP.commands;

import me.Mattq.NokSMP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;


import java.io.File;
import java.io.IOException;


public class cmd_rules implements CommandExecutor {
    Main plugin;

    public cmd_rules(Main instance) {
        plugin = instance;
    }
    Server server = Bukkit.getServer();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Player only command");
        } else {
            Player p = (Player) sender;
            File f = new File(plugin.getDataFolder() + File.separator + "player" + File.separator + p.getUniqueId()+".yml");
            FileConfiguration user = YamlConfiguration.loadConfiguration(f);

            String rules = user.getString("rules");
            if (rules.equals("false")) {
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) book.getItemMeta();
                meta.setAuthor("Nokwok");
                meta.setTitle(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("booktitle")));
                for (int i = 1; i < 17; i++) {
                    meta.addPage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("rule"+i)));
                }
                book.setItemMeta(meta);
                p.getInventory().addItem(book);
                user.set("rules", "true");
                try {
                    user.save(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("rulesdone")));
            }



        }


        return false;
    }



}
