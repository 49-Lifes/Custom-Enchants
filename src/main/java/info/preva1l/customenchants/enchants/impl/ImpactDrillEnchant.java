package info.preva1l.customenchants.enchants.impl;

import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.enchants.EnchantInfo;
import info.preva1l.customenchants.enchants.EnchantTarget;
import info.preva1l.customenchants.enchants.Rarity;
import info.preva1l.customenchants.utils.TaskManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EnchantInfo(
        id = "impact_drill",
        name = "Impact Drill",
        rarity = Rarity.RARE,
        appliesTo = EnchantTarget.PICKAXE,
        maxLevel = 2
)
public class ImpactDrillEnchant extends CustomEnchant {
    private static final int LEVEL_ONE_RADIUS = 1;
    private static final int LEVEL_TWO_RADIUS = 2;
    private static final List<Material> BLACKLISTED_BLOCKS = List.of(Material.BEDROCK, Material.BARRIER, Material.COMMAND_BLOCK);

    @Override
    public void trigger(Player player, ItemStack enchantedItem, Event var1) {
        if (!(var1 instanceof BlockBreakEvent event)) return;

        int level = EnchantManager.getInstance().getLevelFromItemStack(this, enchantedItem);

        Location blockLocation = event.getBlock().getLocation();

        event.setCancelled(true);

        int radius = switch (level) {
            case 1:
                yield LEVEL_ONE_RADIUS;
            case 2:
                yield LEVEL_TWO_RADIUS;
            default:
                throw new IllegalStateException("Impact Drill Max Level is %s".formatted(getMaxLevel()));
        };

        getCube(blockLocation, radius).thenAccept(cube -> drill(cube, enchantedItem));
    }

    private void drill(List<Location> cube, ItemStack tool) {
        TaskManager.Sync.run(() -> {
            for (Location location : cube) {
                Block block = location.getWorld().getBlockAt(location);
                if (BLACKLISTED_BLOCKS.contains(block.getType())) continue;
                block.breakNaturally(tool);
            }
        });
    }

    private CompletableFuture<List<Location>> getCube(Location origin, int radius) {
        return CompletableFuture.supplyAsync(() -> {
            List<Location> points = new ArrayList<>();

            final int x = (int) origin.getX();
            final int y = (int) origin.getY();
            final int z = (int) origin.getZ();

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dz = -radius; dz <= radius; dz++) {
                        if (radius > 1) {
                            points.add(new Location(origin.getWorld(), x + dx, y + dy + 1, z + dz));
                        } else {
                            points.add(new Location(origin.getWorld(), x + dx, y + dy, z + dz));
                        }
                    }
                }
            }

            return points;
        });
    }
}
