package info.preva1l.customenchants.enchants;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface NotSoCustomEnchant {
    void trigger(Player player, ItemStack enchantedItem, Event callingEvent);

    String getId();

    String getName();

    List<EnchantTarget> getTargets();

    int getMaxLevel();

    Rarity getRarity();

    String targetString();
}
