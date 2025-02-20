package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;
import org.minelore.plugin.anomalyevent.util.CooldownRegistry;

import java.time.Duration;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * @author TheDiVaZo
 * created on 20.02.2025
 */
public class CooldownTargetLauncher<T, A extends Anomaly<T>> extends TargetLauncher<T, A> {
    protected final CooldownRegistry<T> cooldownRegistry;
    protected final Duration duration;

    public CooldownTargetLauncher(Supplier<Iterator<T>> iteratorSupplier, CooldownRegistry<T> cooldownRegistry, Duration duration) {
        super(iteratorSupplier);
        this.cooldownRegistry = cooldownRegistry;
        this.duration = duration;
    }

    @Override
    public boolean launch(A anomaly) {
        Iterator<T> iterator = iteratorSupplier.get();
        boolean anyLaunch = false;
        while (iterator.hasNext()) {
            T target = iterator.next();
            if (!cooldownRegistry.isOnCooldown(target)) {
                cooldownRegistry.register(target, duration);
                anomaly.activate(target);
                anyLaunch = true;
            }
        }
        return anyLaunch;
    }

    public CooldownRegistry<T> getCooldownRegistry() {
        return cooldownRegistry;
    }

    public Duration getDuration() {
        return duration;
    }
}
