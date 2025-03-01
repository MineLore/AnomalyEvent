package org.minelore.plugin.anomalyevent.manager;

public interface LocalAnomalyManager<T> {
    void start(String name, T target);
}
