package info.preva1l.customenchants.listeners;

import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.CustomEnchant;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class ToolListeners implements Listener {
    private final EnchantManager enchantManager;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        ItemStack tool = e.getPlayer().getActiveItem();
        for (CustomEnchant enchant : enchantManager.getEnchants(tool)) {
            enchant.trigger(e.getPlayer(), tool, e);
        }
    }
}
