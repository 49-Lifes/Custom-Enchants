package info.preva1l.customenchants.commands.subcommands;

import info.preva1l.customenchants.CustomEnchants;
import info.preva1l.customenchants.menus.EnchantsAdminMenu;
import info.preva1l.customenchants.utils.commands.BasicSubCommand;
import info.preva1l.customenchants.utils.commands.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminSubCommand extends BasicSubCommand {
    @Command(name = "admin", permission = "custom-enchants.admin")
    public AdminSubCommand(CustomEnchants plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        new EnchantsAdminMenu(player).open(player);
    }
}
