package commands;

import ir.deadlight.ulgenerator.ULGenerator;
import ir.deadlight.ulgenerator.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ulgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command, String label, String[] args) {
        //player instance
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission(ULGenerator.getInstance().getConfig().getString("admin-permission"))) {

                if (args.length != 1) {

                    player.sendMessage(Utils.color(Utils.pluginprefix + "&7Help page: "));
                    player.sendMessage(Utils.color("&e/ulg disable &7- Disable the plugin. "));
                    player.sendMessage(Utils.color("&e/ulg reload &7- Reload the configuration file. "));

                } else {

                    if (args[0].equalsIgnoreCase("disable")) {

                        ULGenerator.getInstance().getServer().getPluginManager().disablePlugin(ULGenerator.getInstance());
                        player.sendMessage(Utils.color(Utils.pluginprefix + "&cPlugin disabled."));

                    } else if (args[0].equalsIgnoreCase("reload")) {

                        ULGenerator.getInstance().reloadConfig();
                        player.sendMessage(Utils.color(Utils.pluginprefix + "&aConfig has been reloaded."));

                    } else {

                        player.sendMessage(Utils.color(Utils.pluginprefix + "&7Help page: "));
                        player.sendMessage(Utils.color("&e/ulg disable &7- Disable the plugin. "));
                        player.sendMessage(Utils.color("&e/ulg reload &7- Reload the configuration file. "));

                    }

                }
            } else {
                //doesnt have perm so just show the default introduction message.

                player.sendMessage(Utils.color(Utils.pluginprefix + "&a Under Lava Generator | By Dead_Light - &c ver 1.4"));

            }


        } else {
            //console instance
            if (args.length != 1) {

                sender.sendMessage(Utils.color(Utils.pluginprefix + "&7Help page: "));
                sender.sendMessage(Utils.color("&e/ulg disable &7- Disable the plugin. "));
                sender.sendMessage(Utils.color("&e/ulg reload &7- Reload the configuration file. "));

            } else {

                if (args[0].equalsIgnoreCase("disable")) {

                    ULGenerator.getInstance().getServer().getPluginManager().disablePlugin(ULGenerator.getInstance());
                    sender.sendMessage(Utils.color(Utils.pluginprefix + "&cPlugin disabled."));

                } else if (args[0].equalsIgnoreCase("reload")) {

                    ULGenerator.getInstance().reloadConfig();
                    sender.sendMessage(Utils.color(Utils.pluginprefix + "&aConfig has been reloaded."));

                } else {

                    sender.sendMessage(Utils.color(Utils.pluginprefix + "&7Help page: "));
                    sender.sendMessage(Utils.color("&e/ulg disable &7- Disable the plugin. "));
                    sender.sendMessage(Utils.color("&e/ulg reload &7- Reload the configuration file. "));

                }

            }

        }

        return false;
    }
}
