package ragnarok.main;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ragnarok.main.commands.Ragnarok;
import ragnarok.main.commands.spawn.Respawn;
import ragnarok.main.commands.spawn.SetSpawn;
import ragnarok.main.commands.spawn.SpawnCommand;
import ragnarok.main.commands.subcommands.ThorC;
import ragnarok.main.items.thor.ItemsThor;
import ragnarok.main.listeners.JoinLeave;
import ragnarok.main.listeners.thor.Thor;
import ragnarok.main.listeners.thor.ThorArmorEvents;
import ragnarok.main.listeners.thor.ThorJumpEvents;
import ragnarok.main.listeners.thor.ThorThrow;
import ragnarok.main.particles.Movement;
import ragnarok.main.utils.Chat;
import ragnarok.main.utils.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static ArrayList<Player> ac = new ArrayList<Player>();
    public static ArrayList<Player> thorcharge = new ArrayList<>();
    public static ArrayList<Player> thorfreeze = new ArrayList<>();

    private Main plugin;
    String version = getDescription().getVersion();
    List<String> authors = getDescription().getAuthors();
    String plugname = getDescription().getName();

    public String noperm = getConfig().getString("noperm");


    private void messages() {
        if (getDescription().getName().equals("Ragnarok")) {
            getServer().getConsoleSender().sendMessage("____________________________________");
            getServer().getConsoleSender().sendMessage("|                                  |");
            getServer().getConsoleSender().sendMessage("|__________________________________|");
            getServer().getConsoleSender().sendMessage("");
            getServer().getConsoleSender().sendMessage("Name Plugin: " + plugname);
            getServer().getConsoleSender().sendMessage("Version: " + version);
            getServer().getConsoleSender().sendMessage("Developers: " + authors);
            getServer().getConsoleSender().sendMessage("");
            getServer().getConsoleSender().sendMessage("____________________________________");
            getServer().getConsoleSender().sendMessage("|                                  |");
            getServer().getConsoleSender().sendMessage("|                                  |");
            getServer().getConsoleSender().sendMessage("____________________________________");
        } else {
            getServer().getConsoleSender().sendMessage("RUE Blocked Load");
            getPluginLoader().disablePlugin(this);
        }

    }


    public void commands(){
        new ThorC(this);
        new SetSpawn(this);
        new SpawnCommand(this);
        new Chat(this);
        new Ragnarok(this);
        new JoinLeave(this);

    }

    public void thor(){
        new Thor(this);
        new ThorJumpEvents(this);
        new ThorThrow(this);
        new ThorArmorEvents(this);
    }

    public void events(){
        new Movement(this);
        new ScoreBoard(this);
        new Respawn(this);
        new JoinLeave(this);

    }

    @Override
    public void onEnable() {

        events();
        commands();
        ItemsThor.init();


        getConfig().options().copyDefaults(true);
        saveConfig();
        messages();
    }

    @Override
    public void onDisable() {
    }
}
