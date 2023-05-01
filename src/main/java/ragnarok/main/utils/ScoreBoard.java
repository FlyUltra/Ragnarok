package ragnarok.main.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.*;
import ragnarok.main.Main;

public class ScoreBoard implements Listener {

    private Main plugin;

    public ScoreBoard(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        createBoard(e.getPlayer());
    }

    public void createBoard(Player p){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("cs", "cs", ChatColor.translateAlternateColorCodes('&',"&a&l<< &e&lRagnarok &a&l>>"));
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);


            Score score = obj.getScore(ChatColor.GOLD + "Zlatáku: " + "0");
            score.setScore(1);


            Score score2 = obj.getScore(ChatColor.GOLD + "" + "");
            score2.setScore(2);


            Score score3 = obj.getScore(ChatColor.GREEN + "Level: " + 1);
            score3.setScore(3);

            Score score4 = obj.getScore(ChatColor.GREEN + " " + "");
            score4.setScore(4);

            Score score5 = obj.getScore(ChatColor.BLUE + "Role: " + "Thor");
            score5.setScore(5);
            p.setScoreboard(board);
    }





}
