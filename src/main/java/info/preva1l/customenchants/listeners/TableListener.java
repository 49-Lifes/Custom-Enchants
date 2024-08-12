package info.preva1l.customenchants.listeners;

import info.preva1l.customenchants.CustomEnchants;
import info.preva1l.customenchants.menus.EnchantsRarityMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class TableListener implements Listener {
    private final CustomEnchants plugin;

    @EventHandler
    public void onEnchantTableClick(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null || e.getClickedBlock().getType() != Material.ENCHANTING_TABLE) return;

        World world = Bukkit.getWorld(plugin.getConfig().getString("rune-table.world"));
        if (!e.getClickedBlock().getWorld().equals(world)) return;
        double blockX = plugin.getConfig().getDouble("rune-table.x");
        double blockY = plugin.getConfig().getDouble("rune-table.y");
        double blockZ = plugin.getConfig().getDouble("rune-table.z");

        Location location = new Location(world, blockX, blockY, blockZ);

        if (!e.getClickedBlock().getLocation().equals(location)) return;

        e.setCancelled(true);

        new EnchantsRarityMenu().open(e.getPlayer());
    }
}
