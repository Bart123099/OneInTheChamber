package nl.bart123099.oneinthechamber.commands;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import nl.bart123099.oneinthechamber.others.SpawnManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private OneInTheChamber plugin;
    private SpawnManager spawnManager;

    public SpawnCommand(OneInTheChamber plugin, SpawnManager spawnManager) {
        this.plugin = plugin;
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        if(args[0] == null) return true;
        String name = args[0];
        Player player = (Player) sender;
        Location location = player.getLocation();

        if(label.equals("addspawnlocation")) {

            // If the name already exists in the spawnlocations config, send the player a message that the name already is occupied
            if(SpawnManager.nameExistsInConfig(name)) {
                player.sendMessage(OneInTheChamber.serverPrefix + ChatColor.GOLD + name + " is an already existing name, choose another name please!");
                return true;
            }

            // Add the location to the spawnlocations config and send the player a message that the action was successful.
            player.sendMessage(OneInTheChamber.serverPrefix + "You just added the spawnlocation: " + ChatColor.GOLD + name);
            this.spawnManager.addSpawnLocationToConfig(name, location);
            return true;
        }

        if(label.equals("removespawnlocation")) {

            //If the name doesn't exist in the spawnlocations config, send the player a message that the name doesn't exist.
            if(!SpawnManager.nameExistsInConfig(name)) {
                player.sendMessage(OneInTheChamber.serverPrefix + "Spawnlocation " + ChatColor.GOLD + name + ChatColor.YELLOW + " doesn't exist!");
                return true;
            }

            // Remove the spawnlocation from the spawnlocations config and send the player a message that the action was successful;
            player.sendMessage(OneInTheChamber.serverPrefix + "You just removed: " + ChatColor.GOLD + name);
            this.spawnManager.removeSpawnLocationFromConfig(name);
            return true;
        }
        return false;
    }
}
