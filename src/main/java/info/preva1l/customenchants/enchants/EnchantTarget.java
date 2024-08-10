package info.preva1l.customenchants.enchants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@AllArgsConstructor
@Getter
public enum EnchantTarget {
    PICKAXE("Pickaxes") {
        @Override
        public boolean shouldApply(Material material) {
            return material == Material.WOODEN_PICKAXE
                    || material == Material.STONE_PICKAXE
                    || material == Material.IRON_PICKAXE
                    || material == Material.GOLDEN_PICKAXE
                    || material == Material.DIAMOND_PICKAXE
                    || material == Material.NETHERITE_PICKAXE;
        }
    },
    SWORD("Swords") {
        @Override
        public boolean shouldApply(Material material) {
            return material == Material.WOODEN_SWORD
                    || material == Material.STONE_SWORD
                    || material == Material.IRON_SWORD
                    || material == Material.GOLDEN_SWORD
                    || material == Material.DIAMOND_SWORD
                    || material == Material.NETHERITE_SWORD;
        }
    },
    HELMET("Helmets") {
        @Override
        public boolean shouldApply(Material material) {
            return material == Material.LEATHER_HELMET
                    || material == Material.IRON_HELMET
                    || material == Material.GOLDEN_HELMET
                    || material == Material.DIAMOND_HELMET
                    || material == Material.NETHERITE_HELMET;
        }
    },
    LEGGINGS("Leggings") {
        @Override
        public boolean shouldApply(Material material) {
            return material == Material.LEATHER_LEGGINGS
                    || material == Material.IRON_LEGGINGS
                    || material == Material.GOLDEN_LEGGINGS
                    || material == Material.DIAMOND_LEGGINGS
                    || material == Material.NETHERITE_LEGGINGS;
        }
    },
    BOOTS("Boots") {
        @Override
        public boolean shouldApply(Material material) {
            return material == Material.LEATHER_BOOTS
                    || material == Material.IRON_BOOTS
                    || material == Material.GOLDEN_BOOTS
                    || material == Material.DIAMOND_BOOTS
                    || material == Material.NETHERITE_BOOTS;
        }
    };

    private final String friendlyName;

    public abstract boolean shouldApply(Material material);
}
