package info.preva1l.customenchants.enchants;

import java.util.Arrays;
import java.util.List;

public abstract class CustomEnchant implements NotSoCustomEnchant {
    private final EnchantInfo enchantInfo;

    protected CustomEnchant() {
        this.enchantInfo = this.getClass().getAnnotation(EnchantInfo.class);
        if (enchantInfo == null) throw new RuntimeException("Enchant %s constructor must be annotated with @EnchantInfo".formatted(this.getClass().getSimpleName()));
    }

    @Override
    public String getId() {
        return enchantInfo.id();
    }

    @Override
    public String getName() {
        return enchantInfo.name();
    }

    @Override
    public List<EnchantTarget> getTargets() {
        return Arrays.stream(enchantInfo.appliesTo()).toList();
    }

    @Override
    public int getMaxLevel() {
        return enchantInfo.maxLevel();
    }

    @Override
    public Rarity getRarity() {
        return enchantInfo.rarity();
    }

    @Override
    public String targetString() {
        List<EnchantTarget> targets = getTargets();
        if (targets.size() == 1) {
            return targets.get(0).getFriendlyName();
        }
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (EnchantTarget target : getTargets()) {
            stringBuilder.append(target.getFriendlyName());
            if (index == targets.size()) {
                stringBuilder.append(" or ");
            } else {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
