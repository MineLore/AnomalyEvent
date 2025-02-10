package org.minelore.plugin.anomalyevent.data;

import java.time.Duration;

public class ThunderStrikeData extends AnomalyData {

    public final int countLighting;
    public final Duration interval;

    public ThunderStrikeData(int countLighting, Duration interval) {
        this.countLighting = countLighting;
        this.interval = interval;
    }
}
