package org.minelore.plugin.anomalyevent.config.anomalies;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class ChargeCreeperConfig extends AnomalySetting {
    private double distance;
    private int creeperCount;
    private double probability;
}
