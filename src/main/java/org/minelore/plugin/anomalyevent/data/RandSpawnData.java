package org.minelore.plugin.anomalyevent.data;

import org.bukkit.entity.EntityType;

public class RandSpawnData extends AnomalyData {

    public final EntityType entityType;
    public final int count;
    public final double maxDistance;

    public RandSpawnData(EntityType entityType, int count, double maxDistance) {
        this.entityType = entityType;
        this.count = count;
        this.maxDistance = maxDistance;
    }
}
