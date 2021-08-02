package nl.bart123099.oneinthechamber.listeners;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import nl.bart123099.oneinthechamber.data.DataHandler;
import nl.bart123099.oneinthechamber.data.PlayerData;
import nl.bart123099.oneinthechamber.others.ArrowManager;
import nl.bart123099.oneinthechamber.others.SpawnManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.concurrent.CompletableFuture;

public class PlayerJoinQuitListener implements Listener {

    DataHandler mongoDataHandler;

    public PlayerJoinQuitListener(DataHandler mongoDataHandler) {
        this.mongoDataHandler = mongoDataHandler;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        // Gets the player's data from the database, if the playerdate doesn't exist, create new playerdata.
        CompletableFuture<PlayerData> data = mongoDataHandler.getData(event.getPlayer().getUniqueId());
        data.whenComplete((playerData, throwable) -> {
            if (playerData == null) player.kickPlayer("Er is iets foutgegaan");
            OneInTheChamber.onlinePlayers.put(player.getUniqueId(), playerData);
        });

        // Resets the player's inventory to default oneinthechamber settings, and sends him to a random spawnpoint.
        ArrowManager.resetInventoryToStartConditions(player);
        SpawnManager.sendToRandomSpawn(player);
        player.setHealth(20);
        player.setFoodLevel(20);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        PlayerData playerData = OneInTheChamber.onlinePlayers.get(player.getUniqueId());
        mongoDataHandler.saveData(playerData);

        OneInTheChamber.onlinePlayers.remove(player.getUniqueId());
    }
}

