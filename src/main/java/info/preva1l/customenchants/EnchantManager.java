package info.preva1l.customenchants;

import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.enchants.EnchantTarget;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnchantManager {
    private static EnchantManager instance;

    private final JavaPlugin plugin;
    private static final String PDC_FORMAT = "%enchant%:%level%";
    private final NamespacedKey enchantApplierKey;

    private Map<String, CustomEnchant> enchants = new ConcurrentHashMap<>();

    private EnchantManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.enchantApplierKey = new NamespacedKey(plugin, "custom_enchant");
    }

    public boolean canApplyToItem(CustomEnchant customEnchant, ItemStack itemStack) {
        for (EnchantTarget target : customEnchant.getTargets()) {
            if (target.shouldApply(itemStack.getType())) return true;
        }
        return false;
    }

    public boolean isMaxLevel(CustomEnchant customEnchant, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        NamespacedKey key = getNamespacedKey(customEnchant);
        if (!pdc.has(key, PersistentDataType.STRING)) return false;
        return getLevelFromData(pdc.get(key, PersistentDataType.STRING)) == customEnchant.getMaxLevel();
    }

    public ItemStack applyEnchant(CustomEnchant customEnchant, int level, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        NamespacedKey key = getNamespacedKey(customEnchant);
        if (pdc.has(key, PersistentDataType.STRING)) {
            pdc.remove(key);
        }

        itemMeta.getPersistentDataContainer().set(getNamespacedKey(customEnchant), PersistentDataType.STRING, getDataString(customEnchant, level));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void registerEnchant(CustomEnchant enchant) {
        enchants.put(enchant.getId(), enchant);
    }

    public List<CustomEnchant> getEnchants(ItemStack itemStack) {
        List<CustomEnchant> customEnchants = new ArrayList<>();
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        for (CustomEnchant enchant : enchants.values()) {
            if (pdc.has(getNamespacedKey(enchant))) customEnchants.add(enchant);
        }
        return customEnchants;
    }

    public CustomEnchant getEnchantFromApplier(ItemStack itemStack) {
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        if (!pdc.has(enchantApplierKey)) return null;
        return enchants.get(pdc.get(enchantApplierKey, PersistentDataType.STRING).split(":")[0]);
    }

    public int getLevelFromApplier(ItemStack itemStack) {
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        if (!pdc.has(enchantApplierKey)) return 0;
        return Integer.parseInt(pdc.get(enchantApplierKey, PersistentDataType.STRING).split(":")[1]);
    }

    public boolean isEnchantApplier(ItemStack itemStack) {
        if (itemStack.getType() != Material.ENCHANTED_BOOK) return false;
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        return pdc.has(enchantApplierKey, PersistentDataType.STRING);
    }

    private NamespacedKey getNamespacedKey(CustomEnchant enchant) {
        return new NamespacedKey(plugin, enchant.getId());
    }

    private String getDataString(CustomEnchant enchant, int level) {
        return PDC_FORMAT
                .replace("%enchant%", enchant.getId())
                .replace("%level%", String.valueOf(level));
    }

    private int getLevelFromData(String dataString) {
        return Integer.parseInt(dataString.split(":")[1]);
    }

    public static EnchantManager getInstance() {
        if (instance == null) {
            instance = new EnchantManager(CustomEnchants.getInstance());
        }
        return instance;
    }
}
