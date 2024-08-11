package info.preva1l.customenchants;

import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import info.preva1l.customenchants.utils.Text;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EnchantItemsFactory {
    @SuppressWarnings("deprecation")
    public ItemStack createEnchantApplier(NotSoCustomEnchant enchant, int level) {
        String displayName = Text.colorize(enchant.getRarity().getColour() + enchant.getName());
        List<String> lore = new ArrayList<>(enchant.getDescription());

        lore.addAll(List.of(
                "",
                "&fLevel: ",
                "",
                "&7Applies To: " + enchant.targetString(),
                "",
                enchant.getRarity().getColour() + "&l" + enchant.getRarity().getFriendlyName() + " RUNE"
        ));

        ItemStack itemStack = new ItemStack(enchant.getRarity().getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Text.colorizeList(lore));
        itemMeta.setCustomModelData(12);
        itemStack.setItemMeta(itemMeta);
        return EnchantManager.getInstance().addPdcToApplier(enchant, level, itemStack);
    }

    public ItemStack createEnchantApplierPreview(NotSoCustomEnchant enchant) {
        String displayName = Text.colorize(enchant.getRarity().getColour() + enchant.getName());
        List<String> lore = new ArrayList<>(enchant.getDescription());

        lore.addAll(List.of(
                "",
                "&7Applies To: " + enchant.targetString(),
                "",
                enchant.getRarity().getColour() + "&l" + enchant.getRarity().getFriendlyName() + " RUNE"
        ));

        ItemStack itemStack = new ItemStack(enchant.getRarity().getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Text.colorizeList(lore));
        itemMeta.setCustomModelData(12);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
