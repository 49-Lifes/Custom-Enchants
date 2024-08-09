package info.preva1l.customenchants;

import info.preva1l.customenchants.commands.CustomEnchantsCommand;
import info.preva1l.customenchants.enchants.impl.ImpactDrillEnchant;
import info.preva1l.customenchants.utils.commands.CommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class CustomEnchants extends JavaPlugin {
    @Getter private static CustomEnchants instance;

    @Override
    public void onEnable() {
        instance = this;

        Stream.of(
                new CustomEnchantsCommand(this)
        ).forEach(CommandManager.getInstance()::registerCommand);

        Stream.of(
                new ImpactDrillEnchant()
        ).forEach(EnchantManager.getInstance()::registerEnchant);
    }
}
