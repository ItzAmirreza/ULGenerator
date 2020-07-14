package commands;

import ir.deadlight.ulgenerator.ULGenerator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reloadcommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if ((args.length == 1) && sender.hasPermission(ULGenerator.getInstance().getConfig().getString("admin-permission"))) {

            if (args[0].equalsIgnoreCase("reload")) {

                if (sender instanceof Player) {

                    ULGenerator.getInstance().reloadConfig();

                    Player player = (Player) sender;

                    player.sendMessage(ChatColor.YELLOW + "ULGenerator Config Successfully Reloaded!");

                } else {

                    ULGenerator.getInstance().reloadConfig();
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "ULGenerator Config Reloaded!");

                }

            } else if (args[0].equalsIgnoreCase("disable")) {

                ULGenerator.getInstance().getServer().getPluginManager().disablePlugin(ULGenerator.getInstance());

                if (sender instanceof Player) {

                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.AQUA + "ULGenerator " + ChatColor.GREEN + "Successfully " + ChatColor.AQUA + "Disabled. ");

                } else {

                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "ULGenerator " + ChatColor.GREEN + "Successfully " + ChatColor.AQUA + "Disabled. ");;

                }




            } else {

                if (sender instanceof Player) {

                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.AQUA + "Under Lava Generator(ULG)" + ChatColor.RED + " Version 1.0");
                    player.sendMessage(ChatColor.AQUA + "Authored And Coded By Dead_Light");
                    player.sendMessage(ChatColor.YELLOW + "Commands List: ");
                    player.sendMessage(ChatColor.GREEN + "/ulg reload " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Reload The Config File.");
                    player.sendMessage(ChatColor.GREEN + "/ulg disable" + ChatColor.GRAY + "- " + ChatColor.AQUA + "Disable the plugin.");


                } else {

                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Under Lava Generator(ULG)" + ChatColor.RED + " Version 1.0");
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Authored And Coded By Dead_Light");
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Commands List: ");
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "/ulg reload " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Reload The Config File.");
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "/ulg disable" + ChatColor.GRAY + "- " + ChatColor.AQUA + "Disable the plugin.");

                }

            }


        } else {

            if (sender instanceof Player) {

                Player player = (Player) sender;
                player.sendMessage(ChatColor.AQUA + "Under Lava Generator(ULG)" + ChatColor.RED + " Version 1.0");
                player.sendMessage(ChatColor.AQUA + "Authored And Coded By Dead_Light");

            } else {

                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Under Lava Generator(ULG)" + ChatColor.RED + " Version 1.0");
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Authored And Coded By Dead_Light");
            }


        }


        return false;
    }
}
