package nl.bart123099.oneinthechamber.others;

import nl.bart123099.oneinthechamber.OneInTheChamber;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class ArrowCooldownRunnable {

    private final Player player;
    private static final long duration = TimeUnit.SECONDS.toNanos(3);
    private final long start;

    /**
     * Creates a new instance of ArrowCooldownRunnable, which will give an arrow to the player after the hardcoded duration.
     * The timer will automatically start when constructing this instance.
     * @param player The player that should receive the arrow after 10 seconds.
     * @param plugin This plugin.
     */
    public ArrowCooldownRunnable(Player player, OneInTheChamber plugin) {

        this.player = player;
        this.start = System.nanoTime();

        bukkitRunnable.runTaskTimer(plugin,0, 1);
    }

    BukkitRunnable bukkitRunnable = new BukkitRunnable()  {
        @Override
        public void run() {
            long timePassed = System.nanoTime() - start;

            // When the passed time since calling the ArrowCoolDownRunnable() method is longer than the wanted duration, set the XPbar to 0 and give the arrow.
            if(timePassed > duration) {
                player.setExp(0F);
                ArrowManager.giveInstantArrow(player);
                cancel();
            }

            // Updates the XPbar to represent the current time remaining.
            // It does this by calculating the ratio between timePassed and the duration, and putting this ratio into the player its xpBar.
            float xpBar = 0F + (float) timePassed / (float) duration;
            if (!(xpBar >= 0.99F)) {
                player.setExp(xpBar);
            }
        }
    };


}
