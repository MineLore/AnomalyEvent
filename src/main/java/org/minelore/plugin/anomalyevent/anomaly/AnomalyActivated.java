package org.minelore.plugin.anomalyevent.anomaly;

/**
 * @author TheDiVaZo
 * created on 19.02.2025
 */
public interface AnomalyActivated<T> {
    T getTarget();
    boolean deactivate();
    boolean hasActive();
}
