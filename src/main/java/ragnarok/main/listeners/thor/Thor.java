package ragnarok.main.listeners.thor;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import ragnarok.main.Main;
import ragnarok.main.items.ItemManager;
import ragnarok.main.items.thor.ItemsThor;
import ragnarok.main.particles.EffectTask;
import ragnarok.main.particles.PData;

import java.util.*;

import static org.bukkit.entity.ArmorStand.LockType.*;

public class Thor implements Listener {

    private Main plugin;

    public Thor(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }

    String charge_1 = "&b&lChargování začalo";

    int charge = 15;
    int task;

    String charge_end = "&b&lCharge skončil";
    String charging = "&b&lChargování...";
    String charge_finish = "&b&lChargování dokončeno!";

    Map<String, Long> coldthrow = new HashMap<String, Long>();
    Map<String, Long> coldlighting = new HashMap<String, Long>();
    Map<String, Long> coldjump = new HashMap<String, Long>();
    Map<String, Long> coldparticle = new HashMap<String, Long>();


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();

        PData particle = new PData(p.getUniqueId());
        if (particle.hasID()) {
            particle.endTask();
            particle.removeID();
        }
        EffectTask trails = new EffectTask(p);

        if (event.getItem() != null) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getItem().getType() == Material.STONE_PICKAXE) {
                    if (event.getItem().getItemMeta().getDisplayName().equals("§b§lThor's hammer")) {
                        if (!p.isSneaking()) {

                            /*-------------------------------------------------------------------------------------------------*/
                            //IGNOR
                            if (coldjump.containsKey(p.getName())) {
                                if (coldjump.get(p.getName()) > System.currentTimeMillis()) {

                                    long timeleft = (coldjump.get(p.getName()) - System.currentTimeMillis()) / 1000;
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                            TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c&lJsi moc unavený počkej " + timeleft + " &c(s)")));
                                    return;
                                }
                            }
                            coldjump.put(p.getName(), System.currentTimeMillis() + (120 * 1000));
                            p.setVelocity(p.getLocation().getDirection().multiply(1).setY(1));

                            //IGNOR
                            /*-------------------------------------------------------------------------------------------------*/


                        } else {
                            if (coldparticle.containsKey(p.getName())) {
                                if (coldparticle.get(p.getName()) > System.currentTimeMillis()) {

                                    long timeleft = (coldparticle.get(p.getName()) - System.currentTimeMillis()) / 1000;
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                            TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c&lJsi moc unavený počkej " + timeleft + " &c(s)")));
                                    return;
                                }
                            }
                            /*-------------------------------------------------------------------------------------------------*/

                            coldparticle.put(p.getName(), System.currentTimeMillis() + (120 * 1000));

                            p.setVelocity(p.getLocation().getDirection().multiply(0).setY(1));

                            //TODO: After 2s freeze and spawn 5x lighting and change storm

                            //
                            // FÁZE 1
                            //

                            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    if (charge == 15) {
                                        p.setInvulnerable(true);
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charge_1)));
                                        p.getWorld().setStorm(true);

                                        Main.thorfreeze.add(p);
                                        charge--;
                                    } else if (charge == 14) {
                                        for (int i = 0; i < 10; i++) {
                                            p.getWorld().strikeLightningEffect(loc);


                                        }
                                        p.getWorld().setStorm(true);
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        charge--;
                                    } else if (charge == 13) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        p.getWorld().strikeLightningEffect(loc);
                                        charge--;

                                    } else if (charge == 12) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));

                                        charge--;

                                    } else if (charge == 11) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        p.getWorld().strikeLightningEffect(loc);


                                        charge--;

                                    } else if (charge == 10) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        p.getWorld().strikeLightningEffect(loc);


                                        charge--;

                                    } else if (charge == 9) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        p.getWorld().strikeLightningEffect(loc);


                                        charge--;

                                    } else if (charge == 8) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        p.getWorld().strikeLightningEffect(loc);


                                        charge--;

                                    } else if (charge == 7) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        p.spawnParticle(Particle.CRIT_MAGIC, loc, 80);


                                        charge--;

                                    } else if (charge == 6) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        for (int i = 0; i < 10; i++) {
                                            p.getWorld().strikeLightningEffect(loc);

                                        }
                                        p.spawnParticle(Particle.FLAME, loc.add(0, 3, 0), 20);

                                        charge--;

                                    } else if (charge == 5) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        for (int i = 0; i < 10; i++) {
                                            p.getWorld().strikeLightningEffect(loc);

                                        }
                                        p.spawnParticle(Particle.FLAME, loc.add(0, 3, 0), 20);

                                        charge--;

                                    } else if (charge == 4) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        for (int i = 0; i < 10; i++) {
                                            p.getWorld().strikeLightningEffect(loc);

                                        }
                                        p.spawnParticle(Particle.FLAME, loc.add(0, -3, 0), 10);

                                        charge--;

                                    } else if (charge == 3) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        for (int i = 0; i < 10; i++) {
                                            p.getWorld().strikeLightningEffect(loc);

                                        }
                                        p.spawnParticle(Particle.FLAME, loc.add(0, -3, 0), 10);

                                        charge--;

                                    } else if (charge == 2) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charging)));
                                        for (int i = 0; i < 10; i++) {
                                            p.getWorld().strikeLightningEffect(loc);
                                            p.spawnParticle(Particle.FLAME, loc, 10);

                                        }

                                        charge--;

                                    } else if (charge == 1) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charge_finish)));
                                        p.spawnParticle(Particle.FLAME, loc, 10);
                                        p.setInvulnerable(false);
                                        p.getWorld().strikeLightningEffect(loc);
                                        p.getWorld().strikeLightningEffect(loc);
                                        p.getWorld().strikeLightningEffect(loc);


                                        trails.startThor();
                                        Main.thorfreeze.remove(p);
                                        Main.thorcharge.add(p);
                                        p.getWorld().strikeLightningEffect(loc);
                                        charge--;
                                        Bukkit.getScheduler().cancelTask(task);
                                        charge = 15;
                                    }


                                }
                            }, 10, 10);


                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.getWorld().setStorm(false);
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                            TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', charge_end)));
                                    Main.thorcharge.remove(p);
                                    cancel();

                                }
                            }.runTaskTimer(plugin, 1800L, 650L);

                        }
                    }
                }
            }
        }
    }
}