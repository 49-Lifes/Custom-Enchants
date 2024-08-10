package info.preva1l.customenchants.enchants.impl.leggings;

import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.enchants.EnchantInfo;
import info.preva1l.customenchants.enchants.EnchantTarget;
import info.preva1l.customenchants.enchants.Rarity;
import info.preva1l.customenchants.events.PassiveEnchantTriggerEvent;
import info.preva1l.customenchants.events.PassiveType;
import info.preva1l.customenchants.utils.TaskManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

@EnchantInfo(
        id = "party_pants",
        name = "Party Pants",
        rarity = Rarity.LEGENDARY,
        appliesTo = EnchantTarget.LEGGINGS,
        maxLevel = 1
)
public class PartyPantsEnchant extends CustomEnchant {
    @Override
    public void trigger(Player player, ItemStack enchantedItem, Event callingEvent) {
        if (!(callingEvent instanceof PassiveEnchantTriggerEvent event)) return;
        if (event.getType() != PassiveType.LEGGINGS) return;

        player.setGlowing(true);
        TaskManager.Sync.runLater(() -> {
            ItemStack itemStack = player.getInventory().getLeggings();
            if (itemStack != null && (itemStack.getType() == Material.AIR || !EnchantManager.getInstance().hasEnchant(itemStack, this))) {
                player.setGlowing(false);
            }
        }, 20 * 3);
    }
}
