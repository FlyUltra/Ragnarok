package ragnarok.main.commands.spawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ragnarok.main.Main;

import java.io.File;

public class SpawnCommand implements CommandExecutor {
    private Main plugin;

    public SpawnCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("spawn").setExecutor((CommandExecutor) this);
    }




    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (p.hasPermission("Ragnarok.spawn")) {
                File file = new File("plugins//Ragnarok//spawn.yml");
                if (!file.exists()) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("spawnfilenull")));
                    return true;
                }
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                Location pLoc = p.getLocation();
                double x = cfg.getDouble("X");
                double y = cfg.getDouble("Y");
                double z = cfg.getDouble("Z");
                double yaw = cfg.getDouble("Yaw");
                double pitch = cfg.getDouble("Pitch");
                String world = cfg.getString("World");
                World world1 = Bukkit.getWorld(world);
                pLoc.setX(x);
                pLoc.setY(y);
                pLoc.setZ(z);
                pLoc.setYaw((float)yaw);
                pLoc.setPitch((float)pitch);
                pLoc.setWorld(world1);
                p.teleport(pLoc);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("teleportspawn")));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("noperm")));
                return true;
            }
        } else {
            return true;
        }
        return false;
    }
}
