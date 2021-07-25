package nl.bart123099.oneinthechamber.others;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArrowManager {

    private OneInTheChamber plugin;

    public ArrowManager(OneInTheChamber plugin) {
        this.plugin = plugin;
    }

    /**
     * Gives an arrow to the player.
     * @param player the player that should receive the arrow.
     */
    public static void giveInstantArrow(Player player) {
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        player.getInventory().addItem(arrow);
        player.sendTitle("", ChatColor.GREEN + "+1 Arrow", 1, 20,1);

    }

    /**
     * Gives a bow to the player.
     * @param player the player that should receive the bow.
     */
    public static void giveInstantBow(Player player) {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        player.getInventory().addItem(bow);
    }

    /**
     * Clears the player's inventory and gives a bow and arrow to the player
     * @param player The player whose inventory should be set to start conditions
     */
    public static void resetInventoryToStartConditions(Player player) {
        player.getInventory().clear();
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(arrow);
    }
}
