package org.minelore.plugin.anomalyevent.anomaly.local;

/**
 * @author TheDiVaZo
 * created on 19.02.2025
 */
public interface ImpactAnomaly<T> {
    T getTarget();
    boolean deactivate();
    boolean hasActive();
}
