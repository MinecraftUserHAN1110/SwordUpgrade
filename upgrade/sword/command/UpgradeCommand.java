package upgrade.sword.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import upgrade.sword.event.GUI;
import upgrade.sword.event.UpgradeBase;

public class UpgradeCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param arg   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
        if (sender instanceof Player) {
            new UpgradeBase((Player) sender);
            return true;
        }
        return false;
    }
}
