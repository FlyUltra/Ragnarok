package ragnarok.main.listeners.thor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import ragnarok.main.Main;

public class ThorArmorEvents implements Listener {
    private Main plugin;

    public ThorArmorEvents(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onFall(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (p.getInventory().getBoots() != null) {
                    if (p.getInventory().getBoots().getItemMeta().getDisplayName().contains("§b§lThor's boots")) {
                        e.setCancelled(true);
                        Location loc = p.getLocation();
                        p.spawnParticle(Particle.FLAME, loc, 20);
                    }
                }
            }
        }
    }
}
