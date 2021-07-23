package nl.bart123099.oneinthechamber.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerHungerListener implements Listener {

    @EventHandler
    public void onPlayerHungerEvent(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
