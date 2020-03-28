package me.Mattq.NokSMP.events;

import me.Mattq.NokSMP.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class event_join implements Listener {

    Main plugin;

    public event_join(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

    }
}
