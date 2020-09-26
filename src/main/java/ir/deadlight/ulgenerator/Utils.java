package ir.deadlight.ulgenerator;

import com.sk89q.worldedit.util.YAMLConfiguration;
import eventlist.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {

    public static String color(String msg) {

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String pluginprefix = "&8[&6UL&9Generator&8] ";


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


    public static Random random = new Random();



    public static void checkForNewConfigs() {


        ConfigurationSection configurationsection = ULGenerator.getInstance().getConfig().getConfigurationSection("generators-settings.generators");
        configurationsection.getKeys(false).forEach(key -> {

            YamlConfiguration configyml = new YamlConfiguration();
            try {
                configyml.load(ULGenerator.getInstance().getDataFolder() + File.separator + "config.yml");
                String thething = configyml.getString("generators-settings.generators." + key + "." + "required-biome");
                if (thething == null) {
                    File config = new File(ULGenerator.getInstance().getDataFolder() + File.separator + "config.yml");

                    try {
                        configyml.load(ULGenerator.getInstance().getDataFolder() + File.separator + "config.yml");
                        configyml.set("generators-settings.generators." + key + "." + "required-biome", "false");
                        configyml.save(config);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

        });

    }

    public static List<String> worldslist = ULGenerator.getInstance().getConfig().getStringList("disabled-worlds");
    public static ConfigurationSection configurationsection = ULGenerator.getInstance().getConfig().getConfigurationSection("generators-settings.generators");
    public static String perm = ULGenerator.getInstance().getConfig().getString("generators-settings.permission");
    public static void reloadCF() {

        worldslist = ULGenerator.getInstance().getConfig().getStringList("disabled-worlds");
        configurationsection = ULGenerator.getInstance().getConfig().getConfigurationSection("generators-settings.generators");
        perm = ULGenerator.getInstance().getConfig().getString("generators-settings.permission");

    }





}
