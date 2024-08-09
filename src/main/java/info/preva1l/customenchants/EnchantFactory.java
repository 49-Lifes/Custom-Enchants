package info.preva1l.customenchants;

import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.utils.Text;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@UtilityClass
public class EnchantFactory {
    @SuppressWarnings("deprecation")
    public ItemStack createEnchantApplier(CustomEnchant enchant, int level) {
        String displayName = Text.colorize(enchant.getRarity().getColour() + enchant.getName());
        List<String> lore = List.of(
                "&fLevel: " + level,
                "",
                "&7Applies To: " + enchant.targetString(),
                "",
                enchant.getRarity().getFriendlyName() + " Rune"
        );

        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
