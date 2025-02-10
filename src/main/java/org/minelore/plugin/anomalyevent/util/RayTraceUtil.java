package org.minelore.plugin.anomalyevent.util;

import io.papermc.paper.math.Position;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public final class RayTraceUtil {
    public static Location rayTraceNoCollisionBlock(Position start, World world, Vector direction, double maxDistance) {
        RayTraceResult rayTraceResult = world.rayTraceBlocks(start, direction, maxDistance, FluidCollisionMode.ALWAYS, true, null);
        Location resultLocation = start.toLocation(world);
        if (rayTraceResult != null && rayTraceResult.getHitBlock() != null && rayTraceResult.getHitBlockFace() != null) {
            Vector hitPosition = rayTraceResult.getHitPosition();
            if (direction.getX() - hitPosition.getX() >= 0) {
                Location hitBlock = rayTraceResult.getHitBlock().getLocation();
                resultLocation = hitBlock.add(rayTraceResult.getHitBlockFace().getDirection());
            }
        }
        return resultLocation;
    }
}
