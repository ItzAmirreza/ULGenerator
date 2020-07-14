package eventlist;
import ir.deadlight.ulgenerator.Utils;
import ir.deadlight.ulgenerator.ULGenerator;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.*;


public class oreevent implements Listener {


    List<String> worldslist = ULGenerator.getInstance().getConfig().getStringList("disabled-worlds");
    ConfigurationSection configurationsection = ULGenerator.getInstance().getConfig().getConfigurationSection("generators-settings.generators");

    Material legacywater = Utils.water;
    Material legacylava = Utils.lava;
    Random random = Utils.random;

    @EventHandler
    public void onhappen(BlockFromToEvent e) {


        Location happenloc = e.getBlock().getLocation();
        Material block1 = e.getBlock().getType();
        Material block2 = e.getToBlock().getType();

        Location block1location = e.getBlock().getLocation();
        Location block2location = e.getToBlock().getLocation();
        Location waterlocation1 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX() + 1, e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ());
        Location waterlocation2 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX() - 1, e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ());
        Location waterlocation3 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX(), e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ() - 1);
        Location waterlocation4 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX(), e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ() + 1);

        if (!(worldslist.contains(e.getBlock().getWorld().getName()))) {



            Location blockl = new Location(e.getBlock().getWorld(), block1location.getBlockX(), block1location.getBlockY() - 1, block1location.getBlockZ());

            configurationsection.getKeys(false).forEach(key -> {
                if (key.equalsIgnoreCase(blockl.getBlock().getType().toString())) {

                    if (block1 == Material.LAVA || block1 == legacylava) {

                        if (waterlocation1.getBlock().getType() == legacywater || waterlocation2.getBlock().getType() == legacywater || waterlocation3.getBlock().getType() == legacywater || waterlocation4.getBlock().getType() == legacywater) {

                            Material[] blocks = new Material[ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks").size()];
                            List<String> listofblocks = ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks");


                            String thatblock = listofblocks.get(random.nextInt(listofblocks.size()));

                            e.setCancelled(true);

                            e.getToBlock().setType(Material.matchMaterial(thatblock));

                        }

                                }}




                    });




            }



        }



}