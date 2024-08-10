package info.preva1l.customenchants.enchants.impl.boots;

import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.enchants.EnchantInfo;
import info.preva1l.customenchants.enchants.EnchantTarget;
import info.preva1l.customenchants.enchants.Rarity;
import info.preva1l.customenchants.events.PassiveEnchantTriggerEvent;
import info.preva1l.customenchants.events.PassiveType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@EnchantInfo(
        id = "heremes_wings",
        name = "Hermes Wings",
        rarity = Rarity.RARE,
        appliesTo = EnchantTarget.BOOTS,
        maxLevel = 3
)
public class HermesWingsEnchant extends CustomEnchant {
    @Override
    public void trigger(Player player, ItemStack enchantedItem, Event callingEvent) {
        if (!(callingEvent instanceof PassiveEnchantTriggerEvent event)) return;
        if (event.getType() != PassiveType.BOOTS) return;

        int level = EnchantManager.getInstance().getLevelFromItemStack(this, enchantedItem);
        player.removePotionEffect(PotionEffectType.SPEED);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 13 * 20, level - 1));
    }
}
