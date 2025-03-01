package org.minelore.plugin.anomalyevent.anomaly.global;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;

public interface GlobalAnomaly extends Anomaly {
    void activate();
    void deactivate();
    void hasActive();
}
