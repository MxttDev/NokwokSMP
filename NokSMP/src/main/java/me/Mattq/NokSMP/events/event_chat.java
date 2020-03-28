package me.Mattq.NokSMP.events;

import me.Mattq.NokSMP.Main;
import net.milkbowl.vault.*;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

public class event_chat implements Listener {

    Main plugin;
    private Chat chat = null;

    public event_chat(Main main, Chat chat) {
        this.plugin = main;
        this.chat = chat;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        String prefix = chat.getPlayerPrefix(p);
        p.sendMessage(chat.getName());

        e.setFormat(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chatmsg").replace("<prefix>", prefix).replace("<message>", msg).replace("<player>", p.getDisplayName())));
    }

}
