package org.minelore.plugin.anomalyevent.activator;

public interface Anomaly<T> {
    String getName();
    void activate(T target);

    interface Deactivable<T> extends Anomaly<T> {
        void deactivate(T target);
    }
}
