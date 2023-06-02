package com.diabloiiiros.PingTabList;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class PingtlCommand implements CommandExecutor, TabCompleter {
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
                        sender.sendMessage(ChatColor.GREEN + "config.yml have been reloaded");
                    } else if (!sender.hasPermission("pingtl.reload")) {
                        if (pl.getConfig().getString("no-permission", "&cYou do not have permission to use this command.").contains("&")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("no-permission", "You do not have permission to use this command.")));
                        } else {
                            sender.sendMessage(pl.getConfig().getString("no-permission", "You do not have permission to use this command."));
                        }
                    }
                } else if (sender instanceof ConsoleCommandSender) {
                    pl.reloadConfig();
                    pl.saveDefaultConfig();
                    pl.console.warning("config.yml have been reloaded");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "invalid argument : '" + args[0] + "'");
            }
        } else if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments has been entered");
        } else if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "To many arguments has been entered");
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
