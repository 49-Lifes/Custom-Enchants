package info.preva1l.customenchants;

import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import info.preva1l.customenchants.utils.Text;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@UtilityClass
public class EnchantItemsFactory {
    @SuppressWarnings("deprecation")
    public ItemStack createEnchantApplier(NotSoCustomEnchant enchant, int level) {
        String displayName = Text.colorize(enchant.getRarity().getColour() + enchant.getName());
        List<String> lore = List.of(
                "&fLevel: " + level,
                "",
                "&7Applies To: " + enchant.targetString(),
                "",
                enchant.getRarity().getColour() + "&l" + enchant.getRarity().getFriendlyName() + " RUNE"
        );

        ItemStack itemStack = new ItemStack(enchant.getRarity().getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Text.colorizeList(lore));
        itemStack.setItemMeta(itemMeta);
        return EnchantManager.getInstance().addPdcToApplier(enchant, level, itemStack);
    }
}
