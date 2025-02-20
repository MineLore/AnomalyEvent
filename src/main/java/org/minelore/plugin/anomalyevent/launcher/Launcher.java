package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;

/**
* @author TheDiVaZo
* created on 20.02.2025
*/
public interface Launcher<A extends Anomaly<?>> {
    boolean launch(A anomaly);
}
