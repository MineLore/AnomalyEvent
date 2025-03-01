package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;
import org.minelore.plugin.anomalyevent.anomaly.local.LocalAnomaly;
import org.minelore.plugin.anomalyevent.launcher.condition.ConditionLaunch;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

/**
* @author TheDiVaZo
* created on 20.02.2025
*/
public class LocalLauncherImpl<T, A extends LocalAnomaly<T>> implements LocalLauncher<T, A>
{
    private final A anomaly;
    private final List<ConditionLaunch<A>> conditionLaunches;

    LocalLauncherImpl(A anomaly, List<ConditionLaunch<A>> conditionLaunches) {
        this.anomaly = anomaly;
        this.conditionLaunches = List.copyOf(conditionLaunches);
    }

    @Override
    public Collection<ConditionLaunch<A>> getConditionLaunch() {
        return conditionLaunches;
    }

    @Override
    public boolean launch(T target) {
        if (hasLaunchReady()) {
            conditionLaunches.forEach(ConditionLaunch::markLaunch);
            anomaly.activate(target);
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
