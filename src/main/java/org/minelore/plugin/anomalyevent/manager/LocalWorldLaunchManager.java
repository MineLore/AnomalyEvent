package org.minelore.plugin.anomalyevent.manager;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.minelore.plugin.anomalyevent.anomaly.depend.Scheduler;
import org.minelore.plugin.anomalyevent.launcher.LocalLauncher;

import java.util.List;
import java.util.function.Supplier;

public class LocalWorldLaunchManager implements WorldLaunchManager {
    private final Supplier<Player> playerSupplier;
    private final List<LocalLauncher<Player, ?>> localLaunchers;
    private final int periodInTick;
    private final Scheduler scheduler;


    private BukkitTask anomalyTask;

    public LocalWorldLaunchManager(Supplier<Player> playerSupplier, List<LocalLauncher<Player, ?>> localLaunchers, int periodInTick, Scheduler scheduler) {
        this.playerSupplier = playerSupplier;
        this.localLaunchers = localLaunchers;
        this.periodInTick = periodInTick;
        this.scheduler = scheduler;
    }

    public void startTask() {
        if (anomalyTask != null) anomalyTask.cancel();
        anomalyTask = scheduler.runTaskTimer(() -> {
            for (LocalLauncher<Player, ?> localLauncher : localLaunchers) {
                if (localLauncher.hasLaunchReady()) {
                    Player player = playerSupplier.get();
                    if (player != null) {
                        localLauncher.launch(player);
                    }
                }
            }
        }, periodInTick, periodInTick);
    }

    public void stopTask() {
        if (anomalyTask != null) anomalyTask.cancel();
    }
}
