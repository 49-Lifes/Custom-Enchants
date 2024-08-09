package info.preva1l.customenchants;

import info.preva1l.customenchants.commands.CustomEnchantsCommand;
import info.preva1l.customenchants.enchants.impl.ImpactDrillEnchant;
import info.preva1l.customenchants.listeners.ApplyListener;
import info.preva1l.customenchants.listeners.ToolListeners;
import info.preva1l.customenchants.utils.commands.CommandManager;
import info.preva1l.customenchants.utils.guis.FastInvManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class CustomEnchants extends JavaPlugin {
    @Getter private static CustomEnchants instance;

    @Override
    public void onEnable() {
        instance = this;
        FastInvManager.register(this);

        Stream.of(
                new CustomEnchantsCommand(this)
        ).forEach(CommandManager.getInstance()::registerCommand);

        Stream.of(
                new ImpactDrillEnchant()
        ).forEach(EnchantManager.getInstance()::registerEnchant);

        Stream.of(
                new ApplyListener(EnchantManager.getInstance()),
                new ToolListeners(EnchantManager.getInstance())
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }
}
