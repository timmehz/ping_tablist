package com.diabloiiiros.PingTabList;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class PingtlCommand implements CommandExecutor, TabCompleter {
    /*
        ** Do NOT Delete this comment, you have to include this notice in all files **
        Copyright (c) diabloIIIROS / timmehz 2023
        All original code is under copyright under diabloIIIROS / timmehz
        For more information visit: https://github.com/timmehz/ping_tablist/blob/main/README.md section License
    */
    private final Main pl;
    public PingtlCommand(Main pl) { this.pl = pl; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("pingtl.reload")) {
                        pl.reloadConfig();
                        pl.saveDefaultConfig();
                        if (pl.getConfig().getString("config-reloaded", "config.yml have been reloaded").contains("&")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("config-reloaded", "config.yml have been reloaded")));
                        } else {
                            sender.sendMessage(pl.getConfig().getString("config-reloaded", "config.yml have been reloaded"));
                        }
                    } else if (!sender.hasPermission("pingtl.reload")) {
                        if (pl.getConfig().getString("no-permission", "You do not have permission to use this command.").contains("&")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("no-permission", "You do not have permission to use this command.")));
                        } else {
                            sender.sendMessage(pl.getConfig().getString("no-permission", "You do not have permission to use this command."));
                        }
                    }
                } else if (sender instanceof ConsoleCommandSender) {
                    pl.reloadConfig();
                    pl.saveDefaultConfig();
                    if (pl.getConfig().getString("config-reloaded", "config.yml have been reloaded").contains("&")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("config-reloaded", "config.yml have been reloaded")));
                    } else {
                        sender.sendMessage(pl.getConfig().getString("config-reloaded", "config.yml have been reloaded"));
                    }
                }
            } else {
                if (pl.getConfig().getString("invalid-arg", "invalid argument : '" + args[0] + "'").contains("&")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("invalid-arg", "invalid argument : '" + args[0] + "'").replace("%arg%", args[0])));
                } else {
                    sender.sendMessage(pl.getConfig().getString("invalid-arg", "invalid argument : '" + args[0] + "'").replace("%arg%", args[0]));
                }
            }
        } else if (args.length < 1) {
            if (pl.getConfig().getString("not-enough-args", "Not enough arguments has been entered").contains("&")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("not-enough-args", "Not enough arguments has been entered")));
            } else {
                sender.sendMessage(pl.getConfig().getString("not-enough-args", "Not enough arguments has been entered"));
            }
        } else if (args.length > 1) {
            if (pl.getConfig().getString("to-many-args", "To many arguments has been entered").contains("&")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("to-many-args", "To many arguments has been entered")));
            } else {
                sender.sendMessage(pl.getConfig().getString("to-many-args", "To many arguments has been entered"));
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            if (sender.hasPermission("pingtablist.reload")) {
                arguments.add("reload");
            }
            return arguments;
        }
        return null;
    }
}
