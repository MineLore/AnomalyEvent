package org.minelore.plugin.anomalyevent.launcher.condition;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;

import java.util.function.Predicate;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public interface ConditionLaunch<A extends Anomaly> {
    void markLaunch();
    boolean test();
    A getAnomaly();
}
