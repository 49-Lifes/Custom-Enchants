package info.preva1l.customenchants.enchants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public enum Rarity {
    COMMON("&7", "COMMON", Material.LIGHT_GRAY_DYE, 0),
    UNCOMMON("&a", "UNCOMMON", Material.LIME_DYE, 1),
    RARE("&9", "RARE", Material.CYAN_DYE, 2),
    EPIC("&5", "EPIC", Material.PURPLE_DYE, 3),
    LEGENDARY("&6", "LEGENDARY", Material.ORANGE_DYE, 4),
    LIMITED("&d", "LIMITED EDITION", Material.PINK_DYE, 5),
    ;

    private final String colour;
    private final String friendlyName;
    private final Material material;
    private final int order;
}
