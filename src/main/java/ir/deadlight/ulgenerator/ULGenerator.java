package ir.deadlight.ulgenerator;
import commands.ulgCommand;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;



public final class ULGenerator extends JavaPlugin {

    public static ULGenerator instance;

    public static ULGenerator getInstance() {

        return instance;
    }



    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        if (!getConfig().getBoolean("enable-plugin-on-startup")) {
            getServer().getConsoleSender().sendMessage(Utils.color(Utils.pluginprefix + "&cULGenerator didn't load up because it is disabled in config.yml file!"));
            Bukkit.getPluginManager().disablePlugin(this);

        } else {

            getServer().getConsoleSender().sendMessage(Utils.color(Utils.pluginprefix + "&aChecking for SkyBlock Plugin..."));

            //Checkin for plugins
            Utils.findSkyBlockPlugin();
            getServer().getPluginCommand("ulg").setExecutor(new ulgCommand());
            getServer().getConsoleSender().sendMessage(Utils.color(Utils.pluginprefix + "&aULGenerator Has Been Enabled. | Authored & Coded By &dDead_Light &a<3"));


        }



    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage(Utils.color(Utils.pluginprefix + "&cULGenerator Has Been Disabled. | Authored & Coded By &dDead_Light &a<3"));
        getServer().getConsoleSender().sendMessage(Utils.color(Utils.pluginprefix + "Integration with Skyblock plugin Disabled"));

    }



}



