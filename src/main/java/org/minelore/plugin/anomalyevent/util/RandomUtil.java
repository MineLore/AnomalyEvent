package org.minelore.plugin.anomalyevent.util;

import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {
    public static Vector randomDirection(double distance) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double theta = Math.toRadians(random.nextDouble() * 360);
        double phi = Math.toRadians(random.nextDouble() * 360);

        Vector vector = new Vector(
                distance * Math.sin(theta) * Math.cos(phi),
                distance * Math.sin(theta) * Math.sin(phi),
                distance * Math.cos(phi)
        );

        return vector;
    }

    public static Vector randomVector(double maxDistance) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double distance = random.nextDouble(maxDistance);
        return randomDirection(distance);
    }
}
