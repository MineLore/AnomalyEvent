package org.minelore.plugin.anomalyevent.anomaly;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pose;
import org.bukkit.util.Vector;
import org.minelore.plugin.anomalyevent.data.RandTeleportData;
import org.minelore.plugin.anomalyevent.util.RandomUtil;
import org.minelore.plugin.anomalyevent.util.RayTraceUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Аномалия на случайное перемещение
 * @param <T> класс цели, которую будут перемещать. Например, {@link org.bukkit.entity.Player}
 */
public class RandTeleportAnomaly<T extends LivingEntity> extends FromDataAnomaly<RandTeleportData, T> {
    public RandTeleportAnomaly(String name, RandTeleportData data) {
        super(name, data);
    }

    @Override
    public void activate(LivingEntity target) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double distance = random.nextDouble(data.maxDistance);

        Vector vector = RandomUtil.randomVector(distance);

        Location resultLocation = RayTraceUtil.rayTraceNoCollisionBlock(target.getLocation(), target.getWorld(), vector, distance);
        target.setPose(Pose.SWIMMING);
        target.teleport(resultLocation);
    }
}
