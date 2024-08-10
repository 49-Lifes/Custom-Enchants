package info.preva1l.customenchants.enchants.impl.helmet;

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
        id = "cat_eyes",
        name = "Cat Eyes",
        rarity = Rarity.EPIC,
        appliesTo = EnchantTarget.HELMET,
        maxLevel = 1
)
public class CatEyesEnchant extends CustomEnchant {
    @Override
    public void trigger(Player player, ItemStack enchantedItem, Event callingEvent) {
        if (!(callingEvent instanceof PassiveEnchantTriggerEvent event)) return;
        if (event.getType() != PassiveType.HELMET) return;

        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 13 * 20, 0));
    }
}
