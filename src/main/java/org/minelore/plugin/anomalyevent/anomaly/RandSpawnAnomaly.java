package org.minelore.plugin.anomalyevent.anomaly;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pose;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.Vector;
import org.minelore.plugin.anomalyevent.data.RandSpawnData;
import org.minelore.plugin.anomalyevent.util.RandomUtil;
import org.minelore.plugin.anomalyevent.util.RayTraceUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Аномалия на рандомное появление мобов в радиусе
 * @param <T> класс цели, во круг которой будут появляться мобы. Например, {@link org.bukkit.entity.Player}
 */
public class RandSpawnAnomaly<T extends LivingEntity> extends FromDataAnomaly<RandSpawnData, T> {
    public RandSpawnAnomaly(String name, RandSpawnData data) {
        super(name, data);
    }

    @Override
    public void activate(T target) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < data.count; i++) {
            double distance = random.nextDouble(data.maxDistance);
            Vector vector = RandomUtil.randomVector(distance);
            Location spawnLocation = RayTraceUtil.rayTraceNoCollisionBlock(target.getLocation(), target.getWorld(), vector, distance);
            if (data.entityType.getEntityClass() == null) {
                throw new IllegalStateException("Unknown entity type "+ data.entityType);
            }
            target.getWorld().spawn(spawnLocation, data.entityType.getEntityClass(), CreatureSpawnEvent.SpawnReason.CUSTOM)
                    .setPose(Pose.SWIMMING);
        }
    }
}
