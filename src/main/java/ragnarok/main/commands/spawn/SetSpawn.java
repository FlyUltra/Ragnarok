package ragnarok.main.commands.spawn;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ragnarok.main.Main;

public class SetSpawn implements CommandExecutor {

    private Main plugin;

    public SetSpawn(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("setspawn").setExecutor((CommandExecutor) this);
    }



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("setspawn"))
                if (p.hasPermission("Ragnarok.spawnset")) {
                    if (args.length > 0) {
                        return true;
                    }
                    File folder = new File("plugins//Ragnarok");
                    File file = new File("plugins//Ragnarok//spawn.yml");
                    if (!folder.exists())
                        folder.mkdir();
                    if (!file.exists())
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    Location pLoc = p.getLocation();
                    double x = pLoc.getX();
                    double y = pLoc.getY();
                    double z = pLoc.getZ();
                    double yaw = pLoc.getYaw();
                    double pitch = pLoc.getPitch();
                    String world = pLoc.getWorld().getName();
                    cfg.set("X", Double.valueOf(x));
                    cfg.set("Y", Double.valueOf(y));
                    cfg.set("Z", Double.valueOf(z));
                    cfg.set("Yaw", Double.valueOf(yaw));
                    cfg.set("Pitch", Double.valueOf(pitch));
                    cfg.set("World", world);
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("success_spawn_create")));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
                    return true;
                }
        } else {
            return true;
        }
        return false;
    }
}