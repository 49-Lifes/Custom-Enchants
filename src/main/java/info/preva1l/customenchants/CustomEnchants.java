package info.preva1l.customenchants;

import info.preva1l.customenchants.enchants.impl.ImpactDrillEnchant;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class CustomEnchants extends JavaPlugin {
    @Getter private static CustomEnchants instance;

    @Override
    public void onEnable() {
        instance = this;

        Stream.of(
                new ImpactDrillEnchant()
        ).forEach(EnchantManager.getInstance()::registerEnchant);
    }
}
