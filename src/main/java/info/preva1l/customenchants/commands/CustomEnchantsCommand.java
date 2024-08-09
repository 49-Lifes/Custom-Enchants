package info.preva1l.customenchants.commands;

import info.preva1l.customenchants.CustomEnchants;
import info.preva1l.customenchants.utils.commands.BasicCommand;
import info.preva1l.customenchants.utils.commands.Command;
import org.bukkit.command.CommandSender;

public class CustomEnchantsCommand extends BasicCommand {
    @Command(name = "custom-enchants", aliases = {"ce", "runes", "rune"}, permission = "custom-enchants.use")
    public CustomEnchantsCommand(CustomEnchants plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("test");
    }
}
