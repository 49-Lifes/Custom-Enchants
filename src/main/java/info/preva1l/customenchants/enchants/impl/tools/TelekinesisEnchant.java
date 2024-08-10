package info.preva1l.customenchants.enchants.impl.tools;

import info.preva1l.customenchants.EnchantManager;
import info.preva1l.customenchants.enchants.CustomEnchant;
import info.preva1l.customenchants.enchants.EnchantInfo;
import info.preva1l.customenchants.enchants.EnchantTarget;
import info.preva1l.customenchants.enchants.Rarity;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

@EnchantInfo(
        id = "telekinesis",
        name = "Telekinesis",
        rarity = Rarity.EPIC,
        appliesTo = {EnchantTarget.PICKAXE, EnchantTarget.AXE, EnchantTarget.HOE},
        maxLevel = 5
)
public class TelekinesisEnchant extends CustomEnchant {
    private static final int LEVEL_ONE_CHANCE = 10;
    private static final int LEVEL_TWO_CHANCE = 25;
    private static final int LEVEL_THREE_CHANCE = 50;
    private static final int LEVEL_FOUR_CHANCE = 75;
    private static final int LEVEL_FIVE_CHANCE = 100;

    private final Random random = new Random(42069);

    @Override
    public void trigger(Player player, ItemStack enchantedItem, Event callingEvent) {
        if (!(callingEvent instanceof BlockBreakEvent event)) return;
        int level = EnchantManager.getInstance().getLevelFromItemStack(this, enchantedItem);

        Block block = event.getBlock();

        double percentage = switch (level) {
            case 1:
                yield LEVEL_ONE_CHANCE;
            case 2:
                yield LEVEL_TWO_CHANCE;
            case 3:
                yield LEVEL_THREE_CHANCE;
            case 4:
                yield LEVEL_FOUR_CHANCE;
            case 5:
                yield LEVEL_FIVE_CHANCE;
            default:
                throw new IllegalStateException("Telekinesis Max Level is %s".formatted(getMaxLevel()));
        };

        if (!doProc(percentage)) return;

        event.setDropItems(false);
        List<ItemStack> drops = event.getBlock().getDrops(enchantedItem, player).stream().toList();

        PlayerInventory inventory = player.getInventory();
        Map<Integer, ItemStack> leftOvers = inventory.addItem(drops.toArray(new ItemStack[]{}));
        if (leftOvers.isEmpty()) {
            return;
        }
        for (ItemStack itemStack : leftOvers.values()) {
            block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
        }
    }

    private boolean doProc(double percentage) {
        double randomDouble = random.nextDouble();
        return randomDouble < (percentage / 100);
    }
}
