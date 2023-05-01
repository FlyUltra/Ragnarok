package ragnarok.main.commands.subcommands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitScheduler;
import ragnarok.main.Main;
import ragnarok.main.items.ItemManager;
import ragnarok.main.items.thor.ItemsThor;

import java.util.ArrayList;

public class ThorC implements CommandExecutor {

    private Main plugin;


    public ThorC(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("thor").setExecutor((CommandExecutor) this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("Thor")) {
            if (!(sender instanceof Player))
                return true;
            Player player = (Player) sender;
            Location ploc = player.getLocation();
            player.getWorld().strikeLightningEffect(ploc);



            String n1 = "\n";
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor("Odin");
            bookMeta.setTitle(ChatColor.BLUE + "§b§lThor");

            ArrayList<String> pages = new ArrayList<String>();

            pages.add(0, ChatColor.translateAlternateColorCodes('&',
                    "      §b§lThor" +
            n1 + " " +
            n1 + "§bHow to use?" +
            n1 + " "








));


                    bookMeta.setPages(pages);
                book.setItemMeta(bookMeta);
                player.getInventory().addItem(ItemsThor.Hammer);
                player.getInventory().addItem(ItemsThor.ThorBoots);
                player.getInventory().addItem(book);



            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b&lOdin &8≻≻ " + "&7Hodně štěstí synu!"));
                }
            }, 20);

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&e&lRagnarok &8≻≻" + "&7Thor zdvihl své Kladivo &b(&e&lMjöllni&b)!"));
                return true;
            }

        return true;
    }
}























