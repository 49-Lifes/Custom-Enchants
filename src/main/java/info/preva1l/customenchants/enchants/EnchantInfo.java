package info.preva1l.customenchants.enchants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnchantInfo {
    String id();

    String name();

    Rarity rarity();

    EnchantTarget[] appliesTo();

    int maxLevel();
}
