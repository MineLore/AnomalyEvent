package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.local.LocalAnomaly;

/**
* @author TheDiVaZo
* created on 20.02.2025
*/
public interface Launcher<A extends LocalAnomaly<?>> {
    boolean launch(A anomaly);
}
