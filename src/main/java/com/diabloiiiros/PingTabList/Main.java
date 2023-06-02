package com.diabloiiiros.PingTabList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    public Logger console = Bukkit.getLogger();
    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("pingtl")).setExecutor(new PingtlCommand(this));
        new PingOnTabListRunTask(this).runTaskTimerAsynchronously(this, this.getConfig().getInt("update-delay", 5) * 20L, this.getConfig().getInt("update-delay", 5) * 20L);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        console.info("Ping Tab List has been fully loaded!");
    }
}
