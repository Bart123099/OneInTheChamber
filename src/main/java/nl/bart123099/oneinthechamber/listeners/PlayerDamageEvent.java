package nl.bart123099.oneinthechamber.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PlayerDamageEvent implements Listener {

    @EventHandler
    public void onItemDamageEvent(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamageEvent(EntityDamageEvent event) {
        if(!(event instanceof Player)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHitPlayerEvent(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }
}
