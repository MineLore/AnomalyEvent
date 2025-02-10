package org.minelore.plugin.anomalyevent.activator;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pose;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.minelore.plugin.anomalyevent.data.RandTeleportData;
import org.minelore.plugin.anomalyevent.util.RandomUtil;
import org.minelore.plugin.anomalyevent.util.RayTraceUtil;

import java.util.concurrent.ThreadLocalRandom;

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
