package nl.bart123099.oneinthechamber.others;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class ArrowCooldownRunnable {

    private final Player player;
    private static final long duration = TimeUnit.SECONDS.toNanos(3);
    private final long start;

    public ArrowCooldownRunnable(Player player, OneInTheChamber plugin) {

        this.player = player;
        this.start = System.nanoTime();

        bukkitRunnable.runTaskTimer(plugin,0, 1);
    }

    BukkitRunnable bukkitRunnable = new BukkitRunnable()  {
        @Override
        public void run() {
            long timeTraveled = System.nanoTime() - start;
            if(timeTraveled > duration) {
                player.setExp(0F);
                ArrowManager.giveInstantArrow(player);
                cancel();
            }
            float xpBar = 0F + (float) timeTraveled / (float) duration;
            if (!(xpBar >= 0.99F)) {
                player.setExp(xpBar);
            }
        }
    };


}
