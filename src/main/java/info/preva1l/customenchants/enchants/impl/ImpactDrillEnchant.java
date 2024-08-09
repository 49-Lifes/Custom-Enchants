package info.preva1l.customenchants.enchants.impl;

import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.enchants.EnchantInfo;
import info.preva1l.customenchants.enchants.EnchantTarget;
import info.preva1l.customenchants.enchants.Rarity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

@EnchantInfo(
        id = "impact_drill",
        name = "Impact Drill",
        rarity = Rarity.RARE,
        appliesTo = EnchantTarget.PICKAXE,
        maxLevel = 3
)
public class ImpactDrillEnchant extends CustomEnchant {
    @Override
    public void trigger(Player player, ItemStack enchantedItem, Event var1) {
        if (!(var1 instanceof BlockBreakEvent event)) return;

        event.getBlock();
    }
}
