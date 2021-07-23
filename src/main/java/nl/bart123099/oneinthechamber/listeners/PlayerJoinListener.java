package nl.bart123099.oneinthechamber.listeners;

import nl.bart123099.oneinthechamber.others.ArrowManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        ArrowManager.giveInstantBow(player);
        ArrowManager.giveInstantArrow(player);
        //Teleport to random spawn
    }
}
