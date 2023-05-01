package ragnarok.main.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import ragnarok.main.Main;

import java.util.ArrayList;
import java.util.List;

public class GungnirC implements CommandExecutor {

    private Main plugin;


    public GungnirC(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("thor").setExecutor((CommandExecutor) this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("Thor")) {
            if (!(sender instanceof Player))
                return true;
            Player player = (Player) sender;
            Location ploc = player.getLocation();
            player.getWorld().strikeLightning(ploc);

            player.setInvulnerable(true);


            ItemStack item = new ItemStack(Material.STONE_PICKAXE);
            item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
            item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
            item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
            item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);


            ItemMeta meta = item.getItemMeta();

            List<String> lore = new ArrayList<String>();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "§4§lGungnir"));
            lore.add(ChatColor.BLUE + "Mjöllni");
            lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "It is not a person who ");
            lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "starts a war, but circumstances");
            lore.add("");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            meta.setUnbreakable(true);


            player.getInventory().addItem(item);


            String n1 = "\n";
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor("Odin");
            bookMeta.setTitle(ChatColor.BLUE + "§b§lThor");

            ArrayList<String> pages = new ArrayList<String>();

            pages.add(0, ChatColor.translateAlternateColorCodes('&', "      §b§lThor" +
                    n1 + " " +
                    n1 + "§bHow to use?"));
            bookMeta.setPages(pages);
            book.setItemMeta(bookMeta);
            player.getInventory().addItem(book);



            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b&lOdin: " + "&fGood luck!"));
                    player.setInvulnerable(false);
                }
            }, 20);

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&d&lRagnarok: " + "&bThor grabbed his hammer!"));
            return true;
        }

        return true;
    }
}









