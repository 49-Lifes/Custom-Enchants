package info.preva1l.customenchants.commands.subcommands;

import info.preva1l.customenchants.CustomEnchants;
import info.preva1l.customenchants.utils.Text;
import info.preva1l.customenchants.utils.commands.BasicSubCommand;
import info.preva1l.customenchants.utils.commands.Command;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTableLocationCommand extends BasicSubCommand {
    @Command(name = "set-table-location", permission = "custom-enchants.admin")
    public SetTableLocationCommand(CustomEnchants plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location loc = player.getLocation();
        String world = loc.getWorld().getName();
        double locX = loc.getBlockX();
        double locY = loc.getBlockY();
        double locZ = loc.getBlockZ();
        plugin.getConfig().set("rune-table.x", locX);
        plugin.getConfig().set("rune-table.y", locY);
        plugin.getConfig().set("rune-table.z", locZ);
        plugin.getConfig().set("rune-table.world", world);
        plugin.saveConfig();
        player.sendMessage(Text.colorize("&6&l49Lifes &fRune table &aSuccessfully &fUpdated!"));
    }
}
