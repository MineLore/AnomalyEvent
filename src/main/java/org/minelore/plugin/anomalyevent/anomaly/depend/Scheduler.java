package org.minelore.plugin.anomalyevent.anomaly.depend;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.minelore.plugin.anomalyevent.AnomalyEvent;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class Scheduler {
    private final AnomalyEvent plugin;
    private final BukkitScheduler bukkitScheduler;

    public Scheduler(AnomalyEvent plugin, BukkitScheduler bukkitScheduler) {
        this.plugin = plugin;
        this.bukkitScheduler = bukkitScheduler;
    }

    public static Scheduler getScheduler(AnomalyEvent plugin) {
        return new Scheduler(plugin, Bukkit.getScheduler());
    }

    public void runTaskTimerAsynchronously(@NotNull Consumer<? super BukkitTask> consumer, long l, long l1) throws IllegalArgumentException {
        bukkitScheduler.runTaskTimerAsynchronously(plugin, consumer, l, l1);
    }

    public @NotNull BukkitTask runTaskTimerAsynchronously(@NotNull Runnable runnable, long l, long l1) throws IllegalArgumentException {
        return bukkitScheduler.runTaskTimerAsynchronously(plugin, runnable, l, l1);
    }

    public void runTaskTimer(@NotNull Consumer<? super BukkitTask> consumer, long l, long l1) throws IllegalArgumentException {
        bukkitScheduler.runTaskTimer(plugin, consumer, l, l1);
    }

    public @NotNull BukkitTask runTaskTimer(@NotNull Runnable runnable, long l, long l1) throws IllegalArgumentException {
        return bukkitScheduler.runTaskTimer(plugin, runnable, l, l1);
    }

    public void runTaskLaterAsynchronously(@NotNull Consumer<? super BukkitTask> consumer, long l) throws IllegalArgumentException {
        bukkitScheduler.runTaskLaterAsynchronously(plugin, consumer, l);
    }

    public @NotNull BukkitTask runTaskLaterAsynchronously(@NotNull Runnable runnable, long l) throws IllegalArgumentException {
        return bukkitScheduler.runTaskLaterAsynchronously(plugin, runnable, l);
    }

    public void runTaskLater(@NotNull Consumer<? super BukkitTask> consumer, long l) throws IllegalArgumentException {
        bukkitScheduler.runTaskLater(plugin, consumer, l);
    }

    public @NotNull BukkitTask runTaskLater(@NotNull Runnable runnable, long l) throws IllegalArgumentException {
        return bukkitScheduler.runTaskLater(plugin, runnable, l);
    }

    public void runTaskAsynchronously(@NotNull Consumer<? super BukkitTask> consumer) throws IllegalArgumentException {
        bukkitScheduler.runTaskAsynchronously(plugin, consumer);
    }

    public @NotNull BukkitTask runTaskAsynchronously(@NotNull Runnable runnable) throws IllegalArgumentException {
        return bukkitScheduler.runTaskAsynchronously(plugin, runnable);
    }

    public void runTask(@NotNull Consumer<? super BukkitTask> consumer) throws IllegalArgumentException {
        bukkitScheduler.runTask(plugin, consumer);
    }

    public @NotNull BukkitTask runTask(@NotNull Runnable runnable) throws IllegalArgumentException {
        return bukkitScheduler.runTask(plugin, runnable);
    }

    public int scheduleSyncDelayedTask(@NotNull Runnable runnable, long l) {
        return bukkitScheduler.scheduleSyncDelayedTask(plugin, runnable, l);
    }

    public int scheduleSyncDelayedTask(@NotNull Runnable runnable) {
        return bukkitScheduler.scheduleSyncDelayedTask(plugin, runnable);
    }

    public int scheduleSyncRepeatingTask(@NotNull Runnable runnable, long l, long l1) {
        return bukkitScheduler.scheduleSyncRepeatingTask(plugin, runnable, l, l1);
    }

    public @NotNull <T> Future<T> callSyncMethod(@NotNull Callable<T> callable) {
        return bukkitScheduler.callSyncMethod(plugin, callable);
    }

    public void cancelTask(int i) {
        bukkitScheduler.cancelTask(i);
    }
}
