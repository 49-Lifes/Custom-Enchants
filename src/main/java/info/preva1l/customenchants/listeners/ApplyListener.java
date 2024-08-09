package info.preva1l.customenchants.listeners;

import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class ApplyListener implements Listener {
    private final EnchantManager enchantManager;

    @EventHandler
    public void onInventoryItemClick(InventoryClickEvent e) {
        if (e.getClick() != ClickType.RIGHT) return;
        ItemStack itemInCursor = e.getCursor();
        ItemStack clickedItem = e.getCurrentItem();
        Inventory inventory = e.getClickedInventory();
        if (itemInCursor == null || clickedItem == null || inventory == null) return;
        if (itemInCursor.getType() == Material.AIR || clickedItem.getType() == Material.AIR) return;
        if (!enchantManager.isEnchantApplier(itemInCursor)) return;
        NotSoCustomEnchant enchant = enchantManager.getEnchantFromApplier(itemInCursor);

        if (!enchantManager.canApplyToItem(enchant, clickedItem)) return;

        clickedItem = enchantManager.applyEnchant(enchant, enchantManager.getLevelFromApplier(itemInCursor), clickedItem);
        inventory.setItem(e.getSlot(), clickedItem);
        e.getView().setCursor(null);
    }
}
