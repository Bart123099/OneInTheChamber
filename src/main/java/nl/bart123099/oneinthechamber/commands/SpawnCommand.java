package nl.bart123099.oneinthechamber.commands;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import nl.bart123099.oneinthechamber.others.SpawnManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private OneInTheChamber plugin;

    public SpawnCommand(OneInTheChamber plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        if(args[0] == null) return true;
        if(label.equals("addspawnlocation")) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            String name = args[0];

            SpawnManager spawnManager = new SpawnManager(this.plugin);
            spawnManager.addSpawnLocationToConfig(name, location);
        }
        return true;
    }
}
