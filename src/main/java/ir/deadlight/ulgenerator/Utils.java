package ir.deadlight.ulgenerator;

import eventlist.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {

    public static String color(String msg) {

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String pluginprefix = "&8[&eUL&bGenerator&8] ";


     public static void findSkyBlockPlugin() {

         if (Bukkit.getPluginManager().getPlugin("ASkyBlock") != null) {

             ULGenerator.getInstance().getServer().getPluginManager().registerEvents(new ASkyblockoreevent(), ULGenerator.getInstance());
             Bukkit.getConsoleSender().sendMessage(color(pluginprefix + "&aYou are using ASkyblock as an island plugin."));
         } else if (Bukkit.getPluginManager().getPlugin("BentoBox") != null) {

             ULGenerator.getInstance().getServer().getPluginManager().registerEvents(new Bentoboxorevent(), ULGenerator.getInstance());
             Bukkit.getConsoleSender().sendMessage(color(pluginprefix + "&aYou are using BentoBox as an island plugin."));
         } else if (Bukkit.getPluginManager().getPlugin("uSkyBlock") != null) {

             ULGenerator.getInstance().getServer().getPluginManager().registerEvents(new uskyblockorevent(), ULGenerator.getInstance());
             Bukkit.getConsoleSender().sendMessage(color(pluginprefix + "&aYou are using uSkyblock as an island plugin."));
         } else if (Bukkit.getPluginManager().getPlugin("SuperiorSkyblock2") != null) {

             ULGenerator.getInstance().getServer().getPluginManager().registerEvents(new Superiorskyblockorevent(), ULGenerator.getInstance());
             Bukkit.getConsoleSender().sendMessage(color(pluginprefix + "&aYou are using SuperiorSkyblock2 as an island plugin."));
         } else if (Bukkit.getPluginManager().getPlugin("FabledSkyBlock") != null) {

             ULGenerator.getInstance().getServer().getPluginManager().registerEvents(new FabledSkyblockoreevent(), ULGenerator.getInstance());
             Bukkit.getConsoleSender().sendMessage(color(pluginprefix + "&aYou are using FabledSkyBlock as an island plugin."));
         } else {
             ULGenerator.getInstance().getServer().getConsoleSender().sendMessage(color(pluginprefix + "&eYou are not using any supported island Plugin - Switched to Vanilla version"));
             ULGenerator.getInstance().getServer().getConsoleSender().sendMessage(color(pluginprefix + "&ePermission & Level check will be disabled..."));
             ULGenerator.getInstance().getServer().getPluginManager().registerEvents(new oreevent(), ULGenerator.getInstance());
         }

     }

    public static boolean isNewVersion() {

        return Arrays.stream(Material.values())
                .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
    }


    public static Material water = Material.matchMaterial(isNewVersion() ? "WATER" : "STATIONARY_WATER");
    public static Material lava = Material.matchMaterial(isNewVersion() ? "LAVA" : "STATIONARY_LAVA");

}
