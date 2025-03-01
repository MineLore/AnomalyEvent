package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.global.GlobalAnomaly;
import org.minelore.plugin.anomalyevent.launcher.condition.ConditionLaunch;

import java.util.Collection;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public interface GlobalLauncher<A extends GlobalAnomaly> extends Launcher<A> {
    boolean launch();
}
