package ragnarok.main.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import ragnarok.main.Main;
import ragnarok.main.particles.PData;

public class JoinLeave implements Listener {

    private Main plugin;

    public JoinLeave(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String name = e.getPlayer().getName();
        Player player = e.getPlayer();
        Player p = (Player) player;
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("message.join").replace("%PLAYER%", name)));

    }

    @EventHandler
    void onPlayerLeave(PlayerQuitEvent e) {
        String name = e.getPlayer().getName();
        Player p = e.getPlayer();
        PData player = new PData(e.getPlayer().getUniqueId());

        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("message.leave").replace("%PLAYER%", name)));


        if(player.hasID())
            player.endTask();
    }
}
