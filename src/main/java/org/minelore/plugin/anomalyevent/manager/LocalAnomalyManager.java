package org.minelore.plugin.anomalyevent.manager;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.minelore.plugin.anomalyevent.anomaly.depend.Scheduler;
import org.minelore.plugin.anomalyevent.launcher.Launcher;

import java.util.List;
import java.util.function.Supplier;

public class LocalAnomalyManager {
    private final Supplier<Player> playerSupplier;
    private final List<Launcher<Player, ?>> launchers;
    private final int periodInTick;
    private final Scheduler scheduler;


    private BukkitTask anomalyTask;

    public LocalAnomalyManager(Supplier<Player> playerSupplier, List<Launcher<Player, ?>> launchers, int periodInTick, Scheduler scheduler) {
        this.playerSupplier = playerSupplier;
        this.launchers = launchers;
        this.periodInTick = periodInTick;
        this.scheduler = scheduler;
    }

    public void startTask() {
        if (anomalyTask != null) anomalyTask.cancel();
        anomalyTask = scheduler.runTaskTimer(() -> {
            for (Launcher<Player, ?> launcher : launchers) {
                if (launcher.hasLaunchReady()) {
                    Player player = playerSupplier.get();
                    if (player != null) {
                        launcher.launch(player);
                    }
                }
            }
        }, periodInTick, periodInTick);
    }

    public void stopTask() {
        if (anomalyTask != null) anomalyTask.cancel();
    }
}
