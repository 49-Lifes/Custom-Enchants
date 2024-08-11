package info.preva1l.customenchants.menus;

import info.preva1l.customenchants.EnchantItemsFactory;
import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.NotSoCustomEnchant;
import info.preva1l.customenchants.enchants.Rarity;
import info.preva1l.customenchants.utils.Text;
import info.preva1l.customenchants.utils.guis.ItemBuilder;
import info.preva1l.customenchants.utils.guis.PaginatedFastInv;
import info.preva1l.customenchants.utils.guis.PaginatedItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantsListMenu extends PaginatedFastInv {
    private static final ItemStack BORDER = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
            .name(Text.colorize(""))
            .lore(Text.colorize("&8dont.vote-preva1l.today")).build();

    private final List<NotSoCustomEnchant> enchants;

    public EnchantsListMenu(Player player, Rarity rarity) {
        super(45, Text.colorize(rarity.getColour() + "&l" + rarity.getFriendlyName() + " RUNES"), player);

        this.enchants = new ArrayList<>(EnchantManager.getInstance().getEnchants().values().stream().toList());
        enchants.removeIf(enchant -> enchant.getRarity() != rarity);

        setItems(getBorders(), BORDER);

        fillPaginationItems();
        populatePage();
        addPaginationControls();
    }

    @Override
    protected void fillPaginationItems() {
        for (NotSoCustomEnchant enchant : enchants) {
            ItemStack itemStack = EnchantItemsFactory.createEnchantApplierPreview(enchant);
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
