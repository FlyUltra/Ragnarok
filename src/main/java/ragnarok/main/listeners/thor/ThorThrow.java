package ragnarok.main.listeners.thor;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import ragnarok.main.Main;
import ragnarok.main.items.thor.ItemsThor;
import ragnarok.main.particles.EffectTask;
import ragnarok.main.particles.PData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.bukkit.entity.ArmorStand.LockType.*;

public class ThorThrow implements Listener {

    private Main plugin;

    public ThorThrow(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }

    Map<String, Long> coldthrowcharge = new HashMap<String, Long>();
    Map<String, Long> coldthrow = new HashMap<String, Long>();
    Map<String, Long> coldlighting = new HashMap<String, Long>();
    Map<String, Long> coldjump = new HashMap<String, Long>();
    Map<String, Long> coldparticle = new HashMap<String, Long>();

    @EventHandler
    public void onThrow(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        PData particle = new PData(p.getUniqueId());
        EffectTask trails = new EffectTask(p);


        if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() != null &&
                    p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("§b§lThor's hammer")) {


                if (Main.thorcharge.contains(p)) {
                    if (p.isSneaking()) {

                        p.getWorld().strikeLightningEffect(loc);
                        p.getWorld().strikeLightningEffect(loc);
                        p.getWorld().strikeLightningEffect(loc);
                        p.getWorld().strikeLightningEffect(loc);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Location location = p.getTargetBlock(null, 30).getLocation();
                                p.getWorld().strikeLightning(location);
                                p.getWorld().strikeLightning(location);

                                cancel();

                            }
                        }.runTaskTimer(plugin, 50L, 20L);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                p.getWorld().strikeLightningEffect(loc.add(0, 20, 0));


                                cancel();

                            }
                        }.runTaskTimer(plugin, 50L, 20L);


                    } else {

                        if (coldthrowcharge.containsKey(p.getName())) {
                            if (coldthrowcharge.get(p.getName()) > System.currentTimeMillis()) {

                                long timeleft = (coldthrowcharge.get(p.getName()) - System.currentTimeMillis()) / 1000;
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                        TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&b&lJsi moc unavený počkej " + timeleft + " &9(s)")));
                                return;
                            }
                        }

                        coldthrowcharge.put(p.getName(), System.currentTimeMillis() + (7 * 1000));
                        ArmorStand armorStandd = (ArmorStand) Bukkit.getWorld("world").spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);


                        ArmorStand armorStand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                        Location destination = p.getLocation().add(p.getLocation().getDirection().multiply(20));
                        p.getWorld().setStorm(true);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Bukkit.getWorld("world").setStorm(true);
                            }
                        }.runTaskTimer(plugin, 10L, 1L);

                        armorStand.addEquipmentLock(EquipmentSlot.HAND, ADDING_OR_CHANGING);
                        armorStand.addEquipmentLock(EquipmentSlot.HAND, REMOVING_OR_CHANGING);
                        armorStand.addEquipmentLock(EquipmentSlot.HAND, ADDING);
                        armorStand.setArms(true);
                        armorStand.setGravity(false);
                        armorStand.setVisible(false);
                        armorStand.setItemInHand(ItemsThor.Hammer);
                        armorStand.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(60), Math.toRadians(0)));
                        EulerAngle body = armorStand.getBodyPose();


                        p.getInventory().removeItem(ItemsThor.Hammer);

                        Vector vector = destination.subtract(p.getLocation()).toVector();

                        new BukkitRunnable() {

                            int distance = 60;
                            int i = 0;

                            public void run() {

                                EulerAngle rot = armorStand.getRightArmPose();
                                EulerAngle rotnew = rot.add(20, 0, 0);
                                armorStand.setRightArmPose(rotnew);

                                if (i >= distance) {
                                    armorStand.teleport(armorStand.getLocation().subtract(vector.normalize()));
                                    if (i >= distance * 2) {
                                        armorStand.remove();
                                        if (p.getInventory().firstEmpty() != -1) {
                                            p.getInventory().addItem(ItemsThor.Hammer);
                                        } else {
                                            p.getWorld().dropItemNaturally(p.getLocation(), ItemsThor.Hammer);
                                        }
                                        cancel();
                                    }
                                } else {
                                    armorStand.teleport(armorStand.getLocation().add(vector.normalize()));
                                }

                                i++;

                                String finaldamagecharge = "30";

                                for (Entity entity : armorStand.getLocation().getChunk().getEntities()) {
                                    if (!armorStand.isDead()) {
                                        if (armorStand.getLocation().distanceSquared(entity.getLocation()) <= 1) {
                                            if (entity != p && entity != armorStand) {
                                                if (entity instanceof LivingEntity) {
                                                    LivingEntity livingentity = (LivingEntity) entity;
                                                    livingentity.damage(Integer.parseInt(finaldamagecharge), p);
                                                    livingentity.addPotionEffect((new PotionEffect(PotionEffectType.BLINDNESS, 5, 5)));
                                                    livingentity.setVelocity(livingentity.getLocation().getDirection().multiply(0).setY(-1));

                                                    armorStand.remove();
                                                    if (p.getInventory().firstEmpty() != -1) {

                                                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                                                        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                p.setInvulnerable(true);
                                                            }
                                                        }, 20);

                                                        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                p.setInvulnerable(false);
                                                                p.getInventory().addItem(ItemsThor.Hammer);
                                                            }
                                                        }, 25);


                                                    } else {
                                                        p.getWorld().dropItemNaturally(p.getLocation(), ItemsThor.Hammer);
                                                    }
                                                    cancel();
                                                }
                                            }
                                        }
                                    }
                                }

                                if (armorStand.getTargetBlockExact(1) != null && !armorStand.getTargetBlockExact(1).isPassable()) {
                                    if (!armorStand.isDead()) {
                                        armorStand.remove();
                                        if (p.getInventory().firstEmpty() != -1) {
                                            p.getInventory().addItem(ItemsThor.Hammer);
                                        } else {
                                            p.getWorld().dropItemNaturally(p.getLocation(), ItemsThor.Hammer);
                                        }
                                        cancel();
                                    }
                                }

                            }
                        }.runTaskTimer(plugin, 1L, 1L);

                        event.setCancelled(true);
                    }
                } else {

                    if (p.isSneaking()) {

                        if (coldlighting.containsKey(p.getName())) {
                            if (coldlighting.get(p.getName()) > System.currentTimeMillis()) {

                                long timeleft = (coldlighting.get(p.getName()) - System.currentTimeMillis()) / 1000;
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                        TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c&lJsi moc unavený počkej " + timeleft + " &c(s)")));
                                return;
                            }
                        }

                        coldlighting.put(p.getName(), System.currentTimeMillis() + (60 * 1000));


                        Location location = p.getTargetBlock((HashSet) null, 15).getLocation();
                        p.getWorld().strikeLightningEffect(location);
                    } else {

                        if (coldthrow.containsKey(p.getName())) {
                            if (coldthrow.get(p.getName()) > System.currentTimeMillis()) {

                                long timeleft = (coldthrow.get(p.getName()) - System.currentTimeMillis()) / 1000;
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                        TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c&lJsi moc unavený počkej " + timeleft + " &c(s)")));
                                return;
                            }
                        }

                        coldthrow.put(p.getName(), System.currentTimeMillis() + (20 * 1000));


                        ArmorStand armorStand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                        Location destination = p.getLocation().add(p.getLocation().getDirection().multiply(10));
                        Bukkit.getWorld("world").setStorm(true);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Bukkit.getWorld("world").setStorm(true);
                            }
                        }.runTaskTimer(plugin, 10L, 1L);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Bukkit.getWorld("world").setStorm(false);
                            }
                        }.runTaskTimer(plugin, 560L, 1L);


                        armorStand.setArms(true);
                        armorStand.setGravity(false);
                        armorStand.setVisible(false);
                        armorStand.setItemInHand(ItemsThor.Hammer);
                        armorStand.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(60), Math.toRadians(0)));
                        armorStand.addEquipmentLock(EquipmentSlot.HAND, ADDING_OR_CHANGING);
                        armorStand.addEquipmentLock(EquipmentSlot.HAND, REMOVING_OR_CHANGING);
                        armorStand.addEquipmentLock(EquipmentSlot.HAND, ADDING);

                        p.getInventory().removeItem(ItemsThor.Hammer);

                        Vector vector = destination.subtract(p.getLocation()).toVector();

                        new BukkitRunnable() {

                            int distance = 40;
                            int i = 0;

                            public void run() {

                                EulerAngle rot = armorStand.getRightArmPose();
                                EulerAngle rotnew = rot.add(20, 0, 0);
                                armorStand.setRightArmPose(rotnew);

                                if (i >= distance) {
                                    armorStand.teleport(armorStand.getLocation().subtract(vector.normalize()));
                                    if (i >= distance * 2) {
                                        armorStand.remove();
                                        if (p.getInventory().firstEmpty() != -1) {
                                            p.getInventory().addItem(ItemsThor.Hammer);
                                        } else {
                                            p.getWorld().dropItemNaturally(p.getLocation(), ItemsThor.Hammer);
                                        }
                                        cancel();
                                    }
                                } else {
                                    armorStand.teleport(armorStand.getLocation().add(vector.normalize()));
                                }

                                i++;

                                String finaldamage = "15";

                                for (Entity entity : armorStand.getLocation().getChunk().getEntities()) {
                                    if (!armorStand.isDead()) {
                                        if (armorStand.getLocation().distanceSquared(entity.getLocation()) <= 1) {
                                            if (entity != p && entity != armorStand) {
                                                if (entity instanceof LivingEntity) {
                                                    LivingEntity livingentity = (LivingEntity) entity;
                                                    livingentity.damage(Integer.parseInt(finaldamage), p);
                                                    armorStand.remove();
                                                    if (p.getInventory().firstEmpty() != -1) {

                                                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                                                        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                p.setInvulnerable(true);
                                                            }
                                                        }, 20);

                                                        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                p.setInvulnerable(false);
                                                                p.getInventory().addItem(ItemsThor.Hammer);
                                                            }
                                                        }, 25);


                                                    } else {
                                                        p.getWorld().dropItemNaturally(p.getLocation(), ItemsThor.Hammer);
                                                    }
                                                    cancel();
                                                }
                                            }
                                        }
                                    }
                                }

                                if (armorStand.getTargetBlockExact(1) != null && !armorStand.getTargetBlockExact(1).isPassable()) {
                                    if (!armorStand.isDead()) {
                                        armorStand.remove();
                                        if (p.getInventory().firstEmpty() != -1) {
                                            p.getInventory().addItem(ItemsThor.Hammer);
                                        } else {
                                            p.getWorld().dropItemNaturally(p.getLocation(), ItemsThor.Hammer);
                                        }
                                        cancel();
                                    }
                                }
                            }
                        }.runTaskTimer(plugin, 1L, 1L);

                        event.setCancelled(true);
                    }
                }
            }
        }


    }
}

