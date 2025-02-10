package org.minelore.plugin.anomalyevent.activator;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitTask;
import org.minelore.plugin.anomalyevent.AnomalyEvent;
import org.minelore.plugin.anomalyevent.data.ThunderStrikeData;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ThunderStrikeAnomaly<T extends LivingEntity> extends FromDataAnomaly<ThunderStrikeData, T> implements Anomaly.Deactivable<T> {
    protected final Map<UUID, BukkitTask> tasks = new ConcurrentHashMap<>();
    protected final AnomalyEvent plugin;

    public ThunderStrikeAnomaly(AnomalyEvent plugin, String name, ThunderStrikeData data) {
        super(name, data);
        this.plugin = plugin;
    }

    @Override
    public void activate(LivingEntity target) {
        deactivate(target);
        if (data.interval.isZero()) {
            for (int i = 0; i < data.countLighting; i++) {
                target.getWorld().strikeLightning(target.getLocation());
            }
        }
        else {
            AtomicInteger counter = new AtomicInteger(data.countLighting);
            BukkitTask bukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                if (counter.intValue() != 0) {
                    target.getWorld().strikeLightning(target.getLocation());
                    counter.decrementAndGet();
                }
                else {
                    Optional.ofNullable(tasks.get(target.getUniqueId())).ifPresent(BukkitTask::cancel);
                }
            }, 20, data.interval.get(ChronoUnit.SECONDS) * 20);
            tasks.put(target.getUniqueId(), bukkitTask);
        }
    }

    @Override
    public void deactivate(LivingEntity target) {
        tasks.remove(target.getUniqueId());
    }
}
