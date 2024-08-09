package info.preva1l.customenchants.enchants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rarity {
    COMMON("&7",  "Common"),
    UNCOMMON("&a","Uncommon"),
    RARE("&9", "Rare"),
    EPIC("&5", "Epic"),
    LEGENDARY("&6", "Legendary"),
    LIMITED("&d", "Limited Edition"),
    ;

    private final String colour;
    private final String friendlyName;
}
