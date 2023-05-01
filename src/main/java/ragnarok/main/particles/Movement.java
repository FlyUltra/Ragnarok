package ragnarok.main.particles;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import ragnarok.main.Main;

import java.util.Random;

public class Movement implements Listener {


    private Main plugin;

    public Movement(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!PData.hasFakeID(event.getPlayer().getUniqueId()))
            return;

        Random r = new Random();
        for (int i = 0; i < 5 ; i++)
            event.getPlayer().getWorld().spawnParticle(Particle.CRIT, event.getPlayer().getLocation().add(
                    r.nextDouble() * 0.5, r.nextDouble() * 0.5, r.nextDouble() * .5), 0);
        for (int i = 0; i < 5 ; i++)
            event.getPlayer().getWorld().spawnParticle(Particle.CRIT, event.getPlayer().getLocation().add(
                    -1*(r.nextDouble() * 0.5), r.nextDouble() * 0.5, (r.nextDouble() * .5) *-1), 0);

        Player p = event.getPlayer();



    }
}

