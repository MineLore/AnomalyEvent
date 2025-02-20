package org.minelore.plugin.anomalyevent.anomaly;

import com.google.common.base.Objects;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pose;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.Vector;
import org.minelore.plugin.anomalyevent.util.RandomUtil;
import org.minelore.plugin.anomalyevent.util.RayTraceUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Аномалия на рандомное появление мобов в радиусе
 * @param <T> класс цели, во круг которой будут появляться мобы. Например, {@link org.bukkit.entity.Player}
 */
public class RandSpawnAnomaly<T extends LivingEntity> extends AbstractAnomaly<T> {
    private final EntityType entityType;
    private final int count;
    private final double maxDistance;

    public RandSpawnAnomaly(String name, EntityType entityType, int count, double maxDistance) {
        super(name);
        this.entityType = entityType;
        this.count = count;
        this.maxDistance = maxDistance;
    }

    @Override
    public void activate(T target) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < count; i++) {
            double distance = random.nextDouble(maxDistance);
            Vector vector = RandomUtil.randomVector(distance);
            Location spawnLocation = RayTraceUtil.rayTraceNoCollisionBlock(target.getLocation(), target.getWorld(), vector, distance);
            if (entityType.getEntityClass() == null) {
                throw new IllegalStateException("Unknown entity type "+ entityType);
            }
            target.getWorld().spawn(spawnLocation, entityType.getEntityClass(), CreatureSpawnEvent.SpawnReason.CUSTOM)
                    .setPose(Pose.SWIMMING);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RandSpawnAnomaly<?> that)) return false;
        if (!super.equals(o)) return false;
        return count == that.count && Double.compare(maxDistance, that.maxDistance) == 0 && entityType == that.entityType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), entityType, count, maxDistance);
    }
}
