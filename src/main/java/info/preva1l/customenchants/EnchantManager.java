package info.preva1l.customenchants;

import info.preva1l.customenchants.enchants.EnchantReference;
import info.preva1l.customenchants.enchants.EnchantTarget;
import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import lombok.Getter;
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

    /**
     * Stores a map of enchant Name to ID
     */
    @Getter private final Map<String, String> enchantIdsCache = new ConcurrentHashMap<>();
    @Getter private final Map<String, NotSoCustomEnchant> enchants = new ConcurrentHashMap<>();

    private EnchantManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.enchantApplierKey = new NamespacedKey(plugin, "custom_enchant");
    }

    public boolean hasEnchant(ItemStack itemStack, NotSoCustomEnchant customEnchant) {
        return getEnchants(itemStack).contains(customEnchant);
    }

    public boolean canApplyToItem(NotSoCustomEnchant customEnchant, ItemStack itemStack) {
        for (EnchantTarget target : customEnchant.getTargets()) {
            if (target.shouldApply(itemStack.getType())) return true;
        }
        return false;
    }

    public int getLevelFromItemStack(NotSoCustomEnchant customEnchant, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        NamespacedKey key = getNamespacedKey(customEnchant);
        if (!pdc.has(key, PersistentDataType.STRING)) return 0;
        return getLevelFromData(pdc.get(key, PersistentDataType.STRING));
    }

    public boolean isMaxLevel(NotSoCustomEnchant customEnchant, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        NamespacedKey key = getNamespacedKey(customEnchant);
        if (!pdc.has(key, PersistentDataType.STRING)) return false;
        return getLevelFromData(pdc.get(key, PersistentDataType.STRING)) == customEnchant.getMaxLevel();
    }

    public ItemStack applyEnchant(NotSoCustomEnchant customEnchant, int level, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        NamespacedKey key = getNamespacedKey(customEnchant);
        if (pdc.has(key, PersistentDataType.STRING)) {
            pdc.remove(key);
        }

        itemMeta.getPersistentDataContainer().set(getNamespacedKey(customEnchant), PersistentDataType.STRING, getDataString(customEnchant, level));
        itemStack.setItemMeta(itemMeta);
        return LoreHandler.getInstance().addCustomEnchant(itemStack, customEnchant, level);
    }

    public void registerEnchant(NotSoCustomEnchant enchant) {
        enchants.put(enchant.getId(), enchant);
    }

    public List<EnchantReference> getEnchantRefs(ItemStack itemStack) {
        List<EnchantReference> customEnchants = new ArrayList<>();
        if (itemStack.getItemMeta() == null) return customEnchants;
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        for (NotSoCustomEnchant enchant : enchants.values()) {
            NamespacedKey key = getNamespacedKey(enchant);
            if (pdc.has(key)) customEnchants.add(new EnchantReference(enchant, getLevelFromData(pdc.get(key, PersistentDataType.STRING))));
        }
        return customEnchants;
    }

    public List<NotSoCustomEnchant> getEnchants(ItemStack itemStack) {
        List<NotSoCustomEnchant> customEnchants = new ArrayList<>();
        if (itemStack.getItemMeta() == null) return customEnchants;
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        for (NotSoCustomEnchant enchant : enchants.values()) {
            if (pdc.has(getNamespacedKey(enchant))) customEnchants.add(enchant);
        }
        return customEnchants;
    }

    public NotSoCustomEnchant getEnchantFromApplier(ItemStack itemStack) {
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
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        return pdc.has(enchantApplierKey, PersistentDataType.STRING);
    }

    public ItemStack addPdcToApplier(NotSoCustomEnchant enchant, int level, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(enchantApplierKey, PersistentDataType.STRING, getDataString(enchant, level));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private NamespacedKey getNamespacedKey(NotSoCustomEnchant enchant) {
        return new NamespacedKey(plugin, enchant.getId());
    }

    private String getDataString(NotSoCustomEnchant enchant, int level) {
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
