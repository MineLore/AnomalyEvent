package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;
import org.minelore.plugin.anomalyevent.util.CooldownRegistry;

import java.time.Duration;

/**
* @author TheDiVaZo
* created on 20.02.2025
*/
public class CooldownAnomalyLauncher<A extends Anomaly<?>> implements Launcher<A>
{
    private final Launcher<A> launcher;
    private final CooldownRegistry<A> cooldownRegistry;
    private final Duration duration;

    public CooldownAnomalyLauncher(Launcher<A> launcher, CooldownRegistry<A> cooldownRegistry, Duration duration) {
        this.launcher = launcher;
        this.cooldownRegistry = cooldownRegistry;
        this.duration = duration;
    }

    @Override
    public boolean launch(A anomaly) {
        if (!cooldownRegistry.isOnCooldown(anomaly)) {
            cooldownRegistry.register(anomaly, duration);
            return launcher.launch(anomaly);
        }
        return false;
    }

    public CooldownRegistry<A> getCooldownRegistry() {
        return cooldownRegistry;
    }

    public Duration getDuration() {
        return duration;
    }
}
