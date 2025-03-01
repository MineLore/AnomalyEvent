package org.minelore.plugin.anomalyevent.launcher.condition;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;
import org.minelore.plugin.anomalyevent.util.CooldownRegistry;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public class CooldownCondition<A extends Anomaly> implements ConditionLaunch<A> {

    private final A anomaly;
    private LocalDateTime localDateTime;
    private final Duration duration;

    public CooldownCondition(A anomaly, Duration duration) {
        this.anomaly = anomaly;
        this.duration = duration;
    }

    @Override
    public void markLaunch() {
        localDateTime = LocalDateTime.now().plus(duration);
    }

    @Override
    public boolean test() {
        return localDateTime.isBefore(LocalDateTime.now());
    }

    @Override
    public A getAnomaly() {
        return anomaly;
    }
}
