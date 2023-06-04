package com.diabloiiiros.PingTabList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PingOnTabListRunTask extends BukkitRunnable {
    /*
        ** Do NOT Delete this comment, you have to include this notice in all files **
        Copyright (c) diabloIIIROS / timmehz 2023
        All original code is under copyright under diabloIIIROS / timmehz
        For more information visit: https://github.com/timmehz/ping_tablist/blob/main/README.md section License
    */
    private final Main pl;
    public PingOnTabListRunTask(Main pl) { this.pl = pl; }
    @Override
    public void run() {
        String bracketsLeft = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("brackets-left", "&7["));
        String bracketsRight = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("brackets-right", "&7]"));
        for (Player players : pl.getServer().getOnlinePlayers()) {
            String name = pl.getConfig().getBoolean("show-display-name", false) ? players.getDisplayName() : players.getName();
            ChatColor color;
            if (players.getPing() <= pl.getConfig().getInt("green-ping-max", 150)) {
                color = ChatColor.valueOf(pl.getConfig().getString("green-color-ping", "GREEN"));
            } else if (players.getPing() <= pl.getConfig().getInt("yellow-ping-max", 220)) {
                color = ChatColor.valueOf(pl.getConfig().getString("yellow-color-ping", "YELLOW"));
            } else {
                color = ChatColor.valueOf(pl.getConfig().getString("red-color-ping", "RED"));
            }
            String formattedPing = color + Integer.toString(players.getPing());
            if (pl.getConfig().getString("ping-position", "behind").equalsIgnoreCase("behind")) {
                if (pl.getConfig().getBoolean("show-ping-brackets", true)) {
                    players.setPlayerListName(name + ChatColor.RESET + " " + bracketsLeft + ChatColor.RESET + formattedPing + ChatColor.RESET + bracketsRight + ChatColor.RESET);
                } else if (!pl.getConfig().getBoolean("show-ping-brackets", true)) {
                    players.setPlayerListName(name + ChatColor.RESET + " " + formattedPing + ChatColor.RESET);
                }
            } else if (pl.getConfig().getString("ping-position", "behind").equalsIgnoreCase("front")) {
                if (pl.getConfig().getBoolean("show-ping-brackets", true)) {
                    players.setPlayerListName(bracketsLeft + ChatColor.RESET + formattedPing + ChatColor.RESET + bracketsRight + ChatColor.RESET + " " + name + ChatColor.RESET);
                } else if (!pl.getConfig().getBoolean("show-ping-brackets", true)) {
                    players.setPlayerListName(formattedPing + ChatColor.RESET + " " + name + ChatColor.RESET);
                }
            } else {
                Bukkit.getLogger().severe(pl.getConfig().getString("ping-position-error", "Invalid value for 'ping-position' in config.yml"));
            }
        }
    }
}
