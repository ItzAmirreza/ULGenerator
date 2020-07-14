package eventlist;

import ir.deadlight.ulgenerator.Utils;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import ir.deadlight.ulgenerator.ULGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ASkyblockoreevent implements Listener {


    private ASkyBlockAPI api;

    public ASkyblockoreevent() {
        api = ASkyBlockAPI.getInstance();
    }

    Material legacywater = Utils.water;
    Material legacylava = Utils.lava;

    List<String> worldslist = ULGenerator.getInstance().getConfig().getStringList("disabled-worlds");
    ConfigurationSection configurationsection = ULGenerator.getInstance().getConfig().getConfigurationSection("generators-settings.generators");
    String perm = ULGenerator.getInstance().getConfig().getString("generators-settings.permission");


    @EventHandler
    public void onhappen(BlockFromToEvent e) {

        if (!(worldslist.contains(e.getBlock().getWorld().getName()))) {
            Location happenloc = e.getBlock().getLocation();
            Material block1 = e.getBlock().getType();
            Material block2 = e.getToBlock().getType();
            Location block1location = e.getBlock().getLocation();
            Location block2location = e.getToBlock().getLocation();
            Location waterlocation1 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX() + 1, e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ());
            Location waterlocation2 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX() - 1, e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ());
            Location waterlocation3 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX(), e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ() - 1);
            Location waterlocation4 = new Location(e.getToBlock().getWorld(), e.getToBlock().getLocation().getBlockX(), e.getToBlock().getLocation().getBlockY(), e.getToBlock().getLocation().getBlockZ() + 1);
            try {
                UUID islandowneruuid = api.getOwner(e.getToBlock().getLocation());
                Player islandowner = Bukkit.getPlayer(islandowneruuid);
                if (islandowner.hasPermission(perm) || perm == "none") {
                    int islandlevel = api.getIslandLevel(islandowneruuid);
                    Location blockl = new Location(e.getBlock().getWorld(), block1location.getBlockX(), block1location.getBlockY() - 1, block1location.getBlockZ());

                    configurationsection.getKeys(false).forEach(key -> {
                        if (key.equalsIgnoreCase(blockl.getBlock().getType().toString())) {

                            if (islandlevel >= ULGenerator.getInstance().getConfig().getInt("generators-settings.generators." + key + ".required-island-level")) {
                                if (block1 == Material.LAVA || block1 == legacylava) {

                                    if (waterlocation1.getBlock().getType() == legacywater || waterlocation2.getBlock().getType() == legacywater  || waterlocation3.getBlock().getType() == legacywater  || waterlocation4.getBlock().getType() == legacywater) {

                                        Material[] blocks = new Material[ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks").size()];
                                        List<String> listofblocks = ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks");

                                        Random rand = new Random();


                                        String thatblock = listofblocks.get(rand.nextInt(listofblocks.size()));

                                        e.setCancelled(true);

                                        e.getToBlock().setType(Material.matchMaterial(thatblock));

                                    }

                                }

                            }

                            }


                    });


                }



            } catch (Exception d) {
                //do nothing
            }








        }



    }



}