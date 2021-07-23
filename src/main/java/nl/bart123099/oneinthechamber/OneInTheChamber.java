package nl.bart123099.oneinthechamber;

import nl.bart123099.oneinthechamber.commands.SpawnCommand;
import nl.bart123099.oneinthechamber.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class OneInTheChamber extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new ProjectileHitEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerHungerListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DropItemsListener(), this);
        getCommand("addspawnlocation").setExecutor(new SpawnCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
