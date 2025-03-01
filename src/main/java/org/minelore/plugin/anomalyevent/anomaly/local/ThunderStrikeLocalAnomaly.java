package org.minelore.plugin.anomalyevent.anomaly.local;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;
import org.minelore.plugin.anomalyevent.AnomalyEvent;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Аномалия на удар молнией по цели
 * @param <T> класс цели, в которую будет ударять молния. Например, {@link org.bukkit.entity.Player}
 */
public class ThunderStrikeLocalAnomaly<T extends LivingEntity> extends AbstractLocalAnomaly<T> implements LocalAnomaly.Deactivable<T> {
    private final AnomalyEvent plugin;
    private final int countLighting;
    private final Duration interval;

    private final Map<UUID, BukkitTask> tasks = new ConcurrentHashMap<>();
    private final WeakHashMap<UUID, ImpactAnomalyBukkitTask> cache = new WeakHashMap<>();

    public ThunderStrikeLocalAnomaly(AnomalyEvent plugin, String name, int countLighting, Duration interval) {
        super(name);
        this.plugin = plugin;
        this.countLighting = countLighting;
        this.interval = interval;
    }

    @Override
    public void activate(LivingEntity target) {
        deactivate(target);
        //т.к. интервала между ударами не будет, можно сразу одновременно ударить всеми молниями
        if (interval.isZero()) {
            for (int i = 0; i < countLighting; i++) {
                target.getWorld().strikeLightning(target.getLocation());
            }
        }
        else {
            AtomicInteger counter = new AtomicInteger(countLighting);
            BukkitTask bukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                if (counter.intValue() != 0) {
                    target.getWorld().strikeLightning(target.getLocation());
                    counter.decrementAndGet();
                }
                else {
                    Optional.ofNullable(tasks.get(target.getUniqueId())).ifPresent(BukkitTask::cancel);
                }
            }, 20, interval.get(ChronoUnit.SECONDS) * 20);
            tasks.put(target.getUniqueId(), bukkitTask);
        }
    }

    @Override
    public void deactivate(LivingEntity target) {
        tasks.remove(target.getUniqueId()).cancel();
        cache.remove(target.getUniqueId());
    }

    @Override
    public void deactivateAll() {
        Iterator<BukkitTask> iterator = tasks.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel();
            iterator.remove();
        }
        cache.clear();
    }

    @Override
    public boolean hasActivate(T target) {
        return tasks.containsKey(target.getUniqueId());
    }

    @Override
    @Nullable
    public ImpactAnomaly<T> getActivated(T target) {
        BukkitTask bukkitTask = tasks.get(target.getUniqueId());
        if (bukkitTask != null) {
            return cache.computeIfAbsent(target.getUniqueId(), (uuid) -> new ImpactAnomalyBukkitTask(target, bukkitTask));
        }
        else return null;
    }

    class ImpactAnomalyBukkitTask implements ImpactAnomaly<T> {
        private final T target;
        private final BukkitTask bukkitTask;
        protected ImpactAnomalyBukkitTask(T target, BukkitTask bukkitTask) {
            this.target = target;
            this.bukkitTask = bukkitTask;
        }

        @Override
        public T getTarget() {
            return target;
        }

        @Override
        public boolean deactivate() {
            boolean beforeIsCancel = bukkitTask.isCancelled();
            tasks.remove(target.getUniqueId()).cancel();
            bukkitTask.cancel();
            boolean afterIsCancel = bukkitTask.isCancelled();
            return !beforeIsCancel && afterIsCancel;
        }

        @Override
        public boolean hasActive() {
            return !bukkitTask.isCancelled();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ThunderStrikeLocalAnomaly<?> that)) return false;
        if (!super.equals(o)) return false;
        return countLighting == that.countLighting && com.google.common.base.Objects.equal(plugin, that.plugin) && com.google.common.base.Objects.equal(interval, that.interval) && com.google.common.base.Objects.equal(tasks, that.tasks) && com.google.common.base.Objects.equal(cache, that.cache);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(super.hashCode(), plugin, countLighting, interval, tasks, cache);
    }
}
