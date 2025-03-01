package org.minelore.plugin.anomalyevent.manager;

import org.bukkit.scheduler.BukkitTask;
import org.minelore.plugin.anomalyevent.anomaly.depend.Scheduler;
import org.minelore.plugin.anomalyevent.launcher.GlobalLauncher;

import java.util.List;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public class GlobalWorldLaunchManager implements WorldLaunchManager {
    private final List<GlobalLauncher<?>> localLaunchers;
    private final int periodInTick;
    private final Scheduler scheduler;


    private BukkitTask anomalyTask;

    public GlobalWorldLaunchManager(List<GlobalLauncher<?>> localLaunchers, int periodInTick, Scheduler scheduler) {
        this.localLaunchers = localLaunchers;
        this.periodInTick = periodInTick;
        this.scheduler = scheduler;
    }

    public void startTask() {
        if (anomalyTask != null) anomalyTask.cancel();
        anomalyTask = scheduler.runTaskTimer(() -> {
            for (GlobalLauncher<?> localLauncher : localLaunchers) {
                if (localLauncher.hasLaunchReady()) {
                    localLauncher.launch();
                }
            }
        }, periodInTick, periodInTick);
    }

    public void stopTask() {
        if (anomalyTask != null) anomalyTask.cancel();
    }
}
