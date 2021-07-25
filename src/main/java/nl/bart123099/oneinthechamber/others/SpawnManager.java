package nl.bart123099.oneinthechamber.others;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpawnManager {
    private static final Map<String, Location> spawnLocations = new HashMap<>();
    private final OneInTheChamber plugin;

    public SpawnManager(OneInTheChamber plugin) {
        this.plugin = plugin;
    }


//    public Map<String, Location> getSpawnLocations() {
//        return spawnLocations;
//    }


//    public void getSpawnLocationsFromConfig() {
//        for(String name: plugin.getConfig().getKeys(false)) {
//            spawnLocations.put(name, plugin.getConfig().getLocation(name));
//        }
//    }

    /**
     * Adds a spawn location to the configuration file
     * @param name The name that the new spawnlocation should get
     * @param location The location of the new spawnlocation
     */
    public void addSpawnLocationToConfig(String name, Location location) {

        // Checks if the given name that the spawnlocation should get is already occupied.
        if(nameExistsInConfig(name)) return;

        // Adds the given spawnlocation to the configuration file.
        spawnLocations.put(name, location);
        plugin.getConfig().set(name, location);
        plugin.saveDefaultConfig();
    }

    /**
     * Removes a spawn location from the configuration file
     * @param name The name of the spawnlocation that should be removed
     */
    public void removeSpawnLocationFromConfig(String name) {

        // Checks if the name of the given spawnlocation does exist in the configuration file.
        if(!nameExistsInConfig(name)) return;

        // Removes the given spawnlocation from the configuration file.
        spawnLocations.remove(name);
        plugin.getConfig().set("spawnlocations." + name, null);
        plugin.saveDefaultConfig();
    }

    public static boolean nameExistsInConfig(String name) {
        return spawnLocations.containsKey(name);
    }

    /**
     * Chooses a random spawnlocation, and sends the player to it.
     * @param player The player that should be sent to the random spawnlocation.
     */
    public static void sendToRandomSpawn(Player player) {

        // Checks if there are any spawnlocations in the configuration file.
        if(spawnLocations.isEmpty()) return;

        // Chooses the random spawnlocation by setting the spawnlocations to an array, and choosing a random position in this array.
        Random random = new Random();
        Object[] locations = spawnLocations.values().toArray();
        Object chosenLocation = locations[random.nextInt(locations.length)];
        Location location = (Location) chosenLocation;

        // Teleports the player to the random chosen location.
        player.teleport(location);

    }
}
