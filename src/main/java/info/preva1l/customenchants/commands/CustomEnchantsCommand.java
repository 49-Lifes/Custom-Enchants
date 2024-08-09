package info.preva1l.customenchants.commands;

import info.preva1l.customenchants.CustomEnchants;
import info.preva1l.customenchants.commands.subcommands.AdminSubCommand;
import info.preva1l.customenchants.utils.Text;
import info.preva1l.customenchants.utils.commands.BasicCommand;
import info.preva1l.customenchants.utils.commands.Command;
import org.bukkit.command.CommandSender;

public class CustomEnchantsCommand extends BasicCommand {
    @Command(name = "custom-enchants", aliases = {"ce", "runes", "rune"}, permission = "custom-enchants.use")
    public CustomEnchantsCommand(CustomEnchants plugin) {
        super(plugin);
        getSubCommands().add(new AdminSubCommand(plugin));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            if (subCommandExecutor(sender, args)) return;
            sender.sendMessage(Text.colorize("&6&l49Lifes &cCommand does not exist?"));
            return;
        }

        sender.sendMessage("coming soon...");
    }
}
