package ragnarok.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ragnarok.main.Main;

public class Ragnarok implements CommandExecutor {
    private Main plugin;

    public Ragnarok(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("ragnarok").setExecutor((CommandExecutor) this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if (!p.hasPermission("Ragnarok.opmanager")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
            return true;
        }
        try {
            if (args[0].equalsIgnoreCase("chat")) {
                for (String chat_arg : plugin.getConfig().getStringList("chat_arg")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', chat_arg));
                }
            }



        } catch (ArrayIndexOutOfBoundsException exc) {
        }

        return false;

    }
}

