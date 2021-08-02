package nl.bart123099.oneinthechamber;

import nl.bart123099.oneinthechamber.commands.SpawnCommand;
import nl.bart123099.oneinthechamber.data.DataHandler;
import nl.bart123099.oneinthechamber.data.MongoDataHandler;
import nl.bart123099.oneinthechamber.data.PlayerData;
import nl.bart123099.oneinthechamber.listeners.*;
import nl.bart123099.oneinthechamber.others.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class OneInTheChamber extends JavaPlugin {

    public static DataHandler dataHandler;
    public static HashMap<UUID, PlayerData> onlinePlayers;

    public final static String serverPrefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "OneInTheChamber" + ChatColor.WHITE + "] " + ChatColor.YELLOW;

    @Override
    public void onEnable() {
        dataHandler = new MongoDataHandler();
        onlinePlayers = new HashMap<UUID, PlayerData>();
        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new ProjectileHitEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinQuitListener(this.dataHandler), this);
        Bukkit.getPluginManager().registerEvents(new PlayerHungerListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new DropItemsListener(), this);
        getCommand("addspawnlocation").setExecutor(new SpawnCommand(this, new SpawnManager(this)));
        getCommand("removespawnlocation").setExecutor(new SpawnCommand(this, new SpawnManager(this)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
