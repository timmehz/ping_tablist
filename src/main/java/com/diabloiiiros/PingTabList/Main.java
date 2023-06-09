package com.diabloiiiros.PingTabList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class Main extends JavaPlugin {
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
    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("pingtl")).setExecutor(new PingtlCommand(this));
        new PingOnTabListRunTask(this).runTaskTimerAsynchronously(this, this.getConfig().getInt("update-delay", 5) * 20L, this.getConfig().getInt("update-delay", 5) * 20L);
        getConfig().options().copyDefaults();
        reloadConfig();
        saveDefaultConfig();
        Bukkit.getLogger().info(getConfig().getString("fully-loaded-message", "Ping Tab List has been fully loaded!"));
    }
}
