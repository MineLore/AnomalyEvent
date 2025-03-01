package org.minelore.plugin.anomalyevent.config.anomalies;

import org.jetbrains.annotations.Range;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

public class TargetAnomalySetting extends AnomalySetting {
    private Target target;

    @ConfigSerializable
    public static class Target {
        @ConfigSerializable
        public static class One extends Target {

        }

        @ConfigSerializable
        public static class Percent extends Target {
            @Range(from = 0, to = 1)
            private Double percent;
        }
    }
}
