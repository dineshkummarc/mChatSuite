package in.mDev.MiracleM4n.mChatSuite.commands;

import in.mDev.MiracleM4n.mChatSuite.mChatSuite;
import in.mDev.MiracleM4n.mChatSuite.util.Messanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MChatShoutCommand implements CommandExecutor {
    mChatSuite plugin;

    public MChatShoutCommand(mChatSuite plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (cmd.equalsIgnoreCase("mchatshout")) {
            String message = "";

            for (String arg : args)
                message += " " + arg;

            if (!(sender instanceof Player)) {
                Messanger.log(Messanger.format("Console's can't shout."));
                return true;
            }

            Player player = (Player) sender;

            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "mchat.shout")) {
                Messanger.sendMessage(player, plugin.getLocale().getOption("noPermissions") + " " + cmd + ".");
                return true;
            }

            plugin.isShouting.put(sender.getName(), true);

            plugin.getServer().broadcastMessage(plugin.getAPI().ParseChatMessage(player.getName(), player.getWorld().getName(), message));

            plugin.isShouting.put(sender.getName(), false);

            return true;
        }

        return false;
    }
}
