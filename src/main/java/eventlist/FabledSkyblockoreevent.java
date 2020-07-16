package eventlist;
import ir.deadlight.ulgenerator.Utils;
import com.songoda.skyblock.SkyBlock;
import ir.deadlight.ulgenerator.ULGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FabledSkyblockoreevent implements Listener {


    private SkyBlock api;
    public FabledSkyblockoreevent() {
        api = (SkyBlock) Bukkit.getPluginManager().getPlugin("FabledSkyBlock");
    }

    Material legacywater = Utils.water;
    Material legacylava = Utils.lava;
    public int low = 1;
    public int high = 100;
    Random random = Utils.random;
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
                UUID islandowneruuid = api.getIslandManager().getIslandAtLocation(e.getToBlock().getLocation()).getOwnerUUID();
                Player islandowner = Bukkit.getPlayer(islandowneruuid);
                if (islandowner.hasPermission(perm) || perm == "none") {
                    OfflinePlayer offplayer = Bukkit.getOfflinePlayer(islandowneruuid);
                    int islandlevel = (int) api.getIslandManager().getIsland(offplayer).getLevel().getLevel();
                    Location blockl = new Location(e.getBlock().getWorld(), block1location.getBlockX(), block1location.getBlockY() - 1, block1location.getBlockZ());

                    configurationsection.getKeys(false).forEach(key -> {
                        if (key.equalsIgnoreCase(blockl.getBlock().getType().toString())) {

                            if (islandlevel >= ULGenerator.getInstance().getConfig().getInt("generators-settings.generators." + key + ".required-island-level")) {
                                if (block1 == Material.LAVA || block1 == legacylava) {

                                    if (waterlocation1.getBlock().getType() == legacywater || waterlocation2.getBlock().getType() == legacywater || waterlocation3.getBlock().getType() == legacywater || waterlocation4.getBlock().getType() == legacywater) {

                                        String requiredb = ULGenerator.getInstance().getConfig().getString("generators-settings.generators." + key + "." + "required-biome");
                                        if (requiredb.equalsIgnoreCase("false")) {

                                            Material[] blocks = new Material[ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks").size()];
                                            List<String> listofblocks = ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks");
                                            boolean vaziat = false;
                                            while (!vaziat) {
                                                String thatblock = listofblocks.get(random.nextInt(listofblocks.size()));
                                                int randomgeneratednumber = random.nextInt(high - low) + low;
                                                String gtsmaterial = thatblock.split(":")[0];
                                                int percent = Integer.parseInt(thatblock.split(":")[1]);

                                                if (randomgeneratednumber <= percent) {

                                                    vaziat = true;
                                                    e.setCancelled(true);

                                                    e.getToBlock().setType(Material.matchMaterial(gtsmaterial));
                                                }
                                            }


                                        } else {
                                            if (requiredb == null) return;
                                            if (Biome.valueOf(requiredb) == e.getBlock().getBiome()) {

                                                Material[] blocks = new Material[ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks").size()];
                                                List<String> listofblocks = ULGenerator.getInstance().getConfig().getStringList("generators-settings.generators." + key + "." + "blocks");
                                                boolean vaziat = false;
                                                while (!vaziat) {
                                                    String thatblock = listofblocks.get(random.nextInt(listofblocks.size()));
                                                    int randomgeneratednumber = random.nextInt(high - low) + low;
                                                    String gtsmaterial = thatblock.split(":")[0];
                                                    int percent = Integer.parseInt(thatblock.split(":")[1]);

                                                    if (randomgeneratednumber <= percent) {

                                                        vaziat = true;
                                                        e.setCancelled(true);

                                                        e.getToBlock().setType(Material.matchMaterial(gtsmaterial));
                                                    }
                                                }
                                            }
                                        }

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
