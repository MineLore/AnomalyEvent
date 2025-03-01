package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.local.LocalAnomaly;
import org.minelore.plugin.anomalyevent.util.CooldownRegistry;

import java.time.Duration;
import java.time.LocalDateTime;

/**
* @author TheDiVaZo
* created on 20.02.2025
*/
public class LocalAnomalyLauncherImpl<T, A extends LocalAnomaly<T>> implements Launcher<T, A>
{
    private final A anomaly;
    private LocalDateTime localDateTime;
    private final Duration duration;

    public LocalAnomalyLauncherImpl(A anomaly, LocalDateTime localDateTime, Duration duration) {
        this.anomaly = anomaly;
        this.localDateTime = localDateTime;
        this.duration = duration;
    }

    @Override
    public boolean launch(T target) {
        if (hasLaunchReady()) {
            anomaly.activate(target);
            localDateTime = LocalDateTime.now().plus(duration);
            return true;
        }
        else return false;
    }

    @Override
    public boolean hasLaunchReady() {
        return localDateTime.isBefore(LocalDateTime.now());
    }

    @Override
    public A getAnomaly() {
        return anomaly;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Duration getDuration() {
        return duration;
    }
}
