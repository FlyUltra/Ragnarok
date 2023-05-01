package ragnarok.main.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import ragnarok.main.Main;

import java.util.ArrayList;

public class  Chat implements CommandExecutor, Listener {

    private Main plugin;


    public Chat(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);
        plugin.getCommand("adminchat").setExecutor((CommandExecutor) this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("Ragnarok.staffchat")) {
        }
        if (args.length > 0) {
            String message = "";
            for (int i = 0; i < args.length; i++) {
                String a = args[i] + " ";
                message = message + a;
            }
            String name = p.getName();
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("Ragnarok.see"))
                    players.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("staffchat").replace("%PLAYER%", name)).replace("%MESSAGE%", message)));
            }
        }
        if (args.length == 0) {
            if (!(p.hasPermission("Ragnarok.staffchat"))) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("noperm")));
                return true;
            } else {
                if (Main.ac.contains(p)) {
                    Main.ac.remove(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("sc-toggle-off")));
                    return true;
                } else {
                    Main.ac.add(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("sc-toggle-on")));
                    return true;

                }
            }
        }

        return true;
    }

    @EventHandler
    public void onStaffChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        String name = e.getPlayer().getName();
        if (Main.ac.contains(p)) {
            e.setCancelled(true);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("StaffChat.see")) {
                    players.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("staffchat").replace("%PLAYER%", name)).replace("%MESSAGE%", message));
                    e.setCancelled(true);
                }
            }
        } else {
            e.setCancelled(true);
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat").replace("%PLAYER%", name)).replace("%MESSAGE%", message));
            }

        }
    }
}





