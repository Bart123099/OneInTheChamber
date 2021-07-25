package nl.bart123099.oneinthechamber.listeners;

import nl.bart123099.oneinthechamber.others.ArrowManager;
import nl.bart123099.oneinthechamber.others.SpawnManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Resets the player's inventory to default oneinthechamber settings, and sends him to a random spawnpoint.
        ArrowManager.resetInventoryToStartConditions(player);
        SpawnManager.sendToRandomSpawn(player);
        player.setHealth(20);
        player.setFoodLevel(20);
    }
}
