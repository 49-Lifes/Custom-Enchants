package info.preva1l.customenchants;

import info.preva1l.customenchants.commands.CustomEnchantsCommand;
import info.preva1l.customenchants.enchants.impl.boots.HermesWingsEnchant;
import info.preva1l.customenchants.enchants.impl.helmet.CatEyesEnchant;
import info.preva1l.customenchants.enchants.impl.leggings.PartyPantsEnchant;
import info.preva1l.customenchants.enchants.impl.pickaxe.ImpactDrillEnchant;
import info.preva1l.customenchants.events.PassiveEnchantTriggerEvent;
import info.preva1l.customenchants.events.PassiveType;
import info.preva1l.customenchants.listeners.ApplyListener;
import info.preva1l.customenchants.listeners.PassiveEnchantsListener;
import info.preva1l.customenchants.listeners.ToolListeners;
import info.preva1l.customenchants.utils.TaskManager;
import info.preva1l.customenchants.utils.commands.CommandManager;
import info.preva1l.customenchants.utils.guis.FastInvManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
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
                new ImpactDrillEnchant(),
                new CatEyesEnchant(),
                new HermesWingsEnchant(),
                new PartyPantsEnchant()
        ).forEach(EnchantManager.getInstance()::registerEnchant);

        Stream.of(
                new ApplyListener(EnchantManager.getInstance()),
                new ToolListeners(EnchantManager.getInstance()),
                new PassiveEnchantsListener(EnchantManager.getInstance())
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));

        TaskManager.Sync.runTask(() -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                PlayerInventory inventory = player.getInventory();
                List<PassiveEnchantTriggerEvent> events = new ArrayList<>();
                if (inventory.getHelmet() != null && inventory.getHelmet().getType() != Material.AIR) {
                    PassiveEnchantTriggerEvent event = new PassiveEnchantTriggerEvent(player, PassiveType.HELMET);
                    events.add(event);
                }
                if (inventory.getChestplate() != null && inventory.getChestplate().getType() != Material.AIR) {
                    PassiveEnchantTriggerEvent event = new PassiveEnchantTriggerEvent(player, PassiveType.CHESTPLATE);
                    events.add(event);
                }
                if (inventory.getLeggings() != null && inventory.getLeggings().getType() != Material.AIR) {
                    PassiveEnchantTriggerEvent event = new PassiveEnchantTriggerEvent(player, PassiveType.LEGGINGS);
                    events.add(event);
                }
                if (inventory.getBoots() != null && inventory.getBoots().getType() != Material.AIR) {
                    PassiveEnchantTriggerEvent event = new PassiveEnchantTriggerEvent(player, PassiveType.BOOTS);
                    events.add(event);
                }
                if (inventory.getItemInMainHand().getType() != Material.AIR) {
                    PassiveEnchantTriggerEvent event = new PassiveEnchantTriggerEvent(player, PassiveType.MAIN_HAND);
                    events.add(event);
                }
                if (inventory.getItemInOffHand().getType() != Material.AIR) {
                    PassiveEnchantTriggerEvent event = new PassiveEnchantTriggerEvent(player, PassiveType.OFF_HAND);
                    events.add(event);
                }

                events.forEach(Bukkit.getPluginManager()::callEvent);
            }
        }, 30L);
    }
}
