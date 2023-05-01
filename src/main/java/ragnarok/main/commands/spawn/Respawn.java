package ragnarok.main.commands.spawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import ragnarok.main.Main;

import java.io.File;

public class Respawn implements Listener {
    private Main plugin;

    public Respawn(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }

    /**
     * When player click on respawn button
     *
     * @param e Event
     */

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        File file = new File("plugins//Ragnarok//spawn.yml");
        if (!file.exists()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("spawnfilenull")));
            return;
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
        e.setRespawnLocation(pLoc);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("teleportspawn")));

    }
}