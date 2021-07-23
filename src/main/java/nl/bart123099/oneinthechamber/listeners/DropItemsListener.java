package nl.bart123099.oneinthechamber.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DropItemsListener implements Listener {

    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDropOnDeathEvent(PlayerDeathEvent event) {
        event.setKeepInventory(true);
        List<ItemStack> droppedItems = event.getDrops();
        droppedItems.clear();
    }
}
