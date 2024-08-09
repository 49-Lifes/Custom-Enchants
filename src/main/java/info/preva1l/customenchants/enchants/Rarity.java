package info.preva1l.customenchants.enchants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public enum Rarity {
    COMMON("&7", "Common", Material.LIGHT_GRAY_DYE),
    UNCOMMON("&a", "Uncommon", Material.LIME_DYE),
    RARE("&9", "Rare", Material.CYAN_DYE),
    EPIC("&5", "Epic", Material.PURPLE_DYE),
    LEGENDARY("&6", "Legendary", Material.ORANGE_DYE),
    LIMITED("&d", "Limited Edition", Material.PINK_DYE),
    ;

    private final String colour;
    private final String friendlyName;
    private final Material material;
}
