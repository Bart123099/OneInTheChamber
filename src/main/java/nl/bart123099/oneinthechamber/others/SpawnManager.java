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

    public Map<String, Location> getSpawnLocations() {
        return spawnLocations;
    }

    public void getSpawnLocationsFromConfig() {
        for(String name: plugin.getConfig().getKeys(false)) {
            spawnLocations.put(name, plugin.getConfig().getLocation(name));
        }
    }

    public void addSpawnLocationToConfig(String name, Location location) {
        if(spawnLocations.containsKey(name)) return;
        spawnLocations.put(name, location);
        plugin.getConfig().set(name, location);
        plugin.saveDefaultConfig();
    }

    public void removeSpawnLocationFromConfig(String name) {
        if(!spawnLocations.containsKey(name)) return;
        spawnLocations.remove(name);
        plugin.getConfig().set("spawnlocations." + name, null);
        plugin.saveDefaultConfig();
    }

    public void sendToRandomSpawn(Player player) {

        if(spawnLocations.isEmpty()) return;
        Random random = new Random();
        Object[] locations = spawnLocations.values().toArray();
        Object chosenLocation = locations[random.nextInt(locations.length)];
        Location location = (Location) chosenLocation;

        player.teleport(location);

    }
}
