package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;
import org.minelore.plugin.anomalyevent.launcher.condition.ConditionLaunch;

import java.util.Collection;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public interface Launcher<A extends Anomaly> {
    Collection<ConditionLaunch<A>> getConditionLaunch();
    boolean hasLaunchReady();
    A getAnomaly();
}
