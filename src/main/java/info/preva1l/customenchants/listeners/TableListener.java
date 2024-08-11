package info.preva1l.customenchants.listeners;

import info.preva1l.customenchants.menus.EnchantsRarityMenu;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TableListener implements Listener {
    @EventHandler
    public void onEnchantTableClick(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null || e.getClickedBlock().getType() != Material.ENCHANTING_TABLE) return;

        e.setCancelled(true);

        new EnchantsRarityMenu().open(e.getPlayer());
    }
}
