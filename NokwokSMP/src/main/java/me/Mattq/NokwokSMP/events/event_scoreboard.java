package me.Mattq.NokwokSMP.events;

import me.Mattq.NokwokSMP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class event_scoreboard implements Listener {

    int total = Bukkit.getOnlinePlayers().size();


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj    = board.registerNewObjective("Main", "dummy", ChatColor.translateAlternateColorCodes('&',"&b&lNokwok"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score blank = obj.getScore(ChatColor.GRAY+" ");
        blank.setScore(6);
        Score name = obj.getScore(ChatColor.GRAY+"Name: "+ChatColor.AQUA+p.getDisplayName());
        name.setScore(5);
        Score rank = obj.getScore(ChatColor.GRAY+"Rank: "+ChatColor.AQUA+"[N/A]");
        rank.setScore(4);
        Score blank1 = obj.getScore(ChatColor.GRAY+"  ");
        blank1.setScore(3);
        Score server = obj.getScore(ChatColor.GRAY+"Server: "+ChatColor.AQUA+"SMP");
        server.setScore(2);
        Score online = obj.getScore(ChatColor.GRAY+"Online: "+ChatColor.AQUA+total);
        online.setScore(1);

        p.setScoreboard(board);
    }
}
