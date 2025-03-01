package org.minelore.plugin.anomalyevent.anomaly.local;

import com.google.common.base.Objects;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pose;
import org.bukkit.util.Vector;
import org.minelore.plugin.anomalyevent.util.RandomUtil;
import org.minelore.plugin.anomalyevent.util.RayTraceUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Аномалия на случайное перемещение
 * @param <T> класс цели, которую будут перемещать. Например, {@link org.bukkit.entity.Player}
 */
public class RandTeleportLocalAnomaly<T extends LivingEntity> extends AbstractLocalAnomaly<T> {
    private final double maxDistance;

    public RandTeleportLocalAnomaly(String name, double maxDistance) {
        super(name);
        this.maxDistance = maxDistance;
    }

    @Override
    public void activate(LivingEntity target) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double distance = random.nextDouble(maxDistance);

        Vector vector = RandomUtil.randomVector(distance);

        Location resultLocation = RayTraceUtil.rayTraceNoCollisionBlock(target.getLocation(), target.getWorld(), vector, distance);
        target.setPose(Pose.SWIMMING);
        target.teleport(resultLocation);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RandTeleportLocalAnomaly<?> that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(maxDistance, that.maxDistance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), maxDistance);
    }
}
