package info.preva1l.customenchants.enchants.impl.sword;

import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.enchants.EnchantInfo;
import info.preva1l.customenchants.enchants.EnchantTarget;
import info.preva1l.customenchants.enchants.Rarity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

@EnchantInfo(
        id = "trickster",
        name = "Trickster",
        description = {"Has a chance to scramble your", "opponents hotbar slots."},
        rarity = Rarity.RARE,
        appliesTo = EnchantTarget.SWORD,
        maxLevel = 4
)
public class TricksterEnchant extends CustomEnchant {
    @Override
    public void trigger(Player player, ItemStack enchantedItem, Event callingEvent) {

    }
}
