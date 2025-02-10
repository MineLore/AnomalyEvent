package org.minelore.plugin.anomalyevent.anomaly;

public interface Anomaly<T> {
    String getName();
    void activate(T target);

    interface Deactivable<T> extends Anomaly<T> {
        void deactivate(T target);
    }
}
