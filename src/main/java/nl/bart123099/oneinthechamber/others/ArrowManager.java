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

    public static void giveInstantArrow(Player player) {
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        player.getInventory().addItem(arrow);
        player.sendTitle(ChatColor.GREEN + "+1 Arrow", "", 1, 20,1);

    }

    public static void giveInstantBow(Player player) {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        player.getInventory().addItem(bow);
    }
}
