package info.preva1l.customenchants.enchants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public enum Rarity {
    COMMON("&7", "COMMON", Material.LIGHT_GRAY_DYE),
    UNCOMMON("&a", "UNCOMMON", Material.LIME_DYE),
    RARE("&9", "RARE", Material.CYAN_DYE),
    EPIC("&5", "EPIC", Material.PURPLE_DYE),
    LEGENDARY("&6", "LEGENDARY", Material.ORANGE_DYE),
    LIMITED("&d", "LIMITED EDITION", Material.PINK_DYE),
    ;

    private final String colour;
    private final String friendlyName;
    private final Material material;
}
