package info.preva1l.customenchants.listeners;

import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import info.preva1l.customenchants.events.PassiveEnchantTriggerEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class PassiveEnchantsListener implements Listener {
    private final EnchantManager enchantManager;

    @EventHandler
    public void onPassiveEnchantTrigger(PassiveEnchantTriggerEvent e) {
        ItemStack tool = switch (e.getType()) {
            case HELMET -> e.getPlayer().getInventory().getHelmet();
            case CHESTPLATE -> e.getPlayer().getInventory().getChestplate();
            case LEGGINGS -> e.getPlayer().getInventory().getLeggings();
            case BOOTS -> e.getPlayer().getInventory().getBoots();
            case MAIN_HAND -> e.getPlayer().getInventory().getItemInMainHand();
            case OFF_HAND -> e.getPlayer().getInventory().getItemInOffHand();
        };
        if (tool == null || tool.getType() == Material.AIR) return;
        for (NotSoCustomEnchant enchant : enchantManager.getEnchants(tool)) {
            enchant.trigger(e.getPlayer(), tool, e);
        }
    }
}
