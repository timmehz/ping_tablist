package com.diabloiiiros.PingTabList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PingOnTabListRunTask extends BukkitRunnable {
    /*
        ** Do NOT Delete this comment multiline **
        Copyright (c) :
            - diabloIIIROS        - Spigot
            - Diablo3ros1         - Minecraft
            - diabloiiiros        - Discord
            - timmehz             - GitHub
        2023
        For more information visit: https://github.com/timmehz/ping_tablist/blob/main/README.md section License
    */
    private final Main pl;
    public PingOnTabListRunTask(Main pl) { this.pl = pl; }
    @Override
    public void run() {
        for (Player players : pl.getServer().getOnlinePlayers()) {
            if (pl.getConfig().getBoolean("restrict-ping-by-perm", false)) {
                if (players.hasPermission("pingtl.ping")) {
                    PingTabList(
                        players,
                        ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("brackets-left", "&7[")),
                        ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("brackets-right", "&7]"))
                    );
                }
            } else if (!pl.getConfig().getBoolean("restrict-ping-by-perm", false)) {
                PingTabList(
                    players,
                    ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("brackets-left", "&7[")),
                    ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("brackets-right", "&7]"))
                );
            }
        }
    }
    private void PingTabList(Player ps, String bL, String bR) {
        String name = pl.getConfig().getBoolean("show-display-name", false) ? ps.getDisplayName() : ps.getName();
        ChatColor color;
        if (ps.getPing() <= pl.getConfig().getInt("green-ping-max", 150)) {
            color = ChatColor.valueOf(pl.getConfig().getString("green-color-ping", "GREEN"));
        } else if (ps.getPing() <= pl.getConfig().getInt("yellow-ping-max", 220)) {
            color = ChatColor.valueOf(pl.getConfig().getString("yellow-color-ping", "YELLOW"));
        } else {
            color = ChatColor.valueOf(pl.getConfig().getString("red-color-ping", "RED"));
        }
        String formattedPing = color + Integer.toString(ps.getPing());
        if (pl.getConfig().getString("ping-position", "behind").equalsIgnoreCase("behind")) {
            if (pl.getConfig().getBoolean("show-ping-brackets", true)) {
                ps.setPlayerListName(name + ChatColor.RESET + pl.getConfig().getString("spaces", " ") + bL + ChatColor.RESET + formattedPing + ChatColor.RESET + bR + ChatColor.RESET);
            } else if (!pl.getConfig().getBoolean("show-ping-brackets", true)) {
                ps.setPlayerListName(name + ChatColor.RESET + pl.getConfig().getString("spaces", " ") + formattedPing + ChatColor.RESET);
            }
        } else if (pl.getConfig().getString("ping-position", "behind").equalsIgnoreCase("front")) {
            if (pl.getConfig().getBoolean("show-ping-brackets", true)) {
                ps.setPlayerListName(bL + ChatColor.RESET + formattedPing + ChatColor.RESET + bR + ChatColor.RESET + pl.getConfig().getString("spaces", " ") + name + ChatColor.RESET);
            } else if (!pl.getConfig().getBoolean("show-ping-brackets", true)) {
                ps.setPlayerListName(formattedPing + ChatColor.RESET + pl.getConfig().getString("spaces", " ") + name + ChatColor.RESET);
            }
        } else {
            Bukkit.getLogger().severe(pl.getConfig().getString("ping-position-error", "Invalid value for 'ping-position' in config.yml"));
        }
    }
}
