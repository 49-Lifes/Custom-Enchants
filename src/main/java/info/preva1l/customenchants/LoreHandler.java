package info.preva1l.customenchants;

import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import info.preva1l.customenchants.utils.Text;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoreHandler {
    private static LoreHandler instance;

    private static final String CRATE_ITEM_MATCH = " CRATE ITEM";
    private static final LinkedHashMap<String, Integer> romanNumerals = new LinkedHashMap<>();

    static {
       romanNumerals.put("M", 1000);
       romanNumerals.put("CM", 900);
       romanNumerals.put("D", 500);
       romanNumerals.put("CD", 400);
       romanNumerals.put("C", 100);
       romanNumerals.put("XC", 90);
       romanNumerals.put("L", 50);
       romanNumerals.put("XL", 40);
       romanNumerals.put("X", 10);
       romanNumerals.put("IX", 9);
       romanNumerals.put("V", 5);
       romanNumerals.put("IV", 4);
       romanNumerals.put("I", 1);
    }

    public ItemStack addCustomEnchant(ItemStack item, NotSoCustomEnchant enchant, int level) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.add(Text.colorize(enchant.getRarity().getColour() + enchant.getName() + " " + getRomanNumeral(level)));
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    private String getRomanNumeral(int level) {
        StringBuilder res = new StringBuilder();

        Map.Entry<String, Integer> entry;
        for(Iterator<Map.Entry<String, Integer>> var4 = romanNumerals.entrySet().iterator(); var4.hasNext(); level %= entry.getValue()) {
            entry = var4.next();
            int matches = level / entry.getValue();
            res.append(repeat(entry.getKey(), matches));
        }

        return res.toString();
    }

    private String repeat(String s, int n) {
        if (s == null) {
            return null;
        } else {
            return s.repeat(Math.max(0, n));
        }
    }

    public static LoreHandler getInstance() {
        if (instance == null) {
            instance = new LoreHandler();
        }
        return instance;
    }
}
