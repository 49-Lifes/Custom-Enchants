package info.preva1l.customenchants.menus;

import info.preva1l.customenchants.EnchantItemsFactory;
import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import info.preva1l.customenchants.utils.Text;
import info.preva1l.customenchants.utils.guis.ItemBuilder;
import info.preva1l.customenchants.utils.guis.PaginatedFastInv;
import info.preva1l.customenchants.utils.guis.PaginatedItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantsAdminMenu extends PaginatedFastInv {
    private static final ItemStack BORDER = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
            .name(Text.colorize(""))
            .lore(Text.colorize("&8dont.vote-preva1l.today")).build();

    private final List<NotSoCustomEnchant> enchants;

    public EnchantsAdminMenu(Player player) {
        super(45, Text.colorize("   "), player);

        this.enchants = EnchantManager.getInstance().getEnchants().values().stream().toList();

        setItems(getBorders(), BORDER);

        fillPaginationItems();
        populatePage();
        addPaginationControls();
    }

    @Override
    protected void fillPaginationItems() {
        for (NotSoCustomEnchant enchant : enchants) {
            for (int level = 1; level <= enchant.getMaxLevel(); level++) {
                ItemStack itemStack = EnchantItemsFactory.createEnchantApplier(enchant, level);
                addPaginationItem(new PaginatedItem(itemStack, (e) -> {
                    int slot = player.getInventory().firstEmpty();
                    if (slot == -1) {
                        player.sendMessage(Text.colorize("&6&l49Lifes &cYour inventory is full!"));
                        return;
                    }
                    player.getInventory().setItem(slot, itemStack);
                }));
            }
        }
    }

    @Override
    protected void addPaginationControls() {
        setItem(39, BORDER);
        setItem(41, BORDER);
        if (page > 0) {
            setItem(39, new ItemBuilder(Material.ARROW)
                    .name(Text.colorize("&a&lPrevious Page"))
                    .lore(Text.colorize("&7Click to go to the previous page!")).build(), e -> previousPage());
        }

        if (enchants != null && enchants.size() >= index + 1) {
            setItem(41, new ItemBuilder(Material.ARROW)
                    .name(Text.colorize("&a&lNext Page"))
                    .lore(Text.colorize("&7Click to go to the next page!")).build(), e -> nextPage());
        }
    }
}
