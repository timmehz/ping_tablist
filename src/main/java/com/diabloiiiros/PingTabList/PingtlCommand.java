package com.diabloiiiros.PingTabList;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class PingtlCommand implements CommandExecutor, TabCompleter {
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
    public PingtlCommand(Main pl) { this.pl = pl; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("pingtl.reload")) {
                        configReload(sender);
                    } else if (!sender.hasPermission("pingtl.reload")) {
                        if (pl.getConfig().getString("no-permission", "You do not have permission to use this command.").contains("&")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("no-permission", "You do not have permission to use this command.")));
                        } else {
                            sender.sendMessage(pl.getConfig().getString("no-permission", "You do not have permission to use this command."));
                        }
                    }
                } else if (sender instanceof ConsoleCommandSender) {
                    configReload(sender);
                }
            } else if (args[0].equalsIgnoreCase("copyright")) {
                sender.sendMessage(
                    "Copyright (c) :\n" +
                    "   - diabloIIIROS        - Spigot\n" +
                    "   - Diablo3ros1         - Minecraft\n" +
                    "   - diabloiiiros        - Discord\n" +
                    "   - timmehz             - GitHub\n" +
                    "2023"
                );
                sender.sendMessage("For more information visit: https://github.com/timmehz/ping_tablist/blob/main/README.md section License");
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
            arguments.add("copyright");
            return arguments;
        }
        return null;
    }
    private void configReload(CommandSender s) {
        pl.reloadConfig();
        pl.saveDefaultConfig();
        if (pl.getConfig().getString("config-reloaded", "config.yml has been reloaded").contains("&")) {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("config-reloaded", "config.yml have been reloaded")));
        } else if (!pl.getConfig().getString("config-reloaded", "config.yml has been reloaded").contains("&")) {
            s.sendMessage(pl.getConfig().getString("config-reloaded", "config.yml have been reloaded"));
        }
    }
}
