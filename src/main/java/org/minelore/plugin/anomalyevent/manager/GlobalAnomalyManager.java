package org.minelore.plugin.anomalyevent.manager;

public interface GlobalAnomalyManager {
    void start(String name);
    boolean tryStop(String name);

    boolean hasAnomaly(String name);
}
