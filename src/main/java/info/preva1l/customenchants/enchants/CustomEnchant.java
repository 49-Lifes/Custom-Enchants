package info.preva1l.customenchants.enchants;

import info.preva1l.customenchants.EnchantManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class CustomEnchant {
    private final EnchantInfo enchantInfo;

    protected CustomEnchant() {
        this.enchantInfo = this.getClass().getAnnotation(EnchantInfo.class);
        if (enchantInfo == null) throw new RuntimeException("Enchant %s constructor must be annotated with @EnchantInfo".formatted(this.getClass().getSimpleName()));
    }

    public abstract void trigger(Player player, ItemStack enchantedItem, Event callingEvent);

    public String getId() {
        return enchantInfo.id();
    }

    public String getName() {
        return enchantInfo.name();
    }

    public List<EnchantTarget> getTargets() {
        return Arrays.stream(enchantInfo.appliesTo()).toList();
    }

    public int getMaxLevel() {
        return enchantInfo.maxLevel();
    }

    public Rarity getRarity() {
        return enchantInfo.rarity();
    }

    public String targetString() {
        List<EnchantTarget> targets = getTargets();
        if (targets.size() == 1) {
            return targets.get(0).getFriendlyName();
        }
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (EnchantTarget target : getTargets()) {
            stringBuilder.append(target.getFriendlyName());
            if (index == targets.size()) {
                stringBuilder.append(" or ");
            } else {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
