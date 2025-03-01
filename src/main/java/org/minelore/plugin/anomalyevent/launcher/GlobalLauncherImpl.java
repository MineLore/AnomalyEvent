package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.global.GlobalAnomaly;
import org.minelore.plugin.anomalyevent.launcher.condition.ConditionLaunch;

import java.util.Collection;
import java.util.List;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public class GlobalLauncherImpl<A extends GlobalAnomaly> implements GlobalLauncher<A> {
    private final A anomaly;
    private final List<ConditionLaunch<A>> conditionLaunches;

    GlobalLauncherImpl(A anomaly, List<ConditionLaunch<A>> conditionLaunches) {
        this.anomaly = anomaly;
        this.conditionLaunches = List.copyOf(conditionLaunches);
    }

    @Override
    public Collection<ConditionLaunch<A>> getConditionLaunch() {
        return conditionLaunches;
    }

    @Override
    public boolean launch() {
        if (hasLaunchReady()) {
            conditionLaunches.forEach(ConditionLaunch::markLaunch);
            anomaly.activate();
            return true;
        }
        else return false;
    }

    @Override
    public boolean hasLaunchReady() {
        return conditionLaunches.stream().allMatch(ConditionLaunch::test);
    }

    @Override
    public A getAnomaly() {
        return anomaly;
    }
}
