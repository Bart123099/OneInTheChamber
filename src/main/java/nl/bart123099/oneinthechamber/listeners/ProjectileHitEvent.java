package nl.bart123099.oneinthechamber.listeners;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import nl.bart123099.oneinthechamber.others.ArrowCooldownRunnable;
import nl.bart123099.oneinthechamber.others.ArrowManager;
import nl.bart123099.oneinthechamber.others.SpawnManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.projectiles.ProjectileSource;

public class ProjectileHitEvent implements Listener {

    private final OneInTheChamber plugin;

    public ProjectileHitEvent(OneInTheChamber plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHitEvent(org.bukkit.event.entity.ProjectileHitEvent event) {

        // Gets the source of the projectile
        // Removes the arrow so no player can pick it up
        Projectile projectile = event.getEntity();
        projectile.remove();
        ProjectileSource source = projectile.getShooter();

        // Checks if the entity that was hit by the arrow is an instance of Player
        // if not, give the source (the player that shot) a new arrow after 10 seconds.
        if (!(event.getHitEntity() instanceof Player)) {
            new ArrowCooldownRunnable((Player) source, this.plugin);
            event.setCancelled(true);
            return;
        }

        Player shooter = (Player) source;
        Player hitPlayer = (Player) event.getHitEntity();

        // Checks if the shooter hit himself, if so, give an arrow after 10 seconds and send him a message that he hit himself;
        if(shooter == hitPlayer) {
            new ArrowCooldownRunnable(shooter, this.plugin);
            shooter.sendMessage(OneInTheChamber.serverPrefix + "You just hit yourself! That hurts...");
            return;
        }

        //Gives instantly a new arrow to the killer, and sends both killer and hitplayer a titlescreen.
        ArrowManager.giveInstantArrow(shooter);
        shooter.sendTitle(ChatColor.YELLOW + "+1 KILL", "+1 Arrow", 1, 20, 1);
        hitPlayer.sendTitle(ChatColor.RED + "+1 DEATH", "", 1, 20, 1);

        //Sends the hitplayer to a random spawnlocation
        SpawnManager.sendToRandomSpawn(hitPlayer);
        event.setCancelled(true);
    }
}
