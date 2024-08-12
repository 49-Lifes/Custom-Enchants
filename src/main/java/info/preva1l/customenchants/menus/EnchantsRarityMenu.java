package info.preva1l.customenchants.menus;

import info.preva1l.customenchants.enchants.Rarity;
import info.preva1l.customenchants.utils.Text;
import info.preva1l.customenchants.utils.guis.FastInv;
import info.preva1l.customenchants.utils.guis.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantsRarityMenu extends FastInv {
    private static final ItemStack BORDER = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
            .name(Text.colorize(""))
            .lore(Text.colorize("&8dont.vote-preva1l.today")).build();

    public EnchantsRarityMenu() {
        super(27, Text.colorize("&8Runes"));

        setItems(getBorders(), BORDER);

        setItem(10, new ItemBuilder(Rarity.COMMON.getMaterial())
                .name(Text.colorize(Rarity.COMMON.getColour() + "&l" + Rarity.COMMON.getFriendlyName()))
                .lore(Text.colorize("&7Click to view enchants.")).build(),
                e -> new EnchantsListMenu((Player) e.getWhoClicked(), Rarity.COMMON).open((Player) e.getWhoClicked()));
        setItem(11, new ItemBuilder(Rarity.UNCOMMON.getMaterial())
                .name(Text.colorize(Rarity.UNCOMMON.getColour() + "&l" + Rarity.UNCOMMON.getFriendlyName()))
                .lore(Text.colorize("&7Click to view enchants.")).build(),
                e -> new EnchantsListMenu((Player) e.getWhoClicked(), Rarity.UNCOMMON).open((Player) e.getWhoClicked()));
        setItem(12, new ItemBuilder(Rarity.RARE.getMaterial())
                .name(Text.colorize(Rarity.RARE.getColour() + "&l" + Rarity.RARE.getFriendlyName()))
                .lore(Text.colorize("&7Click to view enchants.")).build(),
                e -> new EnchantsListMenu((Player) e.getWhoClicked(), Rarity.RARE).open((Player) e.getWhoClicked()));
        setItem(13, new ItemBuilder(Rarity.EPIC.getMaterial())
                .name(Text.colorize(Rarity.EPIC.getColour() + "&l" + Rarity.EPIC.getFriendlyName()))
                .lore(Text.colorize("&7Click to view enchants.")).build(),
                e -> new EnchantsListMenu((Player) e.getWhoClicked(), Rarity.EPIC).open((Player) e.getWhoClicked()));
        setItem(14, new ItemBuilder(Rarity.LEGENDARY.getMaterial())
                .name(Text.colorize(Rarity.LEGENDARY.getColour() + "&l" + Rarity.LEGENDARY.getFriendlyName()))
                .lore(Text.colorize("&7Click to view enchants.")).build(),
                e -> new EnchantsListMenu((Player) e.getWhoClicked(), Rarity.LEGENDARY).open((Player) e.getWhoClicked()));
        setItem(15, BORDER);
        setItem(16, new ItemBuilder(Rarity.LIMITED.getMaterial())
                .name(Text.colorize(Rarity.LIMITED.getColour() + "&l" + Rarity.LIMITED.getFriendlyName()))
                .lore(Text.colorize("&7Click to view enchants.")).build(),
                e -> new EnchantsListMenu((Player) e.getWhoClicked(), Rarity.LIMITED).open((Player) e.getWhoClicked()));
    }
}
