package org.minelore.plugin.anomalyevent.data;

import org.jetbrains.annotations.Range;

public class ChargeCreeperData extends AnomalyData {

    public final double distance;
    public final int creeperCount;
    public final double probability;

    public ChargeCreeperData(double distance, int creeperCount, @Range(from = 0, to = 1) double probability) {
        this.distance = distance;
        this.creeperCount = creeperCount;
        this.probability = probability;
    }
}
