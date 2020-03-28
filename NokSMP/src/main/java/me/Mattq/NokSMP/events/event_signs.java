package me.Mattq.NokSMP.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class event_signs implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Player p = e.getPlayer();
        String[] lines = e.getLines();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

        }
    }



}
