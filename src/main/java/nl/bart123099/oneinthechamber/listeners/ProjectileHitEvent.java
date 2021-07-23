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

        Projectile projectile = event.getEntity();
        projectile.remove();
        ProjectileSource source = projectile.getShooter();

        if (!(event.getHitEntity() instanceof Player)) {
            new ArrowCooldownRunnable((Player) source, this.plugin);
            event.setCancelled(true);
            return;
        }

        Player shooter = (Player) source;

        Player hitPlayer = (Player) event.getHitEntity();

        ArrowManager.giveInstantArrow(shooter);
        shooter.sendTitle(ChatColor.YELLOW + "+1 KILL", ChatColor.GREEN + "+1 Arrow", 1, 20, 1);
        hitPlayer.sendTitle(ChatColor.RED + "+1 DEATH", "", 1, 20, 1);

        SpawnManager spawnManager = new SpawnManager(this.plugin);
        spawnManager.sendToRandomSpawn(hitPlayer);
        event.setCancelled(true);
    }
}
